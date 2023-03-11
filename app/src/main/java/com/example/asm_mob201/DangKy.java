package com.example.asm_mob201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asm_mob201.DAO.NguoiDungDAO;
import com.example.asm_mob201.Model.NguoiDung;
import com.google.android.material.textfield.TextInputEditText;

public class DangKy extends AppCompatActivity {
    Button btnDangKy;

    TextInputEditText edUser, edTen, edDangKyPass, edNhapLai;
    NguoiDungDAO nguoiDungDAO;
    String u, n, np, rp;

    //

    IntentFilter intentFilter = new IntentFilter("hello");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        btnDangKy = findViewById(R.id.btnDangKyThanhCong);

        edUser = findViewById(R.id.edDkyUser);
        edTen = findViewById(R.id.edTen);
        edDangKyPass = findViewById(R.id.edDkyPass);
        edNhapLai = findViewById(R.id.edDkyNhapLai);
        nguoiDungDAO = new NguoiDungDAO(this);
        //

//        intentFilter = new IntentFilter();
//        intentFilter.addAction("kiemTraDangKy");

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                u = edUser.getText().toString();
                n = edTen.getText().toString();
                np = edDangKyPass.getText().toString();
                rp = edNhapLai.getText().toString();
                //
//                Intent intent = new Intent();
//                Bundle bundle = new Bundle();
//                bundle.putString("user", u);
//                bundle.putString("name", n);
//                bundle.putString("passOld", np);
//                intent.putExtras(bundle);
//                intent.setAction("hello");
//                sendBroadcast(intent);
                //
                if (u.equals("") || n.equals("")) {
                    Toast.makeText(DangKy.this, "DATA Rỗng", Toast.LENGTH_SHORT).show();
                    return;

                } else if (!np.equals(rp)) {
                    Toast.makeText(DangKy.this, "PassWord Không Giong Nhau", Toast.LENGTH_SHORT).show();
                }
                nguoiDungDAO = new NguoiDungDAO(DangKy.this);
                NguoiDung nguoiDung = new NguoiDung(u, np, n);
                if (nguoiDungDAO.inser(nguoiDung)) {
                    Toast.makeText(DangKy.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DangKy.this, Navigation.class);

                    startActivity(intent);

                }

//               else{
//                   if(nguoiDungDAO.inser(nguoiDung)){
//                       Toast.makeText(DangKy.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
//                       Intent intent = new Intent(DangKy.this , Navigation.class);
//                       startActivity(intent);
//                   }
//               }
            }
        });
    }



    public BroadcastReceiver brDangKy = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            switch (intent.getAction()) {
//                case "kiemTraDangKy":
//                    Bundle bundle = intent.getExtras();
//                    boolean check = bundle.getBoolean("check");
//                    if(check){
//                        startActivity(new Intent(DangKy.this , Navigation.class));
//                        Toast.makeText(context, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(context, "Đăng Ký khồn thành công", Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//                default:
//                    break;
//            }
            Bundle bundle = intent.getExtras();
            boolean check = bundle.getBoolean("check");
            if (check) {
                startActivity(new Intent(DangKy.this, MainActivity_Login.class));
            }
        }
    };
}