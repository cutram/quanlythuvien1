package fpt.edu.assgimentduanmau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpt.edu.assgimentduanmau.R;
import fpt.edu.assgimentduanmau.fragment.LoaiSachFragment;
import fpt.edu.assgimentduanmau.fragment.PhieuMuonFragment;
import fpt.edu.assgimentduanmau.model.LoaiSach;
import fpt.edu.assgimentduanmau.model.PhieuMuon;
import fpt.edu.assgimentduanmau.model.ThanhVien;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    LoaiSachFragment fragment;
    private ArrayList<LoaiSach> lists;
    TextView tvMaLoaiSach, tvTenLoaiSach;
    ImageView imgDel;
    LinearLayout lvLoaiSach;
ImageView imgLoaiSach;
    public LoaiSachAdapter(@NonNull Context context, LoaiSachFragment fragment, ArrayList<LoaiSach> lists) {
        super(context, 0,lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item,null);
        }
        final LoaiSach item = lists.get(position);
        if (item != null) {
            imgLoaiSach=v.findViewById(R.id.imgLoaiSach);
            Animation ls = AnimationUtils.loadAnimation(context, R.anim.transition2);
            imgLoaiSach.setAnimation(ls);



            lvLoaiSach = v.findViewById(R.id.cns_loaisach);
            Animation aa = AnimationUtils.loadAnimation(context, R.anim.transition);
            lvLoaiSach.setAnimation(aa);

            tvMaLoaiSach = v.findViewById(R.id.tvMaLoaiSach);
            tvMaLoaiSach.setText("Mã Loại sách:  "+item.getMaLoai());
            tvTenLoaiSach = v.findViewById(R.id.tvTenLoaiSach);
            tvTenLoaiSach.setText("Loại sách:  "+item.getTenLoai());


            imgDel = v.findViewById(R.id.imgDeleteLSS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xóa
                fragment.xoa(String.valueOf(item.getMaLoai()));
            }
        });
        return v;



    }
}
