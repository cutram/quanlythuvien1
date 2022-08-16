package fpt.edu.assgimentduanmau.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.widget.AlertDialogLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fpt.edu.assgimentduanmau.R;
import fpt.edu.assgimentduanmau.adapter.PhieuMuonAdapter;
import fpt.edu.assgimentduanmau.adapter.SachSpinnerAdapter;
import fpt.edu.assgimentduanmau.adapter.ThanhViepSpinnerAdapter;
import fpt.edu.assgimentduanmau.dao.PhieuMuonDAO;
import fpt.edu.assgimentduanmau.dao.SachDAO;
import fpt.edu.assgimentduanmau.dao.ThanhVienDAO;
import fpt.edu.assgimentduanmau.model.PhieuMuon;
import fpt.edu.assgimentduanmau.model.Sach;
import fpt.edu.assgimentduanmau.model.ThanhVien;


public class PhieuMuonFragment extends Fragment {
ListView lvPhieuMuon;
ArrayList<PhieuMuon> list;
static PhieuMuonDAO dao;
PhieuMuonAdapter adapter;
PhieuMuon item;

FloatingActionButton fab;
Dialog dialog;
EditText edMaPM;
Spinner spTV, spSach;
TextView tvNgay,tvTienThue;
CheckBox chkTraSach;
Button btnSave,btnCancel;

ThanhViepSpinnerAdapter thanhViepSpinnerAdapter;
ArrayList<ThanhVien> listThanhVien;
ThanhVienDAO thanhVienDAO;
ThanhVien thanhVien;
int maThanhVien;

SachSpinnerAdapter sachSpinnerAdapter;
ArrayList<Sach> listSach;
SachDAO sachDAO;
Sach sach;
int maSach, tienThue;
int positionTV,positionSach;
    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
    lvPhieuMuon = v.findViewById(R.id.lvPhieuMuono);

    dao = new PhieuMuonDAO(getActivity());
        fab =v.findViewById(R.id.fab);
        tvTienThue = v.findViewById(R.id.tvGiaThue);
    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openDialog(getActivity(),0);
        }
    });
    lvPhieuMuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            item = list.get(position);
            openDialog(getActivity(), 1);//update
            return false;
        }
    });
    capNhapLv();
        return v;
    }
    void capNhapLv(){
        list = (ArrayList<PhieuMuon>) dao.getAll();
        adapter= new PhieuMuonAdapter(getActivity(),this,list);
        lvPhieuMuon.setAdapter(adapter);
    }
    protected void  openDialog(final Context context, final  int type){
//custom
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.phieu_muon_dialog);

        edMaPM = dialog.findViewById(R.id.edMaPM);
        spTV = dialog.findViewById(R.id.spMaTV);
        spSach = dialog.findViewById(R.id.spMaSach);
        tvNgay = dialog.findViewById(R.id.tvNgay);

        tvTienThue = dialog.findViewById(R.id.tvTienThue);
        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btnCancel = dialog.findViewById(R.id.btnCancelPM);
        btnSave = dialog.findViewById(R.id.btnSavePM);
        //stngay
        tvNgay.setText(": "+sdf.format(new Date()));
        edMaPM.setEnabled(false);
thanhVienDAO = new ThanhVienDAO(context);
listThanhVien = new ArrayList<ThanhVien>();
listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
thanhViepSpinnerAdapter = new ThanhViepSpinnerAdapter(context,listThanhVien);
spTV.setAdapter(thanhViepSpinnerAdapter);
spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        maThanhVien = listThanhVien.get(position).getMaTV();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});
sachDAO = new SachDAO(context);
listSach = new ArrayList<Sach>();
listSach = (ArrayList<Sach>) sachDAO.getAll();
sachSpinnerAdapter = new SachSpinnerAdapter(context,listSach);
spSach.setAdapter(sachSpinnerAdapter);
//lay ma loai sach
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach =listSach.get(position).getMaSach();
                tienThue =listSach.get(position).getGiaThue();
                tvTienThue.setText(": "+tienThue+" VND");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //sua
        if(type !=0){
            edMaPM.setText(String.valueOf(item.getMaPM()));

            for(int i =0; i < listThanhVien.size();i++)
                if(item.getMaTV() == (listThanhVien.get(i).getMaTV())){
                    positionTV = i;
                }
            spTV.setSelection(positionTV);
                for (int i =0; i<listSach.size();i++)
                    if(item.getMaSach()==(listSach.get(i).getMaSach())){
                        positionTV = i;
                    }
                spSach.setSelection(positionSach);
            tvNgay.setText("Ngày thuê: "+sdf.format(item.getNgay()));
            tvTienThue.setText("Tiền thuê: "+item.getTienThue());
                    if(item.getTraSach() == 1){
                        chkTraSach.setChecked(true);

                    }else {
                        chkTraSach.setChecked(false);
                    }
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
        item = new PhieuMuon();
        item.setMaSach(maSach);
        item.setMaTV(maThanhVien);
        item.setNgay(new Date());
        item.setTienThue(tienThue);
        if(chkTraSach.isChecked()){
            item.setTraSach(1);
        }else {
            item.setTraSach(0);
        }
        if(type == 0){
            if(dao.insert(item) > 0){
                Toast.makeText(context,"thêm thành công",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context,"thêm khôn thành công",Toast.LENGTH_SHORT).show();
            }
        }else {
            item.setMaPM(Integer.parseInt(edMaPM.getText().toString()));
            if(dao.update(item)>0){
                Toast.makeText(context,"sửa thành công",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context,"Sửa khôn thành công",Toast.LENGTH_SHORT).show();
            }
        }
        capNhapLv();
        dialog.dismiss();
    }
});
        dialog.show();
    }
    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Có",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
          dao.delete(Id);
          capNhapLv();
          dialog.cancel();
                    }
                }
        );

         builder.setNegativeButton(
                 "không",
                 new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int id) {
                         dialog.cancel();
                     }
                 }
         );

        AlertDialog alert = builder.create();
        builder.show();
    }

}