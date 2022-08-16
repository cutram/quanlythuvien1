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
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.assgimentduanmau.R;
import fpt.edu.assgimentduanmau.dao.LoaiSachDAO;
import fpt.edu.assgimentduanmau.fragment.SachFragment;
import fpt.edu.assgimentduanmau.model.LoaiSach;
import fpt.edu.assgimentduanmau.model.Sach;

public class SachAdapter extends ArrayAdapter<Sach> {
    Context context;
    SachFragment fragment;
    List<Sach> list;
    TextView tvMaSach,tvTenSach,tvGiaThue,tvLoai;
    ImageView imgDel;
    LinearLayout lvsach;
ImageView sach1,sach2,sach3;
    public SachAdapter(@NonNull Context context,  SachFragment fragment, List<Sach> list) {
        super(context, 0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_item,null);
        }

        final Sach item = list.get(position);
        if(item != null){
            sach1=v.findViewById(R.id.imgSach1);
            Animation s1 = AnimationUtils.loadAnimation(context, R.anim.transition2);
            sach1.setAnimation(s1);

            sach2=v.findViewById(R.id.imgSach2);
            Animation s2 = AnimationUtils.loadAnimation(context, R.anim.transition1);
            sach2.setAnimation(s2);

            sach3=v.findViewById(R.id.imgSach3);
            Animation s3= AnimationUtils.loadAnimation(context, R.anim.second);
            sach3.setAnimation(s3);


            lvsach = v.findViewById(R.id.cns_sach);
            Animation aa = AnimationUtils.loadAnimation(context, R.anim.transition);
            lvsach.setAnimation(aa);

            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.getMaLoai()));
            tvMaSach = v.findViewById(R.id.tvMaSach);
            tvMaSach.setText("Mã Sách: "+item.getMaSach());

            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên Sách: "+item.getTenSach());
            tvGiaThue = v.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText("Giá Thuê: "+item.getGiaThue());

            tvLoai = v.findViewById(R.id.tvLoai);
            tvLoai.setText("Loại Sách: "+loaiSach.getTenLoai());
 imgDel = v.findViewById(R.id.imgDeleteLS);

        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xoa
fragment.xoa(String.valueOf(item.getMaSach()));
            }
        });

        return  v;

    }
}
