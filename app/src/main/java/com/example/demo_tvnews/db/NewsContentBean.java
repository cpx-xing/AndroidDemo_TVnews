package com.example.demo_tvnews.db;

public class NewsContentBean {
    private String name;
    private String newsContent;

    public NewsContentBean(String name, String newsContent) {
        this.name = name;
        this.newsContent = newsContent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
}
