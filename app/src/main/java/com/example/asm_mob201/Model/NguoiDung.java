package com.example.asm_mob201.Model;

public class NguoiDung {
    int id;
    String user;
    String pass;
    String name;



    public NguoiDung() {
    }
    public NguoiDung(String user , String pass , String name){
        this.user = user;
        this.pass = pass;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
