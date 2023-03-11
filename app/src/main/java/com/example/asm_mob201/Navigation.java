package com.example.asm_mob201;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob201.DAO.NguoiDungDAO;
import com.example.asm_mob201.Fragment.ChonAnhActivity;
import com.example.asm_mob201.Fragment.FrmChangePass;
import com.example.asm_mob201.Fragment.FrmTinTuc;
import com.example.asm_mob201.Fragment.MediaMusic;
import com.example.asm_mob201.Model.NguoiDung;
import com.google.android.material.navigation.NavigationView;

public class Navigation extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    View view;
    TextView tvUser;
    MainActivity_Login mainActivity;

    IntentFilter intentFilter;
    NguoiDungDAO nguoiDungDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar1);
        //
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        //
        intentFilter = new IntentFilter();
        intentFilter.addAction("kiemtraLogin");
        //
        nguoiDungDAO = new NguoiDungDAO(this);


        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_list_24);
        actionBar.setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.view);
        view = navigationView.getHeaderView(0);
        tvUser = view.findViewById(R.id.tvWelcome);
        String user123 = getIntent().getStringExtra("user123");
        NguoiDung nguoiDung = nguoiDungDAO.getID(user123);
        tvUser.setText("UserName: " + nguoiDung.getName());

//        tvUser.setText("Welcome");
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()) {
                    case R.id.nav_music:
                        Intent intent = new Intent(Navigation.this, MediaMusic.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_ava:
                        setTitle("Chọn Ảnh đại diện");
                        Intent chonAnh = new Intent(Navigation.this, ChonAnhActivity.class);
                        startActivity(chonAnh);
                        break;

                    case R.id.nav_list_music:
                        setTitle("Danh Sách Bài Hát");
                        SharedPreferences sharedPreferences = getSharedPreferences("NAME_FILE", MODE_PRIVATE);
                        String user = sharedPreferences.getString("USERNAME", "");
                        if (user.equals("USERNAME")) {
                            Intent intent1 = new Intent(Navigation.this, MainActivity_Login.class);
                            startActivity(intent1);
                            Toast.makeText(Navigation.this, "Bạn Cần Đăng Nhập Để Xem Danh Sách Bài Hát", Toast.LENGTH_SHORT).show();
                        } else {

                        }
                        break;
                    case R.id.nav_tt:
                        setTitle("Danh Sách Tin Tức");
                        Fragment FrmTm = new FrmTinTuc();
                        manager.beginTransaction().replace(R.id.fragment, FrmTm).commit();
                        break;
                    case R.id.nav_mk:
                        setTitle("Đổi Mật Khẩu");
                        Fragment fragment = new FrmChangePass();
                        manager.beginTransaction().replace(R.id.fragment, fragment).commit();
                        break;
                    case R.id.nav_logout:
                        Intent intent12 = new Intent(Navigation.this, MainActivity_Login.class);
                        startActivity(intent12);
                        finish();
                }

                drawerLayout.close();

                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, intentFilter);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case "kiemtraLogin":
                    Bundle bundle = intent.getExtras();
                    boolean check = bundle.getBoolean("check");
                    if (check) {
//                        startActivity(new Intent(MainActivity.this, Navigation.class));
                        Toast.makeText(context, "Login Thành Công", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(context, "Đằng Nhập Không Thành Công", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}