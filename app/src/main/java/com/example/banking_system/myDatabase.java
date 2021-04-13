package com.example.banking_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class myDatabase extends SQLiteOpenHelper {
    private Context context;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "bank_app.db";

    private static final String TABLE_NAME = "user_details";
    private static final String TABLE_NAME2 = "transfer_table";

    public myDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ TABLE_NAME + "(id  INTEGER  PRIMARY KEY, name TEXT, email varchar,balance DECIMAL)";
        db.execSQL(query);
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (1,'Raj','raj@gmail.com',10000) ");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (2,'Shyam','shyam@gmail.com',50000) ");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (3,'Reema','reema@gmail.com',7000) ");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (4,'Bharti','bhartii17@gmail.com',15004) ");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (5,'Renuka','renuKa@gail.com',16281) ");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (6,'Ali','ali@gmail.com',89765) ");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (7,'Ganesh','ganesh@gmail.com',20000) ");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (8,'Krishna','krishna@gmail.com',99923) ");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (9,'Isha','isha@gmail.com',70423) ");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (10,'Sayali','sayali@gmail.com',88378) ");
        String query2  = "CREATE TABLE "+ TABLE_NAME2 + "(date DATE,from_name TEXT,to_name TEXT,transfer_amount DECIMAL)";
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS  "+ TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS  "+ TABLE_NAME2);
        onCreate(db);
    }
    Cursor readAllData(){
        String query="SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor=null;
        if(db != null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }
    Cursor read_selected_userdata(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query="SELECT * FROM user_details WHERE name <>'"+ name +" ' " ;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
    public void updateAmount(String name, Double amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE user_details SET balance = " + amount + " where name ='" + name +"'");
    }
    public Cursor read_particular_data(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_details where name ='" + name + "'", null);
        return cursor;
    }
    public boolean insertTransferData(String date, String from_name, String to_name, Double amount){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("date", date);
            contentValues.put("from_name", from_name);
            contentValues.put("to_name", to_name);
            contentValues.put("transfer_amount", amount);
            long result=db.insert(TABLE_NAME2, null, contentValues);
            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
    public Cursor read_transfer_data(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" select * from transfer_table ", null);
        return cursor;
    }

}
