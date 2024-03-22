package fpoly.htdshoes_pro1121;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import fpoly.htdshoes_pro1121.fragment_admin.frg_donhang;
import fpoly.htdshoes_pro1121.fragment_admin.frg_sanpham;
import fpoly.htdshoes_pro1121.fragment_admin.frg_taikhoan;
import fpoly.htdshoes_pro1121.fragment_admin.frg_thongke;
import fpoly.htdshoes_pro1121.fragment_admin.frg_tongquan_user;

public class manhinhchinh_user extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchinh_user);
        bottomNavigationView = findViewById(R.id.bottomnav_user);
        //sử lý sự kiện khi chọn item
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.tongquan){
                    frg_tongquan_user frgTongQuan= new frg_tongquan_user();
                    replaceFrg(frgTongQuan);
                }else
                if (item.getItemId()==R.id.donHang) {
                    frg_donhang frgDonhang = new frg_donhang();
                    replaceFrg(frgDonhang);
                }else
                if (item.getItemId()==R.id.thongke){
                    frg_thongke frgThongke = new frg_thongke();
                    replaceFrg(frgThongke);
                }else
                if (item.getItemId()==R.id.sanPham) {
                    frg_sanpham frgSanPham= new frg_sanpham();
                    replaceFrg(frgSanPham);
                }else
                if (item.getItemId()==R.id.taikhoan){
                    frg_taikhoan frgTaikhoan= new frg_taikhoan();
                    replaceFrg(frgTaikhoan);
                }

                return true;
            }
        });

    }
    public void replaceFrg(Fragment frg){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frmbottom,frg).commit();
    }
}