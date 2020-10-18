package com.example.examplesqlite.model;

public class MyClass {
    public int id;
    public String maLop;
    public String tenLop;

    public MyClass(String maLop, String tenLop) {
        this.maLop = maLop;
        this.tenLop = tenLop;
    }

    public MyClass(int id, String maLop, String tenLop) {
        this.id = id;
        this.maLop = maLop;
        this.tenLop = tenLop;
    }

    public MyClass() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
}
