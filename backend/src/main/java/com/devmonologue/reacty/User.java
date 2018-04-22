package com.devmonologue.reacty;

import java.security.Principal;

public class User implements Principal {

    public String username;
    public String password;

    @Override
    public boolean equals(Object another) {
        if (another.getClass() == User.class) {
            User anotherUser = (User)another;
            return username == anotherUser.username && password == anotherUser.password;
        }
        return false;
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String getName() {
        return username;
    }
}
