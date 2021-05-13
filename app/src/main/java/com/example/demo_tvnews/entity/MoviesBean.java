package com.example.demo_tvnews.entity;

import com.google.gson.annotations.SerializedName;

public class MoviesBean {
    @SerializedName("movieid")
    private String movieid;
    @SerializedName("moviename")
    private String moviename;
    @SerializedName("pic")
    private String pic;

    public MoviesBean(String movieid, String moviename, String pic) {
        this.movieid = movieid;
        this.moviename = moviename;
        this.pic = pic;
    }

    public String getMovieid() {
        return movieid;
    }

    public void setMovieid(String movieid) {
        this.movieid = movieid;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
