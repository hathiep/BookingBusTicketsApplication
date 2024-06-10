package com.example.timxekhach.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.timxekhach.DBUser.User;

import java.util.ArrayList;

public class SQLUser extends SQLiteOpenHelper {
    private static final String DatabaseName = "datauser1";

    private static final String Table_User = "users";
    private static final String UserID = "userid";
    private static final String Account = "account";
    private static final String FullName = "fullname";
    private static final String Email = "email";
    private static final String Password = "password";
    private static final String Status = "status";
    private static int version = 1;

    private SQLiteDatabase db;
    private Context context;
    private ContentValues values;

    public SQLUser(Context context) {
        super(context, DatabaseName, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Table_user = " CREATE TABLE " + Table_User + " ( " +
                UserID + " integer primary key, " +
                Account + " TEXT, " +
                FullName + " TEXT, " +
                Email + " TEXT, " +
                Password + " TEXT, " +
                Status + " TEXT)";
        db.execSQL(Create_Table_user);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Table_User);
        onCreate(db);
    }

    public void AddUser(User user) {
        db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(Account, user.getAccount());
        values.put(FullName, user.getFullname());
        values.put(Email, user.getGmail());
        values.put(Password, user.getPassword());
        values.put(Status, user.getStatus());
        db.insert(Table_User, null, values);
        db.close();
    }

    public ArrayList<User> getArrayUser(){
        ArrayList<User> list = new ArrayList<>();
        String selectUser = "select * from " + Table_User;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectUser, null);
        if(cursor.moveToFirst()){
            do{
                User s = new User();
                s.setUserID(cursor.getInt(0));
                s.setAccount(cursor.getString(1));
                s.setFullname(cursor.getString(2));
                s.setGmail(cursor.getString(3));
                s.setPassword(cursor.getString(4));
                s.setStatus(cursor.getString(5));
                list.add(s);
            }while (cursor.moveToNext());
        }else{
            list = null;
        }
        cursor.close();
        db.close();
        return list;
    }
    public User getUser(String account){

        db = this.getWritableDatabase();
        Cursor cursor = db.query(Table_User, new String[]{UserID, Account, FullName, Email, Password, Status},
                Account + "=?", new String[]{account}, null, null, null,null);
        User s = new User();
        if(cursor.moveToFirst()){
            s.setUserID(cursor.getInt(0));
            s.setAccount(cursor.getString(1));
            s.setFullname(cursor.getString(2));
            s.setGmail(cursor.getString(3));
            s.setPassword(cursor.getString(4));
            s.setStatus(cursor.getString(5));
        }else{
            s = null;
        }
        cursor.close();
        db.close();
        return s;
    }
    public boolean Changpass(String account, String newpass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Password,newpass);
        int a = db.update(Table_User,values,Account + "=?",new String[]{account});
        if(a != 0){
            return true;
        }else{
            return false;
        }
    }

}

