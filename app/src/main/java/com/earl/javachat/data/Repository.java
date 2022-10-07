package com.earl.javachat.data;

import android.util.Log;

import com.earl.javachat.core.Keys;
import com.earl.javachat.core.ListFetchingListener;
import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.data.models.CurrentUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface Repository {

    void register(CurrentUser.BaseCurrentUser user, OperationResultListener callback);

    void saveDetails(HashMap<String, Object> userDetails, OperationResultListener callback);

    void logIn(CurrentUser.BaseCurrentUser user, OperationResultListener callback);

    void fetchUsersList(ListFetchingListener callback);

    void signOut();

    class BaseRepository implements Repository {

        FirebaseUser localUser;

        @Override
        public void register(CurrentUser.BaseCurrentUser user, OperationResultListener callback) {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.createUserWithEmailAndPassword(user.email, user.password)
                    .addOnSuccessListener(authResult -> {
                        localUser = auth.getCurrentUser();
                        callback.success();
                    })
                    .addOnFailureListener(callback::fail);
        }

        @Override
        public void saveDetails(HashMap<String, Object> userDetails, OperationResultListener callback) {
            FirebaseFirestore database = FirebaseFirestore.getInstance();
            database.collection(Keys.KEY_COLLECTION_USERS).document(localUser.getUid())
                    .set(userDetails)
                    .addOnSuccessListener(unused -> {
                        callback.success();
                    })
                    .addOnFailureListener(callback::fail);
        }

        @Override
        public void logIn(CurrentUser.BaseCurrentUser user, OperationResultListener callback) {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(user.email, user.password)
                    .addOnSuccessListener(authResult -> {
                        callback.success();
                    })
                    .addOnFailureListener(callback::fail);
        }

        @Override
        public void fetchUsersList(ListFetchingListener callback) {
            Log.d("tag", "fetchUsersList: started");
            List<CurrentUser.UserDetails> users = new ArrayList<>();
            FirebaseFirestore database = FirebaseFirestore.getInstance();
                database.collection(Keys.KEY_COLLECTION_USERS)
                        .get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    CurrentUser.UserDetails user = new CurrentUser.UserDetails(
                                            document.getString(Keys.KEY_IMAGE),
                                            document.getString(Keys.KEY_NICK_NAME),
                                            document.getString(Keys.KEY_USER_BIO)
                                    );
                                    users.add(user);
                                    Log.d("tag1", document.getId() + " => " + document.getData());
                                }
                            } else {
                                // todo
                            }
                            Log.d("tag", "fetchUsersList: " + users);
                            callback.success(users);
                        });
            Log.d("tag", "fetchUsersList: " + users);
        }

        @Override
        public void signOut() {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
        }
    }
}