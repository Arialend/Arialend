package com.android.notepad.utils;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBUtils {
    public static final String DATABASE_NAME = "Notepad";    //数据库名
    public static final String DATABASE_TABLE = "Note";      //表1名
    public static final String DATABASE_TABLE2 = "Account";  //表2名
    public static final int DATABASE_VERION = 1;             //数据库版本

    // 数据库表1中的列名
    public static String NOTEPAD_ID = "id";                  //编号
    public static final String NOTEPAD_CONTENT = "content";  //内容
    public static final String NOTEPAD_TIME = "notetime";    //时间

    // 数据库表2中的列名
    public static final String ACCOUNT_USERNAME = "username";//用户名
    public static final String ACCOUNT_PASSWORD = "password";//密码

    //获取当前日期
    public static final String getTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date=new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

}
