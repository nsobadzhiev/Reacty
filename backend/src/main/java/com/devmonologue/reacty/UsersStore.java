package com.devmonologue.reacty;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

public class UsersStore {

    private MongoClient mongo = new MongoClient("localhost", 27017);
    private MongoDatabase db = mongo.getDatabase("MyDatabase");

    public Boolean verifyCredentials(String user, String pass) {
        MongoCollection usersCollection = db.getCollection("users");
        Bson text = Filters.regex("username", user, "i");
        FindIterable findIterable = usersCollection.find(text);
        if (findIterable.first() != null) {
            User matchingUser = new User((org.bson.Document)findIterable.first());
            return matchingUser.password.equals(pass);
        }
        return false;

    }
}
