package com.devmonologue.reacty;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.Document;

import java.security.Principal;

public class User implements Principal {

    public String username;
    public String password;

    public User() {
        this.username = "";
        this.password = "";
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Document document) {
        this.username = document.getString("username");
        this.password = document.getString("password");
    }

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

    @JsonProperty
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
