package com.example.examplesqlite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examplesqlite.R;
import com.example.examplesqlite.dao.ClassDAO;
import com.example.examplesqlite.model.MyClass;

public class MainActivity extends AppCompatActivity {

    private ClassDAO classDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnInsertClass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        findViewById(R.id.btnShowClass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListLopActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnManageStudent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                startActivity(intent);
            }
        });

    }

    //show dialog them lop
    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        View alert = LayoutInflater.from(MainActivity.this).inflate(R.layout.insert_class_dialog, null);

        builder.setView(alert);

        final EditText edtMaLop = alert.findViewById(R.id.edtTen);
        final EditText edtTenLop = alert.findViewById(R.id.edtNgaySinh);
        Button btnClear = alert.findViewById(R.id.btnCancel);
        Button btnSaveClass = alert.findViewById(R.id.btnUpdateStudent);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtMaLop.setText("");
                edtTenLop.setText("");
            }
        });

        btnSaveClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classDAO = new ClassDAO(MainActivity.this);
                MyClass mMyClass = new MyClass();
                mMyClass.maLop = edtMaLop.getText().toString().trim();
                mMyClass.tenLop = edtTenLop.getText().toString().trim();
                long result = classDAO.insertClass(mMyClass);
                if (result > 0) {
                    Toast.makeText(MainActivity.this, "Thêm lớp thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Thêm lớp thất bại", Toast.LENGTH_SHORT).show();
                }
                edtMaLop.setText("");
                edtTenLop.setText("");
            }
        });

        builder.create().show();

    }
}
