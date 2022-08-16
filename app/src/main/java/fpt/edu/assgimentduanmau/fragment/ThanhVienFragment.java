package fpt.edu.assgimentduanmau.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpt.edu.assgimentduanmau.R;
import fpt.edu.assgimentduanmau.adapter.ThanhVienAdapter;
import fpt.edu.assgimentduanmau.dao.ThanhVienDAO;
import fpt.edu.assgimentduanmau.model.ThanhVien;


public class ThanhVienFragment extends Fragment {
ListView lvThanhVien;
ArrayList<ThanhVien> list;
static ThanhVienDAO dao;
ThanhVienAdapter adapter;
ThanhVien item;
FloatingActionButton fab;
Dialog dialog;
EditText edMaTV,edTenTV,edNamSinh;
Button btnSave,btnCancel;
ImageView del;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        lvThanhVien = v.findViewById(R.id.lvThanhVien);

        fab = v.findViewById(R.id.fab);
        dao = new ThanhVienDAO(getActivity());
        capNhatLv();
fab.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        openDialog(getActivity(),0);
    }
});
lvThanhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        item = list.get(position);
        openDialog(getActivity(),1);//1update
        return false;
    }
});

        return v;
    }
    void capNhatLv(){
        list = (ArrayList<ThanhVien>)dao.getAll();
        adapter= new ThanhVienAdapter(getActivity(),this,list);
        lvThanhVien.setAdapter(adapter);

    }
   public void xoa(final String Id){
        //fung alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Có",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dao.delete(Id);
                        //cap nhat lv
                        capNhatLv();
                        dialog.cancel();

                    }
                });
        builder.setNegativeButton(
                "không",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        AlertDialog alert = builder.create();
        builder.show();
    }
    protected void  openDialog(final Context context,final int type){
        //custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.thanh_vien_dialog);
        edMaTV = dialog.findViewById(R.id.edMaTV);
        edTenTV = dialog.findViewById(R.id.edTenTV);
        edNamSinh = dialog.findViewById(R.id.edNamSinh);
        btnCancel = dialog.findViewById(R.id.btnCancelTV);
        btnSave = dialog.findViewById(R.id.btnSaveTV);
        //kiem tra type insert0 hay 1;
        edMaTV.setEnabled(false);
        if(type !=0){
            edMaTV.setText(String.valueOf(item.getMaTV()));
            edTenTV.setText(item.getHoTen());
            edNamSinh.setText(item.getNamSinh());
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new ThanhVien();
                item.setHoTen(edTenTV.getText().toString());
                item.setNamSinh(edNamSinh.getText().toString());
                if(validate()>0){
                    if(type==0){
if(dao.insert(item)>0){
    Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();

}else {
    Toast.makeText(context,"Thêm không thành công",Toast.LENGTH_SHORT).show();
}
                    }else {
                        //type=1(update)
                        item.setMaTV(Integer.parseInt(edMaTV.getText().toString()));
                        if(dao.update(item)>0){
                            Toast.makeText(context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Sửa không thành công",Toast.LENGTH_SHORT).show();
                        }

                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
dialog.show();
    }
        public  int validate(){
            int check = 1;
            if(edTenTV.getText().length()==0|| edNamSinh.getText().length()==0){
                Toast.makeText(getContext(),"Bạn pải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                check = -1;
            }
            return check;
        }
}