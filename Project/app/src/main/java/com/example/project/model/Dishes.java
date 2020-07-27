package com.example.project.model;

public class Dishes {
    private String name;
    private String url;
    private int id;

    public Dishes() {
    }

    public Dishes(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
