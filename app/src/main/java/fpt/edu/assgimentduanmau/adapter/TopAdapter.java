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
import fpt.edu.assgimentduanmau.fragment.TopFragment;
import fpt.edu.assgimentduanmau.model.Top;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    TopFragment topFragment;
 private    ArrayList<Top> list;
    TextView tvSach,tvSoLuong,tvXepLoai;


    public TopAdapter(@NonNull Context context, TopFragment topFragment, ArrayList<Top> list) {
        super(context, 0,list);
        this.context = context;
        this.topFragment = topFragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater =(LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.top_item,null);
        }
        final Top item  = list.get(position);
        if(item != null){


            tvSach= v.findViewById(R.id.tvSach);
            tvSach.setText("Sách: "+item.getTenSach());

            tvSoLuong= v.findViewById(R.id.tvSL);
            tvSoLuong.setText("Số lượng: "+item.getSoLuong());
            tvXepLoai = v.findViewById(R.id.tvXepLoai);
            if(item.getSoLuong()>=3){
                tvXepLoai.setText("Sách bán chạy");
            }
            else if(item.getSoLuong()>=2) {
                tvXepLoai.setText("Sách bán vừa ");
            }else {
                tvXepLoai.setText("Sách bán ít");
            }
        }
        return v;

    }

}
