package com.example.asm_mob201.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.asm_mob201.DAO.NguoiDungDAO;
import com.example.asm_mob201.Model.NguoiDung;

public class KiemTraDangKyService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        String user = bundle.getString("user");
        String name = bundle.getString("name");
        String passDky = bundle.getString("passOld");

//        String passNhap = bundle.getString("passNew");
        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(this);
        NguoiDung nguoiDung = new NguoiDung(user , name , passDky );
        boolean check = nguoiDungDAO.inser(nguoiDung);

        //gửi data
        Intent intentBR = new Intent();
        Bundle bundleBR = new Bundle();
        bundleBR.putBoolean("check", check);
        intentBR.putExtras(bundleBR);
        intentBR.setAction("kiemTraDangKy");
        sendBroadcast(intentBR);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
