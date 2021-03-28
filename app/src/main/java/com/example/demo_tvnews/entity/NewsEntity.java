package com.example.demo_tvnews.entity;

public class NewsEntity {
    private int idcode;
    private String newsTitle;
    private String imageUrl;
    private String newsAuthor;
    private String newsTime;

    public NewsEntity(int idcode, String newsTitle, String imageUrl, String newsAuthor, String newsTime) {
        this.idcode = idcode;
        this.newsTitle = newsTitle;
        this.imageUrl = imageUrl;
        this.newsAuthor = newsAuthor;
        this.newsTime = newsTime;
    }

    public int getIdcode() {
        return idcode;
    }

    public void setIdcode(int idcode) {
        this.idcode = idcode;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }
}
