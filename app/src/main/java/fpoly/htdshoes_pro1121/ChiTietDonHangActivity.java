package fpoly.htdshoes_pro1121;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import fpoly.htdshoes_pro1121.AdapterAdmin.ChiTietDonHangAdapter;
import fpoly.htdshoes_pro1121.AdapterAdmin.DonHangAdapter;
import fpoly.htdshoes_pro1121.Dao.ChiTietDonHangDao;
import fpoly.htdshoes_pro1121.Dao.DonHangDao;
import fpoly.htdshoes_pro1121.Dao.KhachHangDao;
import fpoly.htdshoes_pro1121.Dao.SanPhamDao;
import fpoly.htdshoes_pro1121.Database.DBHelper;
import fpoly.htdshoes_pro1121.Model.ChiTietDonHang;
import fpoly.htdshoes_pro1121.Model.DonHang;
import fpoly.htdshoes_pro1121.Model.KhachHang;
import fpoly.htdshoes_pro1121.Model.SanPham;

public class ChiTietDonHangActivity extends AppCompatActivity {
    // Thêm khai báo danh sách sản phẩm
    private ArrayList<ChiTietDonHang> list;
    private RecyclerView rcChiTietDonHang;
    private ChiTietDonHangDao dao;
    private ArrayList<DonHang> listDonHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);
        rcChiTietDonHang = findViewById(R.id.rcChiTietSanPham);
        ArrayList<SanPham> listSanPham = new SanPhamDao(this).getlistdata(); // Lấy danh sách sản phẩm
        ArrayList<KhachHang> listKhachHang = (ArrayList<KhachHang>) new KhachHangDao(this).getAllKhachHang();
        listDonHang = new ArrayList<>();
        dao = new ChiTietDonHangDao(this);


        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcChiTietDonHang.setLayoutManager(manager);
        ChiTietDonHangAdapter adapter = new ChiTietDonHangAdapter(this, list, dao,listDonHang); // Truyền danh sách sản phẩm vào adapter
        adapter.setListSanPham(listSanPham);
        adapter.setListKhachHang(listKhachHang);
        rcChiTietDonHang.setAdapter(adapter);

    }

    // Phương thức để lấy đối tượng DonHang từ Intent hoặc từ danh sách đối tượng
}