package com.example.examplesqlite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.examplesqlite.R;
import com.example.examplesqlite.adapter.StudentAdapter;
import com.example.examplesqlite.dao.ClassDAO;
import com.example.examplesqlite.dao.StudentDAO;
import com.example.examplesqlite.model.MyClass;
import com.example.examplesqlite.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {

    Spinner spnClass;
    EditText edtName, edtBirthday;
    Button btnAddStudent;
    ListView lvListStudent;
    List<String> listMyClass;
    String maLop;
    List<Student> studentList;
    StudentAdapter studentAdapter;
    StudentDAO studentDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        spnClass = findViewById(R.id.spnClass);
        edtName = findViewById(R.id.edtName);
        edtBirthday = findViewById(R.id.edtBirthday);
        btnAddStudent = findViewById(R.id.btnAddStudent);
        lvListStudent = findViewById(R.id.lvListStudent);

        final StudentDAO studentDAO = new StudentDAO(this);

        //spinner
        listMyClass = new ArrayList<>();
        ClassDAO classDAO = new ClassDAO(StudentActivity.this);
        listMyClass = classDAO.getTenLop();
        ArrayAdapter<String> spAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listMyClass);
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnClass.setAdapter(spAdapter);
        spnClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maLop = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //list student
        studentList = studentDAO.getAllStudent();
        studentAdapter = new StudentAdapter(studentList, this);
        lvListStudent.setAdapter(studentAdapter);

        //them
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.tenSv = edtName.getText().toString().trim();
                student.maLop = maLop;
                student.ngaySinh = edtBirthday.getText().toString();
                long result = studentDAO.insertStudent(student);
                if (result > 0) {
                    Toast.makeText(StudentActivity.this, "Thêm sinh viên thành công", Toast.LENGTH_SHORT).show();
                    studentList.add(student);
                    studentAdapter.notifyDataSetChanged();
                    edtName.setText("");
                    edtBirthday.setText("");
                    Log.e("TAG", student.getMaLop());
                } else {
                    Toast.makeText(StudentActivity.this, "Thêm sinh viên thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}