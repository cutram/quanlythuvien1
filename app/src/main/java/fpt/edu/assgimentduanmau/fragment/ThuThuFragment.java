package fpt.edu.assgimentduanmau.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpt.edu.assgimentduanmau.R;
import fpt.edu.assgimentduanmau.dao.ThuThuDAO;
import fpt.edu.assgimentduanmau.model.ThuThu;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThuThuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThuThuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThuThuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThuThuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThuThuFragment newInstance(String param1, String param2) {
        ThuThuFragment fragment = new ThuThuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    EditText edNewUser,edNewHoTen,edNewPass,edReNewPass;
    Button btnLuuUser,btnHuy;
    ThuThuDAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_thu, container, false);
        edNewUser = view.findViewById(R.id.edUser);
        edNewHoTen = view.findViewById(R.id.edHoTen);
        edNewPass = view.findViewById(R.id.edPass);
        edReNewPass = view.findViewById(R.id.edRePass);
        btnLuuUser = view.findViewById(R.id.btn_themTT);
        btnHuy = view.findViewById(R.id.btn_cancelTT);
        //đổi mấy id lại
        //file
        dao = new ThuThuDAO(getActivity());
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edNewUser.setText("");
                edNewHoTen.setText("");
                edNewPass.setText("");
                edReNewPass.setText("");
            }
        });
        btnLuuUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThuThu thuThu = new ThuThu();
                thuThu.setMaTT(edNewUser.getText().toString());
                thuThu.setHoTen(edNewUser.getText().toString());
                thuThu.setMatKhau(edNewPass.getText().toString());
                if (validate() > 0){
                    if(dao.insert(thuThu) > 0){
                        Toast.makeText(getActivity(),"Thêm người dùng thành công !",Toast.LENGTH_LONG).show();
                        edNewUser.setText("");
                        edNewHoTen.setText("");
                        edNewPass.setText("");
                        edReNewPass.setText("");
                    } else {
                        Toast.makeText(getActivity(),"Lưu thất bại-trùng tên đăng nhập hoặc mật khẩu",Toast.LENGTH_LONG).show();
                        Toast.makeText(getActivity(),"Vui lòng thử lại",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return view;
    }
    public int validate(){
        int check = 1 ;
        if (edNewUser.getText().length() == 0 || edNewHoTen.getText().length() == 0 || edNewPass.getText().length() == 0 || edReNewPass.getText().length() == 0){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin !",Toast.LENGTH_LONG).show();
            check = -1;
        }else {
            String pass = edNewPass.getText().toString();
            String rePass = edReNewPass.getText().toString();
            if (!pass.equals(rePass)){
                Toast.makeText(getContext(),"Mật khẩu không trùng khớp",Toast.LENGTH_LONG).show();
                check = -1;
            }
        }
        return check;
    }
}