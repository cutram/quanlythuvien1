package fpt.edu.assgimentduanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
private  static  final  String DB_NAME = "PNLIB1";
private  static final  int DB_VERSION = 1;

static final String CREATE_TABLE_THU_THU =
      "  create table ThuThu (" +
    "maTT TEXT PRIMARY KEY, " +
            "hoTen   TEXT NOT NULL, " +
          "matKhau TEXT NOT NULL)";

    static final String CREATE_TABLE_THANH_VIEN =
"create table ThanhVien (" +
        "maTV  INTEGER PRIMARY KEY AUTOINCREMENT, " +
        "hoTen  TEXT NOT NULL," +
        "namSinh TEXT NOT NULL)";

    static final String CREATE_TABLE_LOAI_SACH =
"create table LoaiSach (" +
        "maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
        "tenLoai TEXT NOT NULL)";

    static final String CREATE_TABLE_SACH =
            "create table Sach ( " +
                    "maSach INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenSach TEXT NOT NULL, " +
                    "giaThue INTEGER NOT NULL, " +
                    "maLoai INTEGER REFERENCES LoaiSach(maloai))";
    static final String CREATE_TABLE_PHIEU_MUON =
            "create table PhieuMuon ( " +
                    "maPM     INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                    "maTT     TEXT     REFERENCES ThuTHu (maTT), " +
                    "maTV     TEXT     REFERENCES ThanhVien (maTV), " +
                    "maSach   INTEGER  REFERENCES Sach (maSach), " +
                     "tienThue INTEGER NOT NULL, " +
                    "ngay     DATE     NOT NULL, " +
                    "traSach  INTEGER  NOT NULL)";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//tao bang thu thu
        db.execSQL(CREATE_TABLE_THU_THU);
        // thanh vein
        db.execSQL(CREATE_TABLE_THANH_VIEN);
        //loai sach
        db.execSQL(CREATE_TABLE_LOAI_SACH);
    //sach
        db.execSQL(CREATE_TABLE_SACH);
        //phieu muon
        db.execSQL(CREATE_TABLE_PHIEU_MUON);
    //insert data
        db.execSQL(Data_SQLite.INSERT_THU_THU);
        db.execSQL(Data_SQLite.INSERT_THANH_VIEN);
        db.execSQL(Data_SQLite.INSERT_LOAI_SACH);
        db.execSQL(Data_SQLite.INSERT_SACH);
        db.execSQL(Data_SQLite.INSERT_PHIEU_MUON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
String dropTableLoaiThuThu = "drop table if exists ThuThu";
db.execSQL(dropTableLoaiThuThu);
String dropTableThanhVien = "drop table if exists ThanhVien";
db.execSQL(dropTableThanhVien);
String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);
        onCreate(db);

    }
}
