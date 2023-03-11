package com.example.asm_mob201.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_mob201.Model.NguoiDung;
import com.example.asm_mob201.WebView1234.SQlite.SQLitee;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    SQLitee sqLitee;
    SQLiteDatabase sqLiteDatabase;

    public NguoiDungDAO(Context context) {
        sqLitee = new SQLitee(context);
        sqLiteDatabase = sqLitee.getWritableDatabase();
    }

    //Kiểm tra thông tin đăng nhập
    public boolean kiemtra(String user, String pass) {
        SQLiteDatabase sqLiteDatabase = sqLitee.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE username = ? AND password = ?", new String[]{user, pass});
        if (cursor.getCount() != 0) {// tìm thấy dữ liệu
            return true;
        }
        return false;
    }



    public boolean inser(NguoiDung nguoiDung) {
        ContentValues values = new ContentValues();
        values.put("username", nguoiDung.getUser());
        values.put("password", nguoiDung.getPass());
        values.put("name", nguoiDung.getName());
        long check = sqLiteDatabase.insert("NGUOIDUNG", null, values);
        if (check == -1) {
            return false;
        }
        return true;
    }

    //
//    public boolean Them(String user, String name, String pass) {
//        ContentValues values = new ContentValues();
//        values.put("username", user);
//        values.put("password", name);
//        values.put("name", pass);
//        long check = sqLiteDatabase.insert("NGUOIDUNG", null, values);
//        if (check == -1) {
//            return false;
//        }
//        return true;
//    }

    public boolean update(NguoiDung nguoiDung) {
        ContentValues values = new ContentValues();
        values.put("username", nguoiDung.getUser());
        values.put("password", nguoiDung.getPass());
        values.put("name", nguoiDung.getName());
        long check = sqLiteDatabase.update("NGUOIDUNG", values, "username = ?" , new String[]{String.valueOf(nguoiDung.getUser())});
        if (check == -1) {
            return false;
        }
        return true;
    }

    //
    @SuppressLint("Range")
    public ArrayList<NguoiDung> getData(String sql, String... selectionArgs) {
        ArrayList<NguoiDung> list = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            NguoiDung nguoiDung = new NguoiDung();
            nguoiDung.setUser(c.getString(c.getColumnIndex("username")));
            nguoiDung.setName(c.getString(c.getColumnIndex("name")));
            nguoiDung.setPass(c.getString(c.getColumnIndex("password")));
//            Log.i("thuThu", thuThu.toString());
            list.add(nguoiDung);
        }
        return list;
    }
    public ArrayList<NguoiDung> getAll() {
        String sql = "SELECT * FROM NGUOIDUNG";
        return getData(sql);
    }

    //get id
    public NguoiDung getID(String id) {
        String sql = "SELECT * FROM NGUOIDUNG Where username=?";
        List<NguoiDung> list = getData(sql, id);
        return list.get(0);
    }

}