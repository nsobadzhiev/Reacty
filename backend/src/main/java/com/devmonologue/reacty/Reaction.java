package com.devmonologue.reacty;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.Document;

public class Reaction {

    @JsonProperty
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @JsonProperty
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String id = "";
    public String name = "";
    public String image = "";
    public String tags = "";

    public Reaction(Document document) {
        this.id = document.getString("id");
        this.name = document.getString("name");
        this.tags = document.getString("tags");
        this.image = document.getString("image");
    }

    public Reaction() {}

    public Document toDocument() {
        Document doc = new Document();
        doc.append("id", this.id);
        doc.append("name", this.name);
        doc.append("tags", this.tags);
        doc.append("image", this.image);
        return doc;
    }
}
