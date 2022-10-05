package com.earl.javachat.data.models;

public interface CurrentUser {

    class BaseCurrentUser implements CurrentUser {
        public String email;
        public String password;
        String image;
        String nickName;
        String bio;

        public BaseCurrentUser (String email, String password) {
            this.email = email;
            this.password = password;
        }
    }
}