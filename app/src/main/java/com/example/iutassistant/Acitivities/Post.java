package com.example.iutassistant.Acitivities;

public class Post {

    private String poster_name;
    private String post,id,postArea;

    public Post(){}

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getPostArea() {
        return postArea;
    }

    public Post(String poster_name, String post, String id, String postArea) {
        this.poster_name = poster_name;
        this.post = post;
        this.id=id;
        this.postArea=postArea;
    }

    public String getPoster_name() {
        return poster_name;
    }

    public void setPoster_name(String poster_name) {
        this.poster_name = poster_name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }


}
