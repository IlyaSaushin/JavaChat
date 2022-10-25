package com.earl.javachat.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.earl.javachat.core.Keys;
import com.earl.javachat.core.UsersListFetchingResultListener;
import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.data.models.CurrentUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface Repository {

    void register(CurrentUser.BaseCurrentUser user, OperationResultListener callback);

    void saveDetails(HashMap<String, Object> userDetails, OperationResultListener callback);

    void logIn(CurrentUser.BaseCurrentUser user, OperationResultListener callback);

    void fetchUsersList(UsersListFetchingResultListener callback);

    void addUserToContacts(String userId, OperationResultListener callback);

    void getUserById(String userId);

    void signOut();

    class BaseRepository implements Repository {

        FirebaseUser localUser;
        HashMap<String, Object> localUserDetails;

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
            localUserDetails = userDetails;
//            String uid = localUser.getUid();
            FirebaseFirestore database = FirebaseFirestore.getInstance();

//            userDetails.put(Keys.KEY_USER_ID, uid); // todo !!!

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
        public void fetchUsersList(UsersListFetchingResultListener callback) {
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
                                            document.getString(Keys.KEY_USER_BIO),
                                            document.toString()
                                    );
                                    users.add(user);
                                    Log.d("tag1", document.getId() + " => " + document.getData());
                                }
                            } else {
                                // todo
                            }
                            Log.d("tag", "fetchUsersList: " + users);
                            callback.successList(users);
                        });
            Log.d("tag", "fetchUsersList: " + users);
        }

        @Override
        public void addUserToContacts(String userId, OperationResultListener callback) {
            localUserDetails.put("CONTACTS", userId);
            FirebaseFirestore database = FirebaseFirestore.getInstance();
            database.collection(Keys.KEY_COLLECTION_USERS).document(localUser.getUid())
                    .update(localUserDetails)
                    .addOnSuccessListener(unused -> {
                        callback.success();
                    })
                    .addOnFailureListener(e -> {
                        callback.fail(e);
                    });
        }

        @Override
        public void getUserById(String userId) {
            CurrentUser.UserDetails fetchedUser;
            FirebaseFirestore database = FirebaseFirestore.getInstance();
            database.collection(Keys.KEY_COLLECTION_USERS)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.contains(userId)) {
                                    CurrentUser.UserDetails user = new CurrentUser.UserDetails(
                                            document.getString(Keys.KEY_IMAGE),
                                            document.getString(Keys.KEY_NICK_NAME),
                                            document.getString(Keys.KEY_USER_BIO),
                                            document.toString()
                                    );
                                    Log.d("tag", "getUserById: " + user + document);
                                }
                            }
                        }
                        // callback return user
                    });
        }

        @Override
        public void signOut() {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
        }
    }
}