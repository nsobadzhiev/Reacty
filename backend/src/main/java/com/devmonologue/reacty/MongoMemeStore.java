package com.devmonologue.reacty;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class MongoMemeStore implements MemeStore {

    private MongoClient mongo = new MongoClient("localhost", 27017);
    private MongoDatabase db = mongo.getDatabase("MyDatabase");


    public MongoMemeStore() {

    }

    @Override
    public Reaction getReaction(String name) {
        MongoDatabase db =  mongo.getDatabase("MyDatabase");
        MongoCollection collection = db.getCollection("reactions");
        Bson text = Filters.regex("tags", name, "i");
        FindIterable findIterable = collection.find(text);
        if (findIterable.first() != null) {
            Reaction foundReaction = new Reaction((org.bson.Document)findIterable.first());
            System.out.print("Reaction: " + foundReaction.image);
            return foundReaction;
        }
        else {
            return new Reaction();
        }
    }

    @Override
    public List<Reaction> getReactions(String name) {
        MongoDatabase db =  mongo.getDatabase("MyDatabase");
        MongoCollection collection = db.getCollection("reactions");
        Bson text = Filters.regex("tags", name, "i");
        FindIterable findIterable = collection.find(text);
        List<Reaction> reactions = new ArrayList<Reaction>();
        for (Object document : findIterable) {
            reactions.add(new Reaction((org.bson.Document)document));
        }
        return reactions;
    }

    @Override
    public void saveReaction(String name, String tags, String fileName) {
        MongoDatabase db =  mongo.getDatabase("MyDatabase");
        MongoCollection collection = db.getCollection("reactions");
        Reaction newRecord = new Reaction();
        newRecord.name = name;
        newRecord.tags = tags;
        newRecord.image = fileName;
        collection.insertOne(newRecord.toDocument());
    }

}