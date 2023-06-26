package com.example.testjavafx.store;

import com.example.testjavafx.entity.User;

public class CurrentUser {
    private static User user = null;
    public static User get() {
        return user;
    }

    public static void set(User user) {
        CurrentUser.user = user;
    }
}
