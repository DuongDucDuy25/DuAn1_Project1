package updatefood;

import static fpoly.htdshoes_pro1121.d.ShowMessageHelper.showMessage;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.Objects;

import database.DatabaseHandler;
import fpoly.htdshoes_pro1121.databinding.ActivityUpdateFoodBinding;
import model.FoodModel;

public class UpdateFoodActivity extends AppCompatActivity {

    private ActivityUpdateFoodBinding binding;
    private int documentID = -1;
    private FoodModel foodModel;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initDatabase();
        loadData();
        setupListeners();
    }

    private void initDatabase() {
        databaseHandler = new DatabaseHandler(this);
        documentID = (Integer) Objects.requireNonNull(getIntent().getExtras()).get("documentID");
    }

    private void loadData() {
        foodModel = (FoodModel) Objects.requireNonNull(getIntent().getExtras()).get("data");
        if (foodModel != null) {
            binding.toolBarLayout.setTitle(foodModel.getName());
            Glide.with(this).load(foodModel.getImage()).into(binding.imgProduct);
            binding.name.setText(foodModel.getName());
            binding.description.setText(foodModel.getDescription());

            DecimalFormat formatter = new DecimalFormat("#,###");
            binding.price.setText(formatter.format(foodModel.getPrice()));
            binding.soluong.setText(String.valueOf(foodModel.getSoluong()));
        }
    }

    private void setupListeners() {
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptUpdate();
            }
        });
    }

    private void attemptUpdate() {
        if (TextUtils.isEmpty(binding.name.getText())) {
            showMessage(this, "Không được để trống tên");
            return;
        } else if (TextUtils.isEmpty(binding.description.getText())) {
            showMessage(this, "Không được để trống mô tả");
            return;
        } else if (TextUtils.isEmpty(binding.price.getText())) {
            showMessage(this, "Không được để trống giá");
            return;
        } else if (TextUtils.isEmpty(binding.soluong.getText())) {
            showMessage(this, "Không được để trống số lượng");
            return;
        }

        try {
            long price = Long.parseLong(cleanNumericString(binding.price.getText().toString()));
            int quantity = Integer.parseInt(binding.soluong.getText().toString());
            int result = databaseHandler.updateProduct(
                    binding.name.getText().toString(),
                    binding.description.getText().toString(),
                    price,
                    foodModel.getId(),
                    quantity
            );
            if (result == -1) {
                showMessage(this, "Update thất bại");
            } else {
                showMessage(this, "Update thành công");
            }
        } catch (NumberFormatException e) {
            showMessage(this, "Vui lòng nhập số hợp lệ cho giá và số lượng");
        }
    }

    private String cleanNumericString(String text) {
        return text.replaceAll("[^\\d]", "");
    }
}
