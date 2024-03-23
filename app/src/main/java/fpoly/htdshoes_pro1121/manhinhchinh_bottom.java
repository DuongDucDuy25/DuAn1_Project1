package fpoly.htdshoes_pro1121;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import fpoly.htdshoes_pro1121.fragment_admin.frg_donhang;
import fpoly.htdshoes_pro1121.fragment_admin.frg_sanpham;
import fpoly.htdshoes_pro1121.fragment_admin.frg_taikhoan;
import fpoly.htdshoes_pro1121.fragment_admin.frg_thongke;
import fpoly.htdshoes_pro1121.fragment_admin.frg_tongquan;

public class manhinhchinh_bottom extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchinh_bottom);
        bottomNavigationView = findViewById(R.id.bottomnav);
        drawerLayout = findViewById(R.id.drawer_layout);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                if (item.getItemId() == R.id.tongquan) {
                    fragment = new frg_tongquan();
                } else if (item.getItemId() == R.id.donHang) {
                    fragment = new frg_donhang();
                } else if (item.getItemId() == R.id.thongke) {
                    fragment = new frg_thongke();
                } else if (item.getItemId() == R.id.sanPham) {
                    fragment = new frg_sanpham();
                } else if (item.getItemId() == R.id.taikhoan) {
                    fragment = new frg_taikhoan();
                }

                replaceFrg(fragment);
                return true;
            }
        });

        setDefaultFragment();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                setTitle(item.getTitle());

                if (item.getItemId() == R.id.tongquan) {
                    frg_tongquan phieuMuonFragment = new frg_tongquan();
                    replaceFrg(phieuMuonFragment);
                } else if (item.getItemId() == R.id.thongke) {
                    frg_thongke loaiSachFragment = new frg_thongke();
                    replaceFrg(loaiSachFragment);
                } else if (item.getItemId() == R.id.donHang) {
                    frg_donhang sachFragment = new frg_donhang();
                    replaceFrg(sachFragment);
                } else if (item.getItemId() == R.id.sanPham) {
                    frg_sanpham thanhVienFragment = new frg_sanpham();
                    replaceFrg(thanhVienFragment);
                } else if (item.getItemId() == R.id.taikhoan) {
                    frg_taikhoan frgtaikhoan = new frg_taikhoan();
                    replaceFrg(frgtaikhoan);
                }

                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
    private void setDefaultFragment() {
        frg_tongquan frgTongquan = new frg_tongquan();
        replaceFrg(frgTongquan);
    }

    public void replaceFrg(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frmbottom, fragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



