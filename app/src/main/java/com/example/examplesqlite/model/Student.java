package com.example.examplesqlite.model;

public class Student {
    public String maLop;
    public int maSv;
    public String tenSv;
    public String ngaySinh;

    public Student(String maLop, int maSv, String tenSv, String ngaySinh) {
        this.maLop = maLop;
        this.maSv = maSv;
        this.tenSv = tenSv;
        this.ngaySinh = ngaySinh;
    }

    public Student(int maSv, String tenSv, String ngaySinh) {
        this.maSv = maSv;
        this.tenSv = tenSv;
        this.ngaySinh = ngaySinh;
    }

    public Student(String maLop, String tenSv, String ngaySinh) {
        this.maLop = maLop;
        this.tenSv = tenSv;
        this.ngaySinh = ngaySinh;
    }

    public Student() {
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public int getMaSv() {
        return maSv;
    }

    public void setMaSv(int maSv) {
        this.maSv = maSv;
    }

    public String getTenSv() {
        return tenSv;
    }

    public void setTenSv(String tenSv) {
        this.tenSv = tenSv;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }


}
