package fpt.edu.assgimentduanmau.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fpt.edu.assgimentduanmau.R;
import fpt.edu.assgimentduanmau.dao.SachDAO;
import fpt.edu.assgimentduanmau.dao.ThanhVienDAO;
import fpt.edu.assgimentduanmau.fragment.PhieuMuonFragment;
import fpt.edu.assgimentduanmau.model.PhieuMuon;
import fpt.edu.assgimentduanmau.model.Sach;
import fpt.edu.assgimentduanmau.model.ThanhVien;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
private Context context;
PhieuMuonFragment fragment;
private ArrayList<PhieuMuon> lists;
TextView tvMaPM,tvTenTV,tvTenSach,tvTienThue,tvNgay,tvTraSach;
ImageView imageDel;
SachDAO sachDAO;
ThanhVienDAO thanhVienDAO;
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
LinearLayout lvpm;
ImageView imgPhieuMuon;
public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment fragment, ArrayList<PhieuMuon> lists) {

        super(context, 0,lists);
        this.context= context;
        this.lists=lists;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View v = convertView;
    if(v==null){
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.phie_muon_item,null);

    }


    final PhieuMuon item = lists.get(position);

    if(item != null){

        imgPhieuMuon=v.findViewById(R.id.imgPhieuMuon);
        Animation pm = AnimationUtils.loadAnimation(context, R.anim.transition1);
        imgPhieuMuon.setAnimation(pm);


        Log.i("//==PhieuMuon===="," "+ item.toString());
        tvMaPM = v.findViewById(R.id.tvMaPM);
        tvMaPM.setText("Mã phiếu mượn: "+item.getMaPM());


        lvpm = v.findViewById(R.id.cns_pm);
        Animation aa = AnimationUtils.loadAnimation(context, R.anim.transition);
        lvpm.setAnimation(aa);

        sachDAO = new SachDAO(context);
        Sach sach = sachDAO.getID(String.valueOf(item.getMaSach()));
        tvTenSach = v.findViewById(R.id.tvTenSach);
        tvTenSach.setText("Tên sách: "+sach.getTenSach());
        thanhVienDAO = new ThanhVienDAO(context);
        Log.i("//======"," "+ item.getMaTV());
        ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(item.getMaTV()));
        tvTenTV = v.findViewById(R.id.tvTenThanhVienPM);
        tvTenTV.setText("Thành viên: "+thanhVien.getHoTen());

        tvTienThue = v.findViewById(R.id.tvTienThue);
        tvTienThue.setText("Tiền thuê: "+item.getTienThue());

        tvNgay = v.findViewById(R.id.tvNgayPM);

   tvNgay.setText("Ngày thuê: "+sdf.format(item.getNgay())) ;

        tvTraSach = v.findViewById(R.id.tvTraSach);
        if(item.getTraSach()==1){
            tvTraSach.setTextColor(Color.BLUE);
            tvTraSach.setText("Đã trả sách");
        }else {
            tvTraSach.setTextColor(Color.RED);
            tvTraSach.setText("Chưa trả sách");
        }
        imageDel=v.findViewById(R.id.imgDeleteLS);
    }
    imageDel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //xoa
fragment.xoa(String.valueOf(item.getMaPM()));
        }
    });



    return v;

    }

}
