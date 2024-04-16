package fpoly.htdshoes_pro1121.d;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.Objects;

import database.DatabaseHandler;
import fpoly.htdshoes_pro1121.R;
import fpoly.htdshoes_pro1121.databinding.ActivityDetailFoodBinding;
import model.FoodModel;
import model.UserData;

public class DetailFoodActivity extends AppCompatActivity {

    private ActivityDetailFoodBinding binding;
    private FoodModel foodModel;
    private UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userData = new Gson().fromJson(SharedPref.read(SharedPref.USER_DATA, ""), UserData.class);
        setOnClick();
        AppBarLayout appBarLayout = findViewById(R.id.appBarLayout);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolBarLayout);

        appBarLayout.addOnOffsetChangedListener((appBarLayout1, verticalOffset) -> {
            collapsingToolbarLayout.setTitleEnabled(Math.abs(verticalOffset) == appBarLayout1.getTotalScrollRange());
        });
        foodModel = (FoodModel) Objects.requireNonNull(getIntent().getExtras()).get("data");
        if (foodModel != null) {
            binding.toolBarLayout.setTitle(foodModel.getName());
            Glide.with(this).load(foodModel.getImage()).into(binding.imgProduct);
            binding.name.setText(foodModel.getName());
            binding.description.setText(foodModel.getDescription());
            DecimalFormat formatter = new DecimalFormat("#,###");
            binding.price.setText(formatter.format(foodModel.getPrice()) + " VNĐ");

            binding.soluong.setText("Số lượng: " + foodModel.getSoluong());
        }
    }

    private void setOnClick() {
        binding.btnCong.setOnClickListener(view -> {
            int soluong = Integer.parseInt(binding.tvSoLuong.getText().toString());
            soluong++;
            binding.tvSoLuong.setText(soluong + "");
        });

        binding.btnTru.setOnClickListener(view -> {
            int soluong = Integer.parseInt(binding.tvSoLuong.getText().toString());
            if (soluong == 1) {
                binding.tvSoLuong.setText(1 + "");
                return;
            }
            soluong--;
            binding.tvSoLuong.setText(soluong + "");
        });

        binding.btnAddCart.setOnClickListener(view -> {
            if (foodModel.getSoluong() == 0) {
                Toast.makeText(this, "Số lượng hàng trong kho đã hết", Toast.LENGTH_SHORT).show();
            } else {
                if (foodModel != null && userData != null) {
                    DatabaseHandler databaseHandler = new DatabaseHandler(this);
                    int amount = Integer.parseInt(binding.tvSoLuong.getText().toString());
                    String description = binding.description.getText().toString();
                    int foodId = foodModel.getId();
                    String image = foodModel.getImage();
                    String name = binding.name.getText().toString();
                    Long price = foodModel.getPrice();
                    int userID = userData.getId();
                    int loaiGiay = foodModel.getLoaiGiay();
                    long result = databaseHandler.addCart(amount, description, foodId, image, name, price, userID, loaiGiay);
                    if (result == -1) {
                        ShowMessageHelper.showMessage(DetailFoodActivity.this, "Thêm giỏ hàng thất bại");
                    } else {
                        ShowMessageHelper.showMessage(DetailFoodActivity.this, "Thêm giỏ hàng thành công");
                    }
                }
            }

        });
    }
}