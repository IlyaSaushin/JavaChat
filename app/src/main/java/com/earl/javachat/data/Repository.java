package com.earl.javachat.data;

import com.earl.javachat.core.Keys;
import com.earl.javachat.core.SuccessOperationResultListener;
import com.earl.javachat.data.models.CurrentUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public interface Repository {

    void register(CurrentUser.BaseCurrentUser user, SuccessOperationResultListener callback);

    void saveDetails(HashMap<String, Object> userDetails, SuccessOperationResultListener callback);

    void logIn(CurrentUser.BaseCurrentUser user, SuccessOperationResultListener callback);

    void signOut();

    class BaseRepository implements Repository {

        FirebaseUser localUser;

        @Override
        public void register(CurrentUser.BaseCurrentUser user, SuccessOperationResultListener callback) {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.createUserWithEmailAndPassword(user.email, user.password)
                    .addOnSuccessListener(authResult -> {
                        localUser = auth.getCurrentUser();
                        callback.success();
                    })
                    .addOnFailureListener(callback::fail);
        }

        @Override
        public void saveDetails(HashMap<String, Object> userDetails, SuccessOperationResultListener callback) {
            FirebaseFirestore database = FirebaseFirestore.getInstance();
            database.collection(Keys.KEY_COLLECTION_USERS).document(localUser.getUid())
                    .set(userDetails)
                    .addOnSuccessListener(unused -> {
                        callback.success();
                    })
                    .addOnFailureListener(callback::fail);
        }

        @Override
        public void logIn(CurrentUser.BaseCurrentUser user, SuccessOperationResultListener callback) {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(user.email, user.password)
                    .addOnSuccessListener(authResult -> {
                        callback.success();
                    })
                    .addOnFailureListener(callback::fail);
        }

        @Override
        public void signOut() {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
        }
    }
}