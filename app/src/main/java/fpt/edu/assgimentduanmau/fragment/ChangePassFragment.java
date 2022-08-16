package fpt.edu.assgimentduanmau.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Trace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import fpt.edu.assgimentduanmau.R;
import fpt.edu.assgimentduanmau.dao.ThuThuDAO;
import fpt.edu.assgimentduanmau.model.ThuThu;


public class ChangePassFragment extends Fragment {
TextInputEditText edPassOld, edPass,edRePass;
Button btnSave, btnCancel;
ThuThuDAO dao;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_change_pass, container, false);
       dao = new ThuThuDAO(getActivity());
edPassOld = v.findViewById(R.id.edPassOld);
        edPassOld = v.findViewById(R.id.edPassOld);
        edPass = v.findViewById(R.id.edPassChange);
        edRePass = v.findViewById(R.id.edRePassChange);
        btnSave = v.findViewById(R.id.btnSaveUserChange);
        btnCancel = v.findViewById(R.id.btnCancelUserChange);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              edPass.setText("");
              edPassOld.setText("");
              edRePass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME","");
                if(validate()>0){
                    ThuThu thuThu = dao.getID(user);
                    thuThu.setMatKhau(edPass.getText().toString());
                     dao.updatePass(thuThu);
                    if(dao.updatePass(thuThu) > 0){
                        Toast.makeText(getActivity(),"Thay đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                        edPass.setText("");
                        edPassOld.setText("");
                        edRePass.setText("");
                    }else {
                        Toast.makeText(getActivity(),"Thay đổi mật khẩu không thành công",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    return v;

    }
    public  int validate(){
        int check = 1;
if(edPassOld.getText().length()==0||edPass.getText().length()==0||edRePass.getText().length()==0){
    Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
    check = -1;
}else {
    //doc user pass
    SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
    String passOld = pref.getString("PASSWORD","");
    String pass = edPass.getText().toString();
    String rePass = edRePass.getText().toString();
    if(!passOld.equals(edPassOld.getText().toString())){
        Toast.makeText(getContext(),"Bạn nhập sai mật khẩu cũ",Toast.LENGTH_SHORT).show();
        check = -1;
    }
    if(!pass.equals(rePass)){
        Toast.makeText(getContext(),"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
        check = -1;
    }
}


        return check;
    }
}