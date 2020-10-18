package com.example.examplesqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.examplesqlite.database.DatabaseHelper;
import com.example.examplesqlite.model.MyClass;
import com.example.examplesqlite.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private SQLiteDatabase sqLiteDatabase;
    private DatabaseHelper dbManager;

    public static final String TABLE_NAME = "tb_SinhVien";
    public static final String C_MaLop = "maLop";
    public static final String C_MASV = "maSV";
    public static final String C_TENSV = "tenSV";
    public static final String C_NGAYSINH = "ngaySinh";
    public static final String SQL_SINHVIEN = "CREATE TABLE " + TABLE_NAME + " (maSV integer primary key autoincrement, maLop text, tenSV text, ngaySinh text);";


    public StudentDAO(Context context) {
        dbManager = new DatabaseHelper(context);
        sqLiteDatabase = dbManager.getWritableDatabase();
    }

    //insert
    public long insertStudent(Student student) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_MaLop, student.getMaLop());
        contentValues.put(C_TENSV, student.getTenSv());
        contentValues.put(C_NGAYSINH, student.getNgaySinh());
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
//        sqLiteDatabase.close();
        return result;
    }
    public List<Student> getAllStudent() {
        List<Student> studentList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Student student = new Student();
            student.setMaSv(cursor.getInt(0));
            student.setMaLop(cursor.getString(1));
            student.setTenSv(cursor.getString(2));
            student.setNgaySinh(cursor.getString(3));
            studentList.add(student);
            cursor.moveToNext();
        }
        cursor.close();
        return studentList;
    }

    public long updateStudent(Student student) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_MaLop, student.maLop);
        contentValues.put(C_TENSV, student.tenSv);
        contentValues.put(C_NGAYSINH, student.ngaySinh);
        int result = sqLiteDatabase.update(TABLE_NAME, contentValues, "maSV=?", new String[]{String.valueOf(student.maSv)});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int deleteStudent(int maSV) {
        int result = sqLiteDatabase.delete(TABLE_NAME, "maSV=?", new String[]{String.valueOf(maSV)});
        if (result == 0) {
            return -1;
        }
//        dbManager.close();
        return 1;
    }
}
