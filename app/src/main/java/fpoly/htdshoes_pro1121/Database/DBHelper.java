package fpoly.htdshoes_pro1121.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "HTDSHOES";
    public static final int DB_VERSION = 10;
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableAdmin = "create table Admin(" +
                "maAdmin TEXT PRIMARY KEY, " +
                "hoTen TEXT NOT NULL, " +
                "matKhau TEXT NOT NULL,"+
                "role INTEGER NOT NULL)";
        db.execSQL(createTableAdmin);
        // tạo bảng Khách hàng
        String createTableKhachHang = "create table KhachHang(" +
                "maKH INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "hoTen TEXT NOT NULL, " +
                "diachi TEXT NOT NULL,"+
                "sdt TEXT NOT NULL)";
        db.execSQL(createTableKhachHang);
        // tạo bảng Đơn Hàng
        String createTableDonHang = "create table DonHang(" +
                "maDonHang INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT NOT NULL, " +
                "soDonHang INTEGER NOT NULL ,"+
                "donGia INTEGER NOT NULL,"+
                "trangThai INTEGER NOT NULL)";
        db.execSQL(createTableDonHang);
        // tạo bảng Sản Phẩm
        String createTableSanPham = "create table SanPham(" +
                "maSanPham INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenSanPham TEXT NOT NULL, " +
                "maCTSP INTEGER REFERENCES ChiTietSanPham(maCTSP), " +
                "maTL INTEGER REFERENCES TheLoai(maTL),"+
                "giaTien INTEGER NOT NULL,"
                +"soluong INTEGER NOT NULL)";
        db.execSQL(createTableSanPham);
        // tạo bảng chi tiết sản phẩm
        String createTableChiTietSanPham = "create table ChiTietSanPham("+
                "maCTSP INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "maTL INTEGER REFERENCES TheLoai(maTL),"+
                "mau TEXT NOT NULL,"+
                "moTa TEXT NOT NULL,"+
                "size INTERGER NOT NULL)";
        db.execSQL(createTableChiTietSanPham);

        //tạo bảng Thể Loại
        String createTableTheLoai = "create table TheLoai("+
                "maTL INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "tenTheLoai TEXT NOT NULL)";
        db.execSQL(createTableTheLoai);

        //tạo bảng chi tiết đơn hàng
        String createTableChiTietDonHang = "create table ChiTietDonHang("+
                "maCTDH INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "maDonHang INTEGER REFERENCES DonHang(maDonHang),"+
                "soLuong INTEGER NOT NULL,"+
                "status INTEGER NOT NULL)";
        db.execSQL(createTableChiTietDonHang);






        // data mẫu
        db.execSQL("INSERT INTO Admin VALUES('duongducduy25','Dương Đức Duy','123',0)," +
                "('tuananh','Nguyễn Đình Tuấn Anh','1234',0)");
        db.execSQL("INSERT INTO KhachHang VALUES(1,'Dương Đức Duy','Hà Nội','0865289908')," +
                "(2,'Phạm Văn Tài','Hà Nội','0988728378'),"+
                "(3,'Trịnh Thị Quỳnh Anh','Thanh Hóa','0892384732')");
        db.execSQL("INSERT INTO DonHang VALUES " +
                "(1,'08/03/2023',3,101923,1), " +
                "(2,'09/03/2023',12,12342,3), " +
                "(3,'10/03/2023',2,923848,4), " +
                "(4,'15/03/2023',2,874632,2), " +
                "(5,'18/03/2023',1,562391,1), " +
                "(6,'20/03/2023',3,943218,3), " +
                "(7,'22/03/2023',1,732491,1), " +
                "(8,'25/03/2023',2,129874,2), " +
                "(9,'27/03/2023',1,238471,1), " +
                "(10,'30/03/2023',3,982374,5)");
        db.execSQL("INSERT INTO SanPham VALUES(1,'Nike Air Force 1',1,1,2000,2),(2,'Mid Wolk Grey',2,2,3000,2),(3,'Mid Iron',3,3,9000,3)");

        db.execSQL("INSERT INTO DonHang VALUES(1,'08/03/2023',3,101923),(2,'09/03/2023',12,12342),(3,'10/03/2023',2,923848)");
        db.execSQL("INSERT INTO SanPham VALUES(1,'Nike Air Force 1',1,1,2000,5)," +
                "(2,'Nike Air Max 90 - Red Stardust',1,1,2210,6)," +
                "(3,'Nike Air Max 1 SC - Clear Jade',1,1,2465,5)," +
                "(4,'Nike Dunk Low Retro - Team Gold',1,1,2930,5)," +
                        "(5,'VANS OLD SKOOL CLASSIC ',2,2,2930,5),"+
                        "(6,'VANS CHECKERBOARD SLIP-ON CLASSIC BLACK',2,2,2930,5),"+
                        "(7,'VANS SLIP-ON CLASSIC TRUE WHITE',2,2,2930,5),"+
                        "(8,'VANS AUTHENTIC CLASSIC BLACK',2,2,2930,5),"+
                        "(9,'Giày Converse Chuck 70 Seasonal Color Canvas',3,3,2930,5),"+
                        "(10,'Giày mules đế bệt unisex Chuck Taylor All Star 1970s',3,3,2930,5),"+
                        "(11,'Giày sneakers nữ cổ thấp Chuck Taylor All Star Lift',3,3,2930,5),"+
                        "(12,'Giày sneakers unisex cổ thấp Chuck Taylor All Star Seasonal',3,3,2930,5),"+
                        "(13,'Giày Adidas Grand Court Alpha Shoes',4,4,2930,5),"+
                        "(14,'Giày Adidas Forum Low Shoes',4,4,2930,5),"+
                        "(15,'Giày Adidas Forum 84 Low Shoes',4,4,3000,5),"+
                        "(16,'Giày Adidas Stan Smith Shoes ',4,4,2930,5),"+
                        "(17,'MLB - Giày sneakers unisex cổ thấp Chunky Liner Mid Saffiano',5,5,2930,5),"+
                        "(18,'MLB - Giày sneakers unisex cổ thấp Chunky Liner',5,5,2930,5),"+
                        "(19,'MLB - Giày sneakers unisex cổ thấp Bigball Chunky Varsity',5,5,2930,5),"+
                        "(20,'MLB - Giày sneakers unisex cổ thấp đế vừa Chunky Liner Hologram',5,5,2930,5)"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        if (i != i1) {
            db.execSQL("drop table if exists Admin");
            db.execSQL("drop table if exists KhachHang");
            db.execSQL("drop table if exists DonHang");
            db.execSQL("drop table if exists SanPham");
            db.execSQL("drop table if exists ChiTietSanPham");
            db.execSQL("drop table if exists ChiTietDonHang");
            db.execSQL("drop table if exists TheLoai");
            onCreate(db);
        }
    }
}
