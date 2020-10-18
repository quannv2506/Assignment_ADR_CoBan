package com.example.examplesqlite.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.examplesqlite.dao.ClassDAO;
import com.example.examplesqlite.dao.StudentDAO;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbManager2";
    private static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ClassDAO.SQL_Lop);
        sqLiteDatabase.execSQL(StudentDAO.SQL_SINHVIEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ClassDAO.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StudentDAO.TABLE_NAME);

    }
}
