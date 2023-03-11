package com.example.asm_mob201.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asm_mob201.R;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class MediaMusic extends AppCompatActivity {
    Button btnStop;
    ListView lv;
    MediaPlayer mediaPlayer;
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<Uri> uriList = new ArrayList<>(); // play

    ComponentName sv = null;


    public void getListRaw() {
        Field[] fields = R.raw.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            nameList.add(fields[i].getName()); // thêm tên file nhạc vào list
            Uri uri1 = getRawUri(fields[i].getName());
            uriList.add(uri1);
        }
    }

    public Uri getRawUri(String fileName) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                File.pathSeparator + File.separator + File.separator +
                getPackageName() + "/raw/" + fileName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_music);
        btnStop = findViewById(R.id.btnMussic);
        lv = findViewById(R.id.lv);
        //
        getListRaw();// lấy data từ raw
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, nameList);
        lv.setAdapter(adapter);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MediaMusic.this, "Pausing sound", Toast.LENGTH_SHORT).show();
                mediaPlayer.stop();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    mediaPlayer.reset();
                } catch (Exception e) {

                }
                String name = nameList.get(i);// lấy tên
                Uri uri2 = getRawUri(name);

                mediaPlayer = MediaPlayer.create(MediaMusic.this, uri2);
                Toast.makeText(MediaMusic.this, "Playing Sound", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
                btnStop.setEnabled(true);
            }
        });
    }
}
//    void PlayOrStopMusic(){
//        // bấm 1 lần thì chạy sau đó chuyển nút bấm về stop
//        // bấm lại thì stop
//
//        if (myMusicPlayer == null) {
//            // chưa chạy
//            myMusicPlayer = MediaPlayer.create(this, uri);
//
//            int s = myMusicPlayer.getDuration();
//
//            TextView tvTotal = findViewById(R.id.totalTime);
//
//            tvTotal.setText(formatDuration(s));
//            myMusicPlayer.start();
//
//            runnable.run();
//            btnPlayStop.setText("Stop");
//            btnPause.setEnabled(true);
//
//            myMusicPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mediaPlayer) {
//
//                    myMusicPlayer.release();
//                    myMusicPlayer = null;
//                    btnPlayStop.setText("Play");
//                    btnPause.setEnabled(false);
//
//                }
//            });
//
//        } else {
//            // media khác nulll
//
//            if ( myMusicPlayer.isPlaying()) {
//                myMusicPlayer.stop();
//                myMusicPlayer.release();
//                myMusicPlayer = null;
//                btnPlayStop.setText("Play");
//                btnPause.setEnabled(false);
//
//            } else {
//                // media đang dừng
//                try {
//                    if (myMusicPlayer.isLooping())
//                        myMusicPlayer.prepare();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                myMusicPlayer.start();
//
//                btnPlayStop.setText("Stop");
//                btnPause.setEnabled(true);
//
//            }
//
//        }
//    }