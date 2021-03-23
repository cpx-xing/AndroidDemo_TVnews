package com.example.demo_tvnews.db;

public class DatabaseBean {
    private int _id;
    private String user_name,user_pwd;

    public DatabaseBean(int _id, String user_name, String user_pwd) {
        this._id = _id;
        this.user_name = user_name;
        this.user_pwd = user_pwd;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }
}

