package com.devmonologue.reacty;

import java.util.*;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;

public class AwsMemeStore implements MemeStore {

    final private AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();
    @Override
    public Reaction getReaction(String name) {
        HashMap<String,AttributeValue> queryAttributes = new HashMap<String,AttributeValue>();

        queryAttributes.put("name", new AttributeValue(name));

        GetItemRequest request = null;
        request = new GetItemRequest()
                .withKey(queryAttributes)
                .withTableName("reactions");

        try {
            Map<String,AttributeValue> returnedItem = ddb.getItem(request).getItem();
            if (returnedItem != null) {
                Set<String> keys = returnedItem.keySet();
                for (String key : keys) {
                    System.out.format("%s: %s\n",
                            key, returnedItem.get(key).toString());
                }
                Reaction foundReaction = new Reaction();
                foundReaction.id = returnedItem.get("id").toString();
                foundReaction.image = returnedItem.get("image").toString();
                foundReaction.name = returnedItem.get("name").toString();
                foundReaction.tags = returnedItem.get("tags").toString();
                return foundReaction;
            } else {
                System.out.format("No item found with the key %s!\n", name);
            }
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        return null;
    }

    @Override
    public List<Reaction> getReactions(String name) {
        List<Reaction> reactions = new ArrayList<Reaction>();
        reactions.add(getReaction(name));
        return reactions;
    }

    @Override
    public void saveReaction(String name, String tags, String fileName) {
        HashMap<String,AttributeValue> reactionAttributes = new HashMap<String,AttributeValue>();
        reactionAttributes.put("name", new AttributeValue(name));
        reactionAttributes.put("tags", new AttributeValue(tags));
        reactionAttributes.put("image", new AttributeValue(fileName));

        try {
            PutItemResult result = ddb.putItem("reactions", reactionAttributes);
        }
        catch (ResourceNotFoundException e) {
            System.err.println("Error: The table can't be found.\n");
            System.exit(1);
        } catch (AmazonServiceException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
