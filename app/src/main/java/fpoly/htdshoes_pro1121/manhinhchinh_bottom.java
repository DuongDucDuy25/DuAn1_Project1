package fpoly.htdshoes_pro1121;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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

        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNavigationView = findViewById(R.id.bottomnav);

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
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        setDefaultFragment();

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_home_24);

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
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
