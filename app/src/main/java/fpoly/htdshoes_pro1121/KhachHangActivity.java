package fpoly.htdshoes_pro1121;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import fpoly.htdshoes_pro1121.AdapterAdmin.KhachHangAdminAdapter;
import fpoly.htdshoes_pro1121.AdapterAdmin.SanPhamAdminAdapter;
import fpoly.htdshoes_pro1121.Dao.KhachHangDao;
import fpoly.htdshoes_pro1121.Dao.SanPhamDao;
import fpoly.htdshoes_pro1121.Model.KhachHang;
import fpoly.htdshoes_pro1121.Model.SanPham;

public class KhachHangActivity extends AppCompatActivity {
    private RecyclerView rcKhachHang;
    private KhachHangDao dao;
    private androidx.appcompat.widget.SearchView searchView;
    private LinearLayout btnThemSP, btnSuaSp;
    private ArrayList<KhachHang> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);
        rcKhachHang = findViewById(R.id.rcKhachHang);


        dao = new KhachHangDao(this);
        loadData();

    }
    private void loadData() {
        ArrayList<KhachHang> list = (ArrayList<KhachHang>) dao.getAllKhachHang();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcKhachHang.setLayoutManager(manager);
        KhachHangAdminAdapter adapter = new KhachHangAdminAdapter(this, list,dao);
        rcKhachHang.setAdapter(adapter);

    }
}