package fpt.edu.assgimentduanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpt.edu.assgimentduanmau.dao.ThuThuDAO;
import fpt.edu.assgimentduanmau.model.ThuThu;

public class DangKiActivity extends AppCompatActivity {
    EditText edNewUser,edNewHoTen,edNewPass,edReNewPass;
    Button btnLuuUser,btnHuy;
    ThuThuDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);

        edNewUser = findViewById(R.id.edUser);
        edNewHoTen =findViewById(R.id.edHoTen);
        edNewPass = findViewById(R.id.edPass);
        edReNewPass = findViewById(R.id.edRePass);
        btnLuuUser = findViewById(R.id.btn_themTT);
        btnHuy = findViewById(R.id.btn_cancelTT);
        //đổi mấy id lại
        //file
        dao = new ThuThuDAO(getApplicationContext());
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
                        Toast.makeText(getApplicationContext(),"Đăng kí thàng công!",Toast.LENGTH_LONG).show();
                        edNewUser.setText("");
                        edNewHoTen.setText("");
                        edNewPass.setText("");
                        edReNewPass.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(),"đăng kí thất bại-trùng tài khoản hoặc mật khuẩu",Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),"Vui lòng thử lại",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }




    public int validate(){
        int check = 1 ;
        if (edNewUser.getText().length() == 0 || edNewHoTen.getText().length() == 0 || edNewPass.getText().length() == 0 || edReNewPass.getText().length() == 0){
            Toast.makeText(getApplicationContext(),"Bạn phải nhập đầy đủ thông tin !",Toast.LENGTH_LONG).show();
            check = -1;
        }else {
            String pass = edNewPass.getText().toString();
            String rePass = edReNewPass.getText().toString();
            if (!pass.equals(rePass)){
                Toast.makeText(getApplicationContext(),"Mật khẩu không trùng khớp",Toast.LENGTH_LONG).show();
                check = -1;
            }
        }
        return check;
    }
}