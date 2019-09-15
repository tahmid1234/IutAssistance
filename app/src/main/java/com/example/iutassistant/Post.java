package com.example.iutassistant;

public class Post {

    private String poster_name;
    private String post;

    public Post(){}

    public Post(String poster_name, String post) {
        this.poster_name = poster_name;
        this.post = post;
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
