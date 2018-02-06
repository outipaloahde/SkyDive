package com.example.outipupu.skydive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by outipupu on 16/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "jump.db";
    public static final String TABLE_NAME = "JUMP";
    public static final String COL_ID = "ID";
    public static final String COL_PLACE = "PLACE";
    public static final String COL_DATE = "DATE";
    public static final String COL_TYPE ="TYPE";
    public static final String COL_AIRCRAFT ="AIRCRAFT";
    public static final String COL_GEAR ="GEAR";
    public static final String COL_EXIT ="EXIT";
    public static final String COL_DEPLOY ="DEPLOY";
    public static final String COL_DESC ="DESC";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.print("tietokanta luotu");


        // taulujen luonti tietokantaan
        db.execSQL("create table " + TABLE_NAME + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_DATE + " TEXT NOT NULL, " +
                COL_PLACE + " TEXT NOT NULL, " +
                COL_AIRCRAFT + " TEXT, " +
                COL_GEAR + " TEXT, " +
                COL_TYPE + " TEXT, " +
                COL_EXIT + " INTEGER, " +
                COL_DEPLOY + " INTEGER, " +
                COL_DESC + " TEXT" +
                ");" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String place, String date, String aircraft, String gear, String type, String exit, String deploy, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PLACE, place);
        contentValues.put(COL_DATE, date);
        contentValues.put(COL_AIRCRAFT, aircraft);
        contentValues.put(COL_GEAR, gear);
        contentValues.put(COL_TYPE, type);
        contentValues.put(COL_EXIT, exit);
        contentValues.put(COL_DEPLOY, deploy);
        contentValues.put(COL_DESC,desc);
        long result  = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllJumps() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return result;

    }
    public boolean updateData(String id, String place, String date, String aircraft, String gear, String type, String exit, String deploy, String desc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, id);
        contentValues.put(COL_PLACE, place);
        contentValues.put(COL_DATE, date);
        contentValues.put(COL_AIRCRAFT, aircraft);
        contentValues.put(COL_GEAR, gear);
        contentValues.put(COL_TYPE, type);
        contentValues.put(COL_EXIT, exit);
        contentValues.put(COL_DEPLOY, deploy);
        contentValues.put(COL_DESC,desc);
        db.update(TABLE_NAME, contentValues, "ID = ? ", new String[] {id});
        return true;

    }
    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ? ", new String[] {id});
    }

}


