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

    public static void putNewsContent(NewsContentBean newsBean){
        sqLiteDatabase.execSQL("INSERT INTO news_content(news_name,news_content) values(?,?)",new String[]{newsBean.getName(),newsBean.getNewsContent()});
    }
//    判断需要存储的数据是否是空的
    public static boolean checkEmpty(){
        Cursor cursor = sqLiteDatabase.query("news_content",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            return false;
        }else return true;
    }
//    判断是不是第一次填写
    public static boolean isFirstData(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM news_content WHERE news_name = ?",new String[]{"jiankang"});
        if (cursor.moveToFirst()){
            return false;
        }else {
            return true;
        }
    }
//   在新闻不是空的时候，需要更新数据
    public static void reflashNews(String name,NewsContentBean newsContentBean){
        String sql = "UPDATE news_content SET news_content = ? WHERE news_name = ?";
        sqLiteDatabase.execSQL(sql,new String[]{newsContentBean.getNewsContent(),name});
    }
//    生成页面是读取新闻数据
    public static String getNewsContent(String name){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM news_content WHERE news_name = ?",new String[]{name});
        if (cursor.moveToNext()){
            String newsContent = cursor.getString(cursor.getColumnIndex("news_content"));
            return newsContent;
        }else {
            return "查询失败";
        }
    }

}
