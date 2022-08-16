package fpt.edu.assgimentduanmau.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.assgimentduanmau.database.DbHelper;
import fpt.edu.assgimentduanmau.model.PhieuMuon;
import fpt.edu.assgimentduanmau.model.ThanhVien;
import fpt.edu.assgimentduanmau.model.ThuThu;

public class DemoDB {
    private SQLiteDatabase db;
    ThanhVienDAO thanhVienDAO;
    ThuThuDAO thuThuDAO;
    LoaiSachDAO loaiSachDAO;
    PhieuMuonDAO phieuMuonDAO;
    SachDAO sachDAO;
    static final String TAG = "//========";

    public DemoDB(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        thanhVienDAO = new ThanhVienDAO(context);
        thuThuDAO = new ThuThuDAO(context);
        loaiSachDAO = new LoaiSachDAO(context);
        phieuMuonDAO = new PhieuMuonDAO(context);
        sachDAO = new SachDAO(context);

    }

    public void thanhVien() {
        //    List<ThanhVien> ls = new ArrayList<>();
//        ThanhVien tv = new ThanhVien(1, "minh tram", "2000");
        //    if(thanhVienDAO.insert(tv) > 0){
        //       Log.i(TAG,"them thanh cong");
        //    }else {
        //         Log.i(TAG,"them that bai");
        //   }
        //  Log.i(TAG,"//================================");
        //  Log.i(TAG,"Tong so thanh vien la : "+thanhVienDAO.getAll().size());
//
//        Log.i(TAG,"//============sau khi sua====================");
//        tv = new ThanhVien(1,"minh tram2","2000");
//        thanhVienDAO.update(tv);
//        Log.i(TAG,"Tong so thanh vien la : "+thanhVienDAO.getAll().size());
//        thanhVienDAO.delete("1");
//        Log.i(TAG,"//=======sau khi xoa=========================");
//        Log.i(TAG,"Tong so thanh vien la : "+thanhVienDAO.getAll().size());
//    }


    }
}