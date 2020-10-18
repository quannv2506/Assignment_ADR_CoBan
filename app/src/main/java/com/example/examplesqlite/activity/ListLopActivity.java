package com.example.examplesqlite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.examplesqlite.R;
import com.example.examplesqlite.adapter.ClassAdapter;
import com.example.examplesqlite.dao.ClassDAO;
import com.example.examplesqlite.model.MyClass;

import java.util.List;

public class ListLopActivity extends AppCompatActivity {

    ClassDAO dao;
    ListView lvLop;
    ClassAdapter adapter;
    List<MyClass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lop);

        lvLop = findViewById(R.id.lvLop);
        dao = new ClassDAO(this);
        list = dao.getAllClass();
        adapter = new ClassAdapter(list, ListLopActivity.this);
        lvLop.setAdapter(adapter);

//        lvLop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//                Toast.makeText(ListLopActivity.this, list.get(position).maLop, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
