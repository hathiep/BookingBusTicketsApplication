package com.example.timxekhach.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.timxekhach.DBLog.Log;

import java.util.ArrayList;

public class SQLLog extends SQLiteOpenHelper {
    private static final String DatabaseName = "datalog1";
    private static final String Table_Log = "logs";
    private static final String LogID_log = "logid";
    private static final String Account_log = "account";
    private static final String IdXe_log = "idxe";
    private static final String TenXe_log = "tenxe";

    private static int version = 1;

    private SQLiteDatabase db;
    private Context context;
    private ContentValues values;
    public SQLLog(Context context) {
        super(context, DatabaseName, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Table_log = " CREATE TABLE " + Table_Log + " ( " +
                LogID_log + " integer primary key, " +
                Account_log + " TEXT, " +
                IdXe_log + " integer, " +
                TenXe_log + " TEXT)";
        db.execSQL(Create_Table_log);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Table_Log);
        onCreate(db);
    }
    //----------------------------------------------------------------------------------------------------------------
    public void AddLog(Log log) {
        db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(Account_log, log.getAccount());
        values.put(IdXe_log, log.getIdXe());
        values.put(TenXe_log, log.getTenXe());
        db.insert(Table_Log, null, values);
        db.close();
    }

    //------------Xem danh sách xe yêu thích---------------------
    public ArrayList<Log> getAllLogs(){
        ArrayList<Log> list = new ArrayList<>();
        String selectLog = "select * from " + Table_Log;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectLog, null);
        if(cursor.moveToFirst()){
            do{
                list.add(new Log(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3)));
            }while (cursor.moveToNext());
        }else{
            list = null;
        }
        cursor.close();
        db.close();
        return list;
    }
    //------------Xóa xe khỏi danh sách yêu thích---------------------
    public void DeleteLog(Log log) {
        db = this.getWritableDatabase();
        db.delete(Table_Log, LogID_log + " = ?",
                new String[] { String.valueOf(log.getLogID()) });
        db.close();
    }
}
