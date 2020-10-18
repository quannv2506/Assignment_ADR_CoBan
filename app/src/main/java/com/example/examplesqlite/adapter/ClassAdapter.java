package com.example.examplesqlite.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examplesqlite.R;
import com.example.examplesqlite.dao.ClassDAO;
import com.example.examplesqlite.model.MyClass;

import java.util.List;

public class ClassAdapter extends BaseAdapter {

    List<MyClass> list;
    ClassDAO classDAO;
    Context context;

    public ClassAdapter(List<MyClass> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.item_lop, viewGroup, false);

        TextView tvStt = view.findViewById(R.id.tvStt);
        TextView tvMaLop = view.findViewById(R.id.tvMaLop);
        TextView tvTenLop = view.findViewById(R.id.tvTenLop);

        final MyClass mMyClass = list.get(i);

//        tvStt.setText(mMyClass.getId() + "");
        tvStt.setText(i + 1 + "");
        tvMaLop.setText(mMyClass.getMaLop());
        tvTenLop.setText(mMyClass.getTenLop());

        if ((i + 1) % 2 == 1){
            view.setBackgroundColor(Color.parseColor("#66CCCC"));
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                final View alert = LayoutInflater.from(context).inflate(R.layout.insert_class_dialog, null);

                builder.setView(alert);

                final EditText edtMaLop = alert.findViewById(R.id.edtTen);
                final EditText edtTenLop = alert.findViewById(R.id.edtNgaySinh);
                Button btnClear = alert.findViewById(R.id.btnCancel);
                Button btnSaveClass = alert.findViewById(R.id.btnUpdateStudent);
                TextView tvTitle = alert.findViewById(R.id.tvTitle);
                final int id;

                tvTitle.setText("Cập nhật lớp");
                edtMaLop.setText(mMyClass.getMaLop());
                edtTenLop.setText(mMyClass.getTenLop());
                btnClear.setText("Hủy");
                btnSaveClass.setText("Cập nhật");
                id = mMyClass.getId();

                final AlertDialog dialog = builder.create();
                btnClear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                btnSaveClass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        classDAO = new ClassDAO(context);
                        MyClass mMyClass = new MyClass(id, edtMaLop.getText().toString(), edtTenLop.getText().toString());
//                        mMyClass.maLop = edtMaLop.getText().toString().trim();
//                        mMyClass.tenLop = edtTenLop.getText().toString().trim();
                        long result = classDAO.updateClass(mMyClass);
                        if (result > 0) {
                            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            list = classDAO.getAllClass();
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "Cập nhât thất bại", Toast.LENGTH_SHORT).show();
                        }
                        edtMaLop.setText("");
                        edtTenLop.setText("");
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                // set Message là phương thức thiết lập câu thông báo
                builder.setMessage("Bạn có muốn xóa không ?")
                        // positiveButton là nút thuận : đặt là OK
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                classDAO = new ClassDAO(context);
                                long result = classDAO.deleteClass(mMyClass.getId());
                                if (result > 0) {
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                }
                                list.remove(mMyClass);
                                notifyDataSetChanged();

                                dialog.dismiss();
                            }
                        })
                        // ngược lại negative là nút nghịch : đặt là cancel
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // tạo dialog và hiển thị
                builder.create().show();
                return false;
            }
        });
        return view;
    }
}
