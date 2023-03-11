package com.example.asm_mob201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.asm_mob201.DAO.NguoiDungDAO;
import com.example.asm_mob201.service.kiemTraLoginSerivce;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity_Login extends AppCompatActivity {
    //    TextInputLayout
    TextInputEditText edUser, edPass;
    Button btnLogin, btnDangKy, skip;
    //
    IntentFilter intentFilter;
    NguoiDungDAO nguoiDungDAO;
    CheckBox chk;
    //
    String u;
    String p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDangKy = findViewById(R.id.btnDangKy);
        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPass);
        btnLogin = findViewById(R.id.btnLogin);
//        skip = findViewById(R.id.btn_Skip);
//        tvNext = findViewById(R.id.tvBoQua);
        chk = findViewById(R.id.chk);
//        skip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, Navigation.class);
//                startActivity(intent);
//            }
//        });
        //add broadcast\
        nguoiDungDAO = new NguoiDungDAO(this);
        intentFilter = new IntentFilter();
        intentFilter.addAction("kiemtraLogin");
        // lưu data user
        SharedPreferences sharedPreferences = getSharedPreferences("NAME_FILE", MODE_PRIVATE);
        String user = sharedPreferences.getString("USERNAME", "");
        String pass = sharedPreferences.getString("PASSWORD", "");
        Boolean rem = sharedPreferences.getBoolean("REMEMBER", false);
        //set data
        edUser.setText(user);
        edPass.setText(pass);
        chk.setChecked(rem);
        //

        //sự kiện nút dăng ký
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Login.this, DangKy.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                u = edUser.getText().toString();
                p = edPass.getText().toString();

                Intent intent = new Intent(MainActivity_Login.this, kiemTraLoginSerivce.class);
                Bundle bundle = new Bundle();
                bundle.putString("user", u);
                bundle.putString("pass", p);

                intent.putExtras(bundle);
                startService(intent);

//                Toast.makeText(MainActivity.this, "Login Thành Công", Toast.LENGTH_SHORT).show();
            }
        });

    }



    //save data
    public void remember(String u, String p, boolean statuss) {
        SharedPreferences sharedPreferences = getSharedPreferences("NAME_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!statuss) {
            editor.clear();
        } else {
            editor.putString("USERNAME", u);
            editor.putString("PASSWORD", p);
            editor.putBoolean("REMEMBER", statuss);
        }
        editor.commit();
    }
    //end save data


    //đăng ký br với intentFillter
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, intentFilter);
    }

    //tạo broadcast trong activity

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case "kiemtraLogin":
                    Bundle bundle = intent.getExtras();
                    boolean check = bundle.getBoolean("check");
                    if (check) {
                        String abc = edUser.getText().toString();
                        Intent intent1 = new Intent(MainActivity_Login.this , Navigation.class);
                        intent1.putExtra("user123" , abc);
                        Log.d("zzz" , abc);
                        Toast.makeText(context, "Login Thành Công", Toast.LENGTH_SHORT).show();
                        startActivity(intent1);
                        remember(u, p, chk.isChecked());
                    } else {
                        Toast.makeText(context, "Đằng Nhập Không Thành Công", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };
}