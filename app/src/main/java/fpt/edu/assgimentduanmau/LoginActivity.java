package fpt.edu.assgimentduanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import fpt.edu.assgimentduanmau.dao.ThuThuDAO;
import fpt.edu.assgimentduanmau.fragment.ThuThuFragment;

public class LoginActivity extends AppCompatActivity {
    ImageView bg,clound1,starts,clound2;
Animation may ,sao,may2,loat,login,cancel;
Button dangki;

EditText edUserName,edPassword;
Button btnLogin,btnCancel;
CheckBox chkRememberPass;
String strUser,strPass;
ThuThuDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dangki = findViewById(R.id.dangki);

     loat = AnimationUtils.loadAnimation(this,R.anim.transition);
     dangki.startAnimation(loat);
btnLogin = findViewById(R.id.btnLogin);
btnCancel = findViewById(R.id.btnCancel);
login =  AnimationUtils.loadAnimation(this,R.anim.transition1);
btnLogin.startAnimation(login);

        cancel =  AnimationUtils.loadAnimation(this,R.anim.transition2);
        btnCancel.startAnimation(cancel);

        clound1 = findViewById(R.id.cloud1);
        clound2 = findViewById(R.id.cloud2);
       starts = findViewById(R.id.stars);
      may = AnimationUtils.loadAnimation(this,R.anim.animacloud);
        may2 = AnimationUtils.loadAnimation(this,R.anim.animaclound2);
        sao = AnimationUtils.loadAnimation(this,R.anim.anmastar);
        clound2.startAnimation(may2);
    clound1.startAnimation(may);
    starts.startAnimation(sao);

        setTitle("ĐĂNG NHẬP");
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        dao = new ThuThuDAO(this);
        ///doc user pass trong splashcreeactiviti
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String user = pref.getString("USERNAME","");
        String pass = pref.getString("PASSWORD","");
        Boolean rem = pref.getBoolean("REMEMBER",false);

        edUserName.setText(user);
        edPassword.setText(pass);
        chkRememberPass.setChecked(rem);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            edUserName.setText("");
                edPassword.setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkLogin();
            }
        });
    }
    public void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(!status){
            //xoa tinh nang lua tru truoc do
            edit.clear();
        }else{
            //llu du lieu
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        //luu lai tat ca
        edit.commit();
    }
    public void checkLogin(){
        strUser = edUserName.getText().toString();
        strPass = edPassword.getText().toString();
        if(strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tên đăng nhập và mật khẩu không được để trống",Toast.LENGTH_SHORT).show();
        }else{
            if(dao.checkLogin(strUser,strPass)>0){
                Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strPass,chkRememberPass.isChecked());
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("user",strUser);
                startActivity(i);
                finish();

            }else{
                Toast.makeText(getApplicationContext(),"Tên đăng nhập và mật khẩu không đúng",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public  void dangki(View view){
        Intent intent = new Intent(LoginActivity.this, DangKiActivity.class);
        startActivity(intent);
    }
}