package fpoly.htdshoes_pro1121.d;

import static androidx.navigation.ui.BottomNavigationViewKt.setupWithNavController;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.Gson;

import fpoly.htdshoes_pro1121.R;
import fpoly.htdshoes_pro1121.databinding.ActivityHomeBinding;
import model.UserData;

public class HomeActivity extends AppCompatActivity {

    private UserData userData;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Khởi tạo SharedPreferences
        SharedPref.init(this);  // Thêm dòng này để khởi tạo mSharedPref
        setUp();
        setOnClick();

    }

    private void setUp() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        setupWithNavController(binding.bottomNavigation, navHostFragment.getNavController());
        userData = new Gson().fromJson(SharedPref.read(SharedPref.USER_DATA, ""), UserData.class);
//        if (userData != null && userData.getRole() != null && userData.getRole().equals("admin")) {
//            binding.bottomNavigation.getMenu().findItem(R.id.cartFragment).setVisible(false);
//            binding.bottomNavigation.getMenu().findItem(R.id.orderHistoryFragment).setVisible(false);
//        }
//        if (userData != null && userData.getRole() != null && userData.getRole().equals("user")) {
//            binding.bottomNavigation.getMenu().findItem(R.id.allOrderFragment).setVisible(false);
//        }

        if (userData != null && userData.getRole() != null && userData.getRole().equals("admin")) {
            binding.bottomNavigation.inflateMenu(R.menu.menu_bottom_admin);
        }
        if (userData != null && userData.getRole() != null && userData.getRole().equals("user")) {
            binding.bottomNavigation.inflateMenu(R.menu.menu_bottom_user);
        }
    }

    private void setOnClick() {

    }
}