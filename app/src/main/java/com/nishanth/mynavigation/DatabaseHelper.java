package com.nishanth.mynavigation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nishanth on 02-09-2017.
 */

public class DatabaseHelper  extends SQLiteOpenHelper{
    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "people_info";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    public DatabaseHelper(Context context) {
        super(context,TABLE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE "+ TABLE_NAME + "( ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COL2 + " TEXT )";
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean addData(String data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2,data);
        long result = db.insert(TABLE_NAME,null,cv);
        if(result!=-1)
            return true;
        else
            return false;

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data= db.rawQuery(query,null);
        return data;
    }
    public Cursor getItemId(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " +COL1 + " FROM " + TABLE_NAME + " WHERE "+ COL2 + " = '" + name+ "'";
         Cursor data = db.rawQuery(query,null);
        return data;


    }
    public void  updateData(int id,String oldname,String newname){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2  + " = '" + newname + "' WHERE " + COL1 + " = '"
                + id + "' AND " + COL2 + " = '" + oldname+"'";
        db.execSQL(query);
    }


    public void deleteData(String name,int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL1  + " = '" + id + "' AND "+ COL2 + " = '"
                 + name + "'";
        db.execSQL(query);
    }
}
