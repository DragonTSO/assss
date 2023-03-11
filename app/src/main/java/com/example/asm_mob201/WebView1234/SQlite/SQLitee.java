package com.example.asm_mob201.WebView1234.SQlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLitee extends SQLiteOpenHelper {
    public SQLitee( Context context) {
        super(context, "QLUD", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String ten = "CREATE TABLE NGUOIDUNG(id integer primary key , username text , password text , name text)";
        sqLiteDatabase.execSQL(ten);
        //test database
        String ngDung = "INSERT INTO NGUOIDUNG VALUES(1,'ngdung1','123' , 'Hello')," +
                "(2,'minhtri','123abc123' , 'Helllo1')";
        sqLiteDatabase.execSQL(ngDung);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       if(i != i1){
           sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
           onCreate(sqLiteDatabase);
       }
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
//        onCreate(sqLiteDatabase);
    }
}
