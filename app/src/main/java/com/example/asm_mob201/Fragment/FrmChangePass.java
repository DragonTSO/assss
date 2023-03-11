package com.example.asm_mob201.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.asm_mob201.DAO.NguoiDungDAO;
import com.example.asm_mob201.Model.NguoiDung;
import com.example.asm_mob201.R;
import com.google.android.material.textfield.TextInputEditText;


public class FrmChangePass extends Fragment {
    Button btnSave, btnCancle;
    TextInputEditText edUser, edName, edNewPass, edOldPass;
    NguoiDungDAO nguoiDungDAO;

    public FrmChangePass() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frm_change_pass, container, false);
        btnSave = view.findViewById(R.id.btnDoi);
        btnCancle = view.findViewById(R.id.btnHuyDoi);
//        edUser = view.findViewById(R.id.edUserChage);
//        edName = view.findViewById(R.id.edName);
        edNewPass = view.findViewById(R.id.edPassNew);
        edOldPass = view.findViewById(R.id.edPassOld);
        nguoiDungDAO = new NguoiDungDAO(getContext());
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                edName.setText("");
//                edUser.setText("");
                edNewPass.setText("");
                edOldPass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("NAME_FILE", Context.MODE_PRIVATE);
                String user = sharedPreferences.getString("USERNAME", "");
                if (validate() > 0) {
                    NguoiDung nguoiDung = new NguoiDung();
                    nguoiDung = nguoiDungDAO.getID(user);
                    Log.d("zzz" , "USER: " + user);
                    nguoiDung.setPass(edNewPass.getText().toString());
//                    String name = edName.getText().toString();
//                    String passOld = edOldPass.getText().toString();
//                    String passNew = edNewPass.getText().toString();
//                    NguoiDung nguoiDung = new NguoiDung(user, passOld, name);
                    if (nguoiDungDAO.update(nguoiDung)) {
                        Toast.makeText(getContext(), "Update Thành Công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        return view;
    }

    public int validate() {
        int check = 1;
        if (edOldPass.getText().length() == 0 || edNewPass.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn Phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edNewPass.getText().toString();
            String passOld = edOldPass.getText().toString();
            if (!pass.equals(passOld)) {
                Toast.makeText(getContext(), "Mật Khẩu Bạn đặt không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}