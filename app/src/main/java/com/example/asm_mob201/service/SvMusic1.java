package com.example.asm_mob201.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.asm_mob201.R;

import java.net.URISyntaxException;


public class SvMusic1 extends IntentService {

    MediaPlayer player = null;
    String Tag = "Play";
    int ID;

    Context context;

    public SvMusic1() {
        super("SvMusic1");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d(Tag, "Goi hàm onStrartCommandid service=" + startId);
        ID = startId;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this) {
            Log.d(Tag, "Gọi hàm onHandlenIntet - id" + ID);
            if (player == null) {
                player = MediaPlayer.create(this, R.raw.luonyeudoi);
                player.start();
            }
            Toast.makeText(this, "onHandleIntent", Toast.LENGTH_SHORT).show();
            Log.d(Tag, "onHandleIntent: " + player.toString());
            Toast.makeText(this, "" + player.toString(), Toast.LENGTH_SHORT).show();
            while (player.isPlaying()) {
                //đang chạy nhạc thì service chưa dừng
                try {
                    wait(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
            stopSelf(ID);
        }
    }

    @Override
    public void onDestroy() {
        Log.d(Tag, "Gọi hàm onDestroy - id = " + ID);
        if (player != null) {
            Log.d(Tag, "player != null ");
            player.stop();
            player.release();// giải phóng playler
            Toast.makeText(this, "Hết nhạc rồi", Toast.LENGTH_SHORT).show();
//            player = null;
        }

        super.onDestroy();
    }

}