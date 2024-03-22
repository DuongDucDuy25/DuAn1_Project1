    package fpoly.htdshoes_pro1121.Database;

    import android.content.Context;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    import androidx.annotation.Nullable;

    public class DBHelper extends SQLiteOpenHelper {
        public static final String DB_NAME = "HTDSHOES";
        public static final int DB_VERSION = 6;
        public DBHelper(@Nullable Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTableAdmin = "create table Admin(" +
                    "maAdmin TEXT PRIMARY KEY, " +
                    "hoTen TEXT NOT NULL, " +
                    "matKhau TEXT NOT NULL,"+
                    "round INTEGER NOT NULL)";
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
                    "status INTEGER NOT NULL)";
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
            db.execSQL("INSERT INTO DonHang VALUES(1,'08/03/2023',3,101923,1),(2,'09/03/2023',12,12342,0),(3,'10/03/2023',2,923848,3)");
            db.execSQL("INSERT INTO SanPham VALUES(1,'Nike Air Force 1',1,1,2000,20),(2,'Mid Wolk Grey',2,2,3092,2),(3,'Mid Iron',3,3,9428,3)");

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
