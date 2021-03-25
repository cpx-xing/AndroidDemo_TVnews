package com.example.demo_tvnews.entity;

import java.io.Serializable;

public class VideoEntity implements Serializable {
    private int id;
    private String title;
    private String name;
    private int dzCount;
    private int plCount;
    private int scCount;

    public VideoEntity(int id, String title, String name, int dzCount, int plCount, int scCount) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.dzCount = dzCount;
        this.plCount = plCount;
        this.scCount = scCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDzCount() {
        return dzCount;
    }

    public void setDzCount(int dzCount) {
        this.dzCount = dzCount;
    }

    public int getPlCount() {
        return plCount;
    }

    public void setPlCount(int plCount) {
        this.plCount = plCount;
    }

    public int getScCount() {
        return scCount;
    }

    public void setScCount(int scCount) {
        this.scCount = scCount;
    }
}
