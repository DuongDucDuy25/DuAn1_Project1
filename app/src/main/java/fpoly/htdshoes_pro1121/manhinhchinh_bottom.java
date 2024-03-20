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
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import fpoly.htdshoes_pro1121.Adapter.PhotoViewPagerAdapter;
import fpoly.htdshoes_pro1121.Model.Photo;
import fpoly.htdshoes_pro1121.fragment.frg_donhang;
import fpoly.htdshoes_pro1121.fragment.frg_sanpham;
import fpoly.htdshoes_pro1121.fragment.frg_taikhoan;
import fpoly.htdshoes_pro1121.fragment.frg_thongke;
import fpoly.htdshoes_pro1121.fragment.frg_tongquan;
import me.relex.circleindicator.CircleIndicator;

public class manhinhchinh_bottom extends AppCompatActivity {
    DrawerLayout drawerLayout;
    View mHeaderView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchinh_bottom);

        drawerLayout = findViewById(R.id.drawer_layout);
        BottomNavigationView nv = findViewById(R.id.bottomnav);
        Intent i = getIntent();


        getSupportFragmentManager().beginTransaction().replace(R.id.frmbottom, new frg_tongquan()).commit();
        setDefaultFragment();
        nv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                setTitle(item.getTitle());
                if (item.getItemId() == R.id.tongquan) {
                    frg_tongquan phieuMuonFragment = new frg_tongquan();
                    setTitle(item.getTitle());
                    replaceFrg(phieuMuonFragment);
                } else if (item.getItemId() == R.id.thongke) {
                    frg_thongke loaiSachFragment = new frg_thongke();
                    setTitle(item.getTitle());
                    replaceFrg(loaiSachFragment);
                } else if (item.getItemId() == R.id.donHang) {
                    frg_donhang sachFragment = new frg_donhang();
                    setTitle(item.getTitle());
                    replaceFrg(sachFragment);
                } else if (item.getItemId() == R.id.sanPham) {
                    frg_sanpham thanhVienFragment = new frg_sanpham();
                    setTitle(item.getTitle());
                    replaceFrg(thanhVienFragment);
                } else if (item.getItemId() == R.id.taikhoan) {
                    frg_taikhoan frgtaikhoan = new frg_taikhoan();
                    setTitle(item.getTitle());
                    replaceFrg(frgtaikhoan);
                }

                drawerLayout.closeDrawers();
                return true; // Thay đổi giá trị trả về thành true
            }
        });
    }
    private void setDefaultFragment() {
        frg_tongquan frgTongquan = new frg_tongquan();
        replaceFrg(frgTongquan);
    }
    public void replaceFrg(Fragment frg) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frmbottom, frg).commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


}