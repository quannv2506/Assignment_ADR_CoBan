package com.example.examplesqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.examplesqlite.database.DatabaseHelper;
import com.example.examplesqlite.model.MyClass;

import java.util.ArrayList;
import java.util.List;

public class ClassDAO {
    private SQLiteDatabase sqLiteDatabase;
    private DatabaseHelper dbManager;

    public static final String TABLE_NAME = "tb_Lop";
    public static final String C_ID = "id";
    public static final String C_MaLop = "maLop";
    public static final String C_TenLop = "tenLop";
    public static final String SQL_Lop = "CREATE TABLE " + TABLE_NAME + " (" + C_ID + " integer primary key autoincrement, " + C_MaLop + " text, " + C_TenLop + " text);";

    public ClassDAO(Context context) {
        dbManager = new DatabaseHelper(context);
        sqLiteDatabase = dbManager.getWritableDatabase();
    }

    //insert
    public long insertClass(MyClass mMyClass) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_MaLop, mMyClass.getMaLop());
        contentValues.put(C_TenLop, mMyClass.getTenLop());
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
//        sqLiteDatabase.close();
        return result;
    }

    public List<MyClass> getAllClass() {
        List<MyClass> nguoiDungList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            MyClass mMyClass = new MyClass();
            mMyClass.setId(cursor.getInt(0));
            mMyClass.setMaLop(cursor.getString(1));
            mMyClass.setTenLop(cursor.getString(2));
            nguoiDungList.add(mMyClass);
            cursor.moveToNext();
        }
        cursor.close();
        return nguoiDungList;
    }

    public int updateClass(MyClass myClass) {
        ContentValues values = new ContentValues();
//        values.put(C_ID, myClass.id);
        values.put(C_MaLop, myClass.maLop);
        values.put(C_TenLop, myClass.tenLop);

        int result = sqLiteDatabase.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(myClass.getId())});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int deleteClass(int id) {
        int result = sqLiteDatabase.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});
        if (result == 0) {
            return -1;
        }
//        dbManager.close();
        return 1;
    }

    public List<String> getTenLop() {
        List<String> list = new ArrayList<>();
        sqLiteDatabase = dbManager.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
//                    String theLoai = cursor.getString(cursor.getColumnIndex("maLop")) +" - "+ cursor.getString(cursor.getColumnIndex("tenLop"));
                    String theLoai =cursor.getString(cursor.getColumnIndex("maLop"));
                    list.add(theLoai);
                }
            }
        }
        return list;
    }
}
