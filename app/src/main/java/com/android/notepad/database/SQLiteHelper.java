package com.android.notepad.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.android.notepad.bean.AccountBean;
import com.android.notepad.bean.NotepadBean;
import com.android.notepad.utils.DBUtils;

public class SQLiteHelper  extends SQLiteOpenHelper{
    // 创建一个数据库对象
    private SQLiteDatabase sqLiteDatabase;
    // 构造函数，用于初始化对象
    public SQLiteHelper(Context context){
        super(context, DBUtils.DATABASE_NAME,null, DBUtils.DATABASE_VERION);
        sqLiteDatabase = this.getWritableDatabase();
    }
    // 初始化同时创建表
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + DBUtils.DATABASE_TABLE + "("+ DBUtils.NOTEPAD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DBUtils.NOTEPAD_CONTENT + " text, "+ DBUtils.NOTEPAD_TIME + " text)");
        db.execSQL("CREATE TABLE " + DBUtils.DATABASE_TABLE2 + "(" + DBUtils.ACCOUNT_USERNAME + " text PRIMARY KEY," + DBUtils.ACCOUNT_PASSWORD + " text)");
    }
    // 升级数据库的函数
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
    // 向表中插入数据
    public boolean insertData(String userContent, String userTime){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBUtils.NOTEPAD_CONTENT, userContent);
        contentValues.put(DBUtils.NOTEPAD_TIME, userTime);
        return sqLiteDatabase.insert(DBUtils.DATABASE_TABLE, null, contentValues) > 0;
    }
    // 从表中删除数据
    public boolean deleteData(String id){
        String sql = DBUtils.NOTEPAD_ID + "=?";
        // 连接字符串，易错点!
        String[] contentValuesArray = new String[]{String.valueOf(id)};
        return sqLiteDatabase.delete(DBUtils.DATABASE_TABLE, sql, contentValuesArray) > 0;
    }
    // 修改表中指定id的数据
    public boolean updateData(String id, String content, String userYear){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBUtils.NOTEPAD_CONTENT, content);
        contentValues.put(DBUtils.NOTEPAD_TIME, userYear);
        String sql = DBUtils.NOTEPAD_ID + "=?";
        String[] strings=new String[]{id};
        return sqLiteDatabase.update(DBUtils.DATABASE_TABLE, contentValues, sql, strings) > 0;
    }
    // 查询表中某条数据
    public List<NotepadBean> query(){
        List<NotepadBean> list = new ArrayList<NotepadBean>();
        Cursor cursor = sqLiteDatabase.query(DBUtils.DATABASE_TABLE,null,null,
                null,null,null,DBUtils.NOTEPAD_ID + " desc");
        if (cursor != null){
            while (cursor.moveToNext()){
                NotepadBean noteInfo = new NotepadBean();
                String id = String.valueOf(cursor.getInt(cursor.getColumnIndex(DBUtils.NOTEPAD_ID)));
                String content = cursor.getString(cursor.getColumnIndex(DBUtils.NOTEPAD_CONTENT));
                String time = cursor.getString(cursor.getColumnIndex(DBUtils.NOTEPAD_TIME));
                noteInfo.setId(id);
                noteInfo.setNotepadContent(content);
                noteInfo.setNotepadTime(time);
                list.add(noteInfo);
            }
            cursor.close();
        }
        return list;
    }
    // 判断账号是否已经存在（找到返回true，没找到返回false）
    public boolean isExistAccount(String name) {
        Cursor cursor = sqLiteDatabase.query(DBUtils.DATABASE_TABLE2, null, "username = ?", new String[]{name}, null, null, null);
        return cursor != null && cursor.getCount() > 0;
    }
    // 查询用户名对应的密码
    public String getPassword(String name) {
        Cursor cursor = sqLiteDatabase.query(DBUtils.DATABASE_TABLE2, null, "username = ?", new String[]{name}, null, null, null);
        String password = null;
        if (cursor.moveToFirst()) {
            password = cursor.getString(cursor.getColumnIndex(DBUtils.ACCOUNT_PASSWORD));
        }
        cursor.close();
        return password;
    }
    // 添加账号
    public void addAccount(String name, String pass) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.ACCOUNT_USERNAME, name);
        values.put(DBUtils.ACCOUNT_PASSWORD, pass);
        sqLiteDatabase.insert(DBUtils.DATABASE_TABLE2, null, values);
    }

}
