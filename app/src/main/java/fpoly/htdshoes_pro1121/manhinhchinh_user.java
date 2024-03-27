package fpoly.htdshoes_pro1121;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fpoly.htdshoes_pro1121.fragment_user.KhachHangFragment;
import fpoly.htdshoes_pro1121.fragment_user.frg_sanphamuser;
import fpoly.htdshoes_pro1121.fragment_user.frg_tongquan_user;

public class manhinhchinh_user extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchinh_user);

        bottomNavigationView = findViewById(R.id.bottomnav_user);

        // Xử lý sự kiện khi người dùng chọn mục trong BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            // Kiểm tra mục được chọn và gán fragment tương ứng
            int itemId = item.getItemId();
            if (itemId == R.id.nav_tongquan) {
                selectedFragment = new frg_tongquan_user();
            } else if (itemId == R.id.nav_khachhang) {
                selectedFragment = new KhachHangFragment();
                // Thêm các trường hợp xử lý cho các mục khác nếu cần
            } else if (itemId == R.id.nav_sanpham) {
                selectedFragment = new frg_sanphamuser();
            }
            // Thay thế fragment hiện tại bằng fragment được chọn
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_user, selectedFragment).commit();
            }
            return true;
        });



        // Mặc định chọn fragment tổng quan khi mở activity
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_user, new frg_tongquan_user()).commit();
    }
    }
