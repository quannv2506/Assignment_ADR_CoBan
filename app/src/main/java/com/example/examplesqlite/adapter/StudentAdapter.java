package com.example.examplesqlite.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examplesqlite.R;
import com.example.examplesqlite.dao.ClassDAO;
import com.example.examplesqlite.dao.StudentDAO;
import com.example.examplesqlite.model.MyClass;
import com.example.examplesqlite.model.Student;

import java.util.List;

public class StudentAdapter extends BaseAdapter {

    List<Student> studentList;
    Context context;
    StudentDAO studentDAO;

    public StudentAdapter(List<Student> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.item_student, parent, false);

        TextView tvStt = view.findViewById(R.id.tvStt);
        TextView tvTen = view.findViewById(R.id.tvMaLop);
        TextView tvNgaySinh = view.findViewById(R.id.tvTenLop);
        ImageView imgDelete = view.findViewById(R.id.imgDelete);

        final Student student = studentList.get(position);

        tvStt.setText(position + 1 + "");
        tvTen.setText(student.getTenSv());
        tvNgaySinh.setText(student.getNgaySinh());

        if ((position + 1) % 2 == 1){
            view.setBackgroundColor(Color.parseColor("#66CCCC"));
        }

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentDAO = new StudentDAO(context);
                long result = studentDAO.deleteStudent(student.getMaSv());
                if (result > 0) {
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    studentList.remove(student);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                final View alert = LayoutInflater.from(context).inflate(R.layout.update_student, null);

                builder.setView(alert);

                final EditText edtTen = alert.findViewById(R.id.edtTen);
                final EditText edtTenLop = alert.findViewById(R.id.edtNgaySinh);
                Button btnCancel = alert.findViewById(R.id.btnCancel);
                Button btnUpdate = alert.findViewById(R.id.btnUpdateStudent);
                final int id;
                final String maLop;

                edtTen.setText(student.getTenSv());
                edtTenLop.setText(student.getNgaySinh());
                id = student.getMaSv();
                maLop = student.getMaLop();

                final AlertDialog dialog = builder.create();
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        studentDAO = new StudentDAO(context);
                        Student myStudent = new Student(maLop, id, edtTen.getText().toString(), edtTenLop.getText().toString());
                        long result = studentDAO.updateStudent(myStudent);
                        if (result > 0) {
                            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            studentList = studentDAO.getAllStudent();
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "Cập nhât thất bại", Toast.LENGTH_SHORT).show();
                        }
                        edtTen.setText("");
                        edtTenLop.setText("");
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        return view;
    }
}
