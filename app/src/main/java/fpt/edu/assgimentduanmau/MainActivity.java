package fpt.edu.assgimentduanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import fpt.edu.assgimentduanmau.dao.DemoDB;
import fpt.edu.assgimentduanmau.dao.PhieuMuonDAO;
import fpt.edu.assgimentduanmau.dao.ThuThuDAO;
import fpt.edu.assgimentduanmau.fragment.ChangePassFragment;
import fpt.edu.assgimentduanmau.fragment.DoanhThuFragment;
import fpt.edu.assgimentduanmau.fragment.LoaiSachFragment;
import fpt.edu.assgimentduanmau.fragment.PhieuMuonFragment;
import fpt.edu.assgimentduanmau.fragment.SachFragment;
import fpt.edu.assgimentduanmau.fragment.ThanhVienFragment;
import fpt.edu.assgimentduanmau.fragment.ThuThuFragment;
import fpt.edu.assgimentduanmau.fragment.TopFragment;
import fpt.edu.assgimentduanmau.model.ThuThu;

public class MainActivity extends AppCompatActivity {
DrawerLayout drawer;
Toolbar toolbar;
View mHeaderView;
TextView edUser;

ThuThuDAO thuThuDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///tao dâtbese
//        DemoDB demoDB = new DemoDB(getApplicationContext());
//        demoDB.thanhVien();

        drawer=findViewById(R.id.drawer_layout);

        toolbar=findViewById(R.id.toolbar1);

        //set toolbar thay the cho actionbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);
        FragmentManager manager = getSupportFragmentManager();
        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,phieuMuonFragment)
                .commit();

        NavigationView nv = findViewById(R.id.nvView);
        mHeaderView = nv.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.txtUser);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        thuThuDAO = new ThuThuDAO(this);
        ThuThu thu = thuThuDAO.getID(user);
        String username = thu.getHoTen();
        edUser.setText("Xin chào: "+username );
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();

                switch (item.getItemId()){
                    case R.id.nav_PhieuMuon:
                        setTitle("Quản lý phiếu mượn");
                        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,phieuMuonFragment)
                                .commit();

                        break;
                    case R.id.nav_LoaiSach:
                        setTitle("Quản lý loại sách");
                        Toast.makeText(getApplicationContext(),"Quản lý loại sách",Toast.LENGTH_SHORT).show();
                        LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,loaiSachFragment)
                                .commit();
                        break;
                    case R.id.nav_Sach:
                        setTitle("Quản lý sách");
                        Toast.makeText(getApplicationContext(),"Quản lý sách",Toast.LENGTH_SHORT).show();
                        SachFragment sachFragment = new SachFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,sachFragment)
                                .commit();

                        break;
                    case R.id.nav_ThanhVien:
                        setTitle("Quản lý thành viên");
                        Toast.makeText(getApplicationContext(),"Quản lý thành viên",Toast.LENGTH_SHORT).show();
                        ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,thanhVienFragment)
                                .commit();

                        break;
                    case R.id.sub_Top:
                        setTitle("Top 10 sách cho thuê nhiều nhất");
                        Toast.makeText(getApplicationContext(),"Top 10 sách cho thuê nhiều nhất",Toast.LENGTH_SHORT).show();
                        TopFragment topFragment = new TopFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,topFragment)
                                .commit();

                        break;
                    case R.id.sub_DoanhThu:
                        setTitle("Thống kê doanh thu");
                        Toast.makeText(getApplicationContext(),"Thống kê doanh thu",Toast.LENGTH_SHORT).show();
                        DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,doanhThuFragment)
                                .commit();

                        break;
                    case R.id.sub_AddUser:
                        setTitle("Thêm người dùng");
                        Toast.makeText(getApplicationContext(),"Thêm người dùng",Toast.LENGTH_SHORT).show();
                        ThuThuFragment thuThuFragment = new ThuThuFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,thuThuFragment)
                                .commit();
                        break;
                    case R.id.sub_Pass:
                        setTitle("Thay đổi mật khẩu");
                        Toast.makeText(getApplicationContext(),"Thay đổi mật khẩu",Toast.LENGTH_SHORT).show();
                            ChangePassFragment changePassFragment = new ChangePassFragment();
                            manager.beginTransaction()
                                    .replace(R.id.flContent,changePassFragment)
                                    .commit();
                        break;
                    case R.id.sub_Logout:
             startActivity(new Intent(getApplicationContext(),LoginActivity.class));
             finish();
                        Toast.makeText(getApplicationContext(),"Xin chào và hẹn gặp lại",Toast.LENGTH_SHORT).show();
                        break;


                }

                drawer.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}