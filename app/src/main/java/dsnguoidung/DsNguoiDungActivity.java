package dsnguoidung;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderapp.database.DatabaseHandler;
import com.example.orderapp.databinding.ActivityDsNguoiDungBinding;
import com.example.orderapp.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class DsNguoiDungActivity extends AppCompatActivity {

    private ActivityDsNguoiDungBinding binding;
    List<UserData> list = new ArrayList<>();
    private DanhSachNguoiDungAdapter adapter;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDsNguoiDungBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDanhSachNguoiDung();
    }

    private void getDanhSachNguoiDung() {
        databaseHandler = new DatabaseHandler(this);
        list = new ArrayList<>();
        list = databaseHandler.getAllUser();
        adapter = new DanhSachNguoiDungAdapter(list);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setHasFixedSize(true);
    }
}

