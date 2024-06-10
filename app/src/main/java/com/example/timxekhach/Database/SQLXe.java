package com.example.timxekhach.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.timxekhach.DBXe.Xe;

import java.util.ArrayList;

public class SQLXe extends SQLiteOpenHelper {

    private static final String DatabaseName = "databasexe";
    private static final String TableXe = "tablexe";
    private static final String IdXe = "idxe";
    private static final String TenXe = "tenxe";
    private static final String BienSo = "bienso";
    private static final String LoaiXe = "loaixe";
    private static final String MauXe = "mauxe";
    private static final String Sdt = "sdt";
    private static final String GiaVe = "giave";
    private static final String Tuyen = "tuyen";
    private static final String ThoiGian = "thoigian";
    private static final String LichTrinh = "lichtrinh";
    private static final String AnhXe = "anhxe";
    private static final String SoGhe = "soghe";

    private static int version = 1;

    private SQLiteDatabase db;
    private Context context;
    private ContentValues values;
    public SQLXe(Context context) {
        super(context, DatabaseName, null, version);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Table_book = " CREATE TABLE " + TableXe + " ( " +
                IdXe + " integer primary key, " +
                TenXe + " TEXT, " +
                BienSo + " TEXT, " +
                LoaiXe + " TEXT, " +
                MauXe + " TEXT, " +
                Sdt + " TEXT, " +
                GiaVe + " TEXT, " +
                Tuyen + " TEXT, " +
                ThoiGian + " TEXT, " +
                LichTrinh + " TEXT, " +
                AnhXe + " integer, " +
                SoGhe + " integer)";
        db.execSQL(Create_Table_book);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TableXe);
        onCreate(db);
    }

    public void AddXe(Xe xe) {
        db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(TenXe, xe.getTenXe());
        values.put(BienSo, xe.getBienSo());
        values.put(LoaiXe, xe.getLoaiXe());
        values.put(MauXe, xe.getMauXe());
        values.put(Sdt, xe.getSdt());
        values.put(GiaVe, xe.getGiaVe());
        values.put(Tuyen, xe.getTuyen());
        values.put(ThoiGian, xe.getThoiGian());
        values.put(LichTrinh, xe.getLichTrinh());
        values.put(AnhXe, xe.getAnhXe());
        values.put(SoGhe, xe.getSoGhe());
        db.insert(TableXe, null, values);
        db.close();
    }

    public Xe getXe(int idxe){
        db = this.getWritableDatabase();
        Cursor cursor = db.query(TableXe, new String[]{IdXe, TenXe, BienSo, LoaiXe, MauXe, Sdt, GiaVe, Tuyen, ThoiGian, LichTrinh, AnhXe, SoGhe},
                IdXe + "=?", new String[]{String.valueOf(idxe)}, null, null, null,null);
        Xe s = new Xe();
        if(cursor.moveToFirst()){
            s.setIdXe(cursor.getInt(0));
            s.setTenXe(cursor.getString(1));
            s.setBienSo(cursor.getString(2));
            s.setLoaiXe(cursor.getString(3));
            s.setMauXe(cursor.getString(4));
            s.setSdt(cursor.getString(5));
            s.setGiaVe(cursor.getString(6));
            s.setTuyen(cursor.getString(7));
            s.setThoiGian(cursor.getString(8));
            s.setLichTrinh(cursor.getString(9));
            s.setAnhXe(cursor.getInt(10));
            s.setSoGhe(cursor.getInt(11));
        }else{
            s = null;
        }
        cursor.close();
        db.close();
        return s;
    }

    public ArrayList<Xe> getAllXe(){
        ArrayList<Xe> list = new ArrayList<>();
        String selectxe = "select * from " + TableXe;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectxe, null);
        if(cursor.moveToFirst()){
            do{
                list.add(new Xe(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9), cursor.getInt(10),cursor.getInt(11) ));
            }while (cursor.moveToNext());
        }else{
            list = null;
        }
        cursor.close();
        db.close();
        return list;
    }

    public void DeleteXe(Xe xe) {
        db = this.getWritableDatabase();
        db.delete(TableXe, IdXe + " = ?",
                new String[] { String.valueOf(xe.getIdXe()) });
        db.close();
    }

}

