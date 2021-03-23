package com.example.demo_tvnews.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    public static SQLiteDatabase sqLiteDatabase;
    public static void initDB(Context context){
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(context);
        sqLiteDatabase = mySQLiteHelper.getWritableDatabase();

       Cursor cursor = sqLiteDatabase.query("login_user",null,null,null,null,null,null);
       if(!cursor.moveToFirst()){
            sqLiteDatabase.execSQL("INSERT INTO login_user (user_name,user_pwd) values (?,?)",new String[]{"admin","admin"});
       }
    }

    public static boolean checkUser(String name,String pwd){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT user_pwd FROM login_user WHERE user_name = ?",new String[]{name});
//        如果有数据，返回true
        if(cursor.moveToFirst()){
            String user_pwd = cursor.getString(cursor.getColumnIndex("user_pwd"));
            if(pwd.equals(user_pwd)){
                return true;
            }
        }
        return false;
    }

    public static boolean registerUser(String name,String pwd){
        try {
            sqLiteDatabase.execSQL("INSERT INTO login_user (user_name,user_pwd) values (?,?)",new String[]{name,pwd});
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
