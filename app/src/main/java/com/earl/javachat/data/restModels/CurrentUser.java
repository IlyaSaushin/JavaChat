package com.earl.javachat.data.restModels;

public interface CurrentUser {

    class BaseCurrentUser implements CurrentUser {
        public String email;
        public String password;

        public BaseCurrentUser (String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    class UserDetails implements CurrentUser {

        public String image;
        public String nickName;
        public String bio;
        public String userId;

        public UserDetails (String image, String nickname, String bio, String userId) {
            this.image = image;
            this.nickName = nickname;
            this.bio = bio;
            this.userId = userId;
        }
    }
}