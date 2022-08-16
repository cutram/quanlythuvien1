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
import java.util.List;

import fpt.edu.assgimentduanmau.R;
import fpt.edu.assgimentduanmau.fragment.ThanhVienFragment;
import fpt.edu.assgimentduanmau.model.ThanhVien;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    ThanhVienFragment fragment;
    private ArrayList<ThanhVien> lists;
LinearLayout lntv;
    TextView tvMaTV,tvTenTV,tvNamSinh;
    ImageView imaDel;
ImageView  tV1,tV2;
    public ThanhVienAdapter(@NonNull Context context, ThanhVienFragment fragment, ArrayList<ThanhVien> lists) {
        super(context, 0, lists);
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
            v = inflater.inflate(R.layout.thah_vien_item,null);
        }
        final ThanhVien item = lists.get(position);
        if (item != null) {
            tV1=v.findViewById(R.id.imgTV1);
            Animation tvv1 = AnimationUtils.loadAnimation(context, R.anim.transition1);
            tV1.setAnimation(tvv1);

            tV2=v.findViewById(R.id.imgTV2);
            Animation tv2 = AnimationUtils.loadAnimation(context, R.anim.transition2);
            tV2.setAnimation(tv2);



            lntv = v.findViewById(R.id.cns_thanhvien);
            Animation aa = AnimationUtils.loadAnimation(context, R.anim.transition);
            lntv.setAnimation(aa);

            tvMaTV = v.findViewById(R.id.tvMaTV);
            tvMaTV.setText("Mã thành viên: "+item.getMaTV());

            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Tên thành viên: "+item.getHoTen());

            tvNamSinh = v.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Năm sinh: "+item.getNamSinh());

            imaDel = v.findViewById(R.id.imgDeleteLS);
        }
        imaDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xóa
                fragment.xoa(String.valueOf(item.getMaTV()));
            }
        });
        return v;



    }
}
