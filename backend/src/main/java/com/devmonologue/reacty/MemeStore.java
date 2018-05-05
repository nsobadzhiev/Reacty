package com.devmonologue.reacty;

import com.mongodb.Block;
import com.mongodb.DBCollection;
import com.mongodb.Function;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

public interface MemeStore {
    public Reaction getReaction(String name);
    public List<Reaction> getReactions(String name);
    public void saveReaction(String name, String tags, String fileName);
}
