package com.earl.javachat.data;

import android.util.Log;

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
                    .addOnFailureListener(e -> {
                        callback.fail(e);
                        Log.d("tag", "register: " + e);
                    });
        }

        @Override
        public void saveDetails(HashMap<String, Object> userDetails, SuccessOperationResultListener callback) {
            FirebaseFirestore database = FirebaseFirestore.getInstance();
            database.collection(Keys.KEY_COLLECTION_USERS).document(localUser.getUid())
                    .set(userDetails)
                    .addOnSuccessListener(unused -> {
                        callback.success();
                    })
                    .addOnFailureListener(e -> {
                        callback.fail(e);
                        Log.d("tag", "saveDetails: " + e);
                    });
        }
    }
}