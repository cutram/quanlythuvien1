package fpt.edu.assgimentduanmau.database;

public class Data_SQLite {
    public static final String  INSERT_THU_THU = "Insert into ThuThu(MaTT, HoTen,MatKhau) values" +
            "('admin','nguyminhtram','123')," +
            "('tram','minhtu','122')," +
            "('teo','minhhung','124')," +
            "('hung','vietkhanh','125')";
    public static final String INSERT_THANH_VIEN = "insert into ThanhVien(hoTen, namSinh) values" +
            "('nguyen minh tram','2002')," +
            "('tran van hung','2002')," +
            "('nguyen trinh khanh','2002')," +
            "('ho han','2002')," +
            "('pham viet','2002')," +
            "('minh anh','2002')" ;
public static  final String INSERT_LOAI_SACH = "insert into LoaiSach(tenLoai) values" +
         "('tieng anh nang cao')," +
        "('lap trinh mobile')," +
        "('lap trinh di dong')," +
        "('lap trinh may tinh')" ;

    public static  final String INSERT_SACH = "insert into Sach(tenSach, giaThue, maLoai) values" +
        "('tieng anh co ban','2000','1')," +
        "('tieng anh nang cao','2000','2')," +
        "('lap trinh java','2000','3')," +
        "('lap trinh mobile','2000','4')";
    public static  final String INSERT_PHIEU_MUON = "insert into PhieuMuon(maTT,maTV,maSach,tienThue,ngay,traSach) values" +
            "('tram','2','2','2000','2021/10/08',1)," +
            "('khanh','3','3','3000','2021/10/09',1)," +
            "('hung','4','4','4000','2021/10/10',0)" ;

}
