package com.example.demo_tvnews.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteHelper extends SQLiteOpenHelper {
    public MySQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySQLiteHelper(Context context){
        super(context,"usermassage.db",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table login_user(_id integer primary key autoincrement,user_name varchar(20) unique not null,user_pwd varchar(20) unique not null)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
