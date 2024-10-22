package com.app.entity;

public class LoginUser {
    private static final LoginUser instance = new LoginUser();
    private User currentUser;

    private LoginUser() {}

    public static LoginUser getInstance() {
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
