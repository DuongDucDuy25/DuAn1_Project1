package home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import database.DatabaseHandler;
import fpoly.htdshoes_pro1121.R;
import fpoly.htdshoes_pro1121.d.DetailFoodActivity;
import fpoly.htdshoes_pro1121.d.SharedPref;
import fpoly.htdshoes_pro1121.d.ShowMessageHelper;
import fpoly.htdshoes_pro1121.d.ThemSanPhamActivity;
import fpoly.htdshoes_pro1121.databinding.FragmentHomeBinding;
import model.FoodModel;
import model.UserData;
import updatefood.UpdateFoodActivity;

public class HomeFragment extends Fragment implements HomeAdapter.OnItemClickListener {

    private FragmentHomeBinding binding;
    private List<FoodModel> list = new ArrayList<>();
    private HomeAdapter adapter;
    private UserData userData;
    private DatabaseHandler databaseHandler;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userData = new Gson().fromJson(SharedPref.read(SharedPref.USER_DATA, ""), UserData.class);
        databaseHandler = new DatabaseHandler(getContext());
        if (userData != null && !TextUtils.isEmpty(userData.getRole()) && userData.getRole().equals("admin")) {
            binding.imgAddFood.setVisibility(View.VISIBLE);
            binding.imgAddFood.setOnClickListener(v -> startActivity(new Intent(getContext(), ThemSanPhamActivity.class)));
        }


        binding.searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.tieude.setVisibility(View.GONE);
                }
            }
        });

        binding.searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                binding.tieude.setVisibility(View.VISIBLE);
                return false;
            }
        });


        final int[] imageResources = {R.drawable.banner_1, R.drawable.banner_2,R.drawable.banner3,R.drawable.banner4,R.drawable.banner5};
        final int[] currentIndex = {0};

        binding.viewpager.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(requireContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return imageView;
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.viewpager.setImageResource(imageResources[currentIndex[0]]);
                currentIndex[0] = (currentIndex[0] + 1) % imageResources.length;
                new Handler().postDelayed(this, 2000);
            }
        }, 500);

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
    }

    private void filter(String text) {
        ArrayList<FoodModel> filteredlist = new ArrayList<FoodModel>();
        for (FoodModel item : list) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (!filteredlist.isEmpty()) {
            adapter.replaceList(filteredlist);
        }
    }

    private void getAllFoods() {
        list.clear();
        list = databaseHandler.getAll();
        adapter = new HomeAdapter(list);
        adapter.setOnItemClickListener(HomeFragment.this);
        binding.recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 3);
        binding.recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onClick(int position) {
        if (userData != null && !TextUtils.isEmpty(userData.getRole()) && userData.getRole().equals("admin")) {
            Intent intent = new Intent(requireContext(), UpdateFoodActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("documentID", list.get(position).getId());
            bundle.putSerializable("data", adapter.getList().get(position));
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getContext(), DetailFoodActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", adapter.getList().get(position));
            intent.putExtras(bundle);
            startActivity(intent);
        }


    }

    @Override
    public void onDelete(FoodModel foodModel) {
        if (userData != null && !TextUtils.isEmpty(userData.getRole()) && userData.getRole().equals("admin")) {
            new AlertDialog.Builder(requireContext()).setMessage("Bạn có muốn xóa không ?").setPositiveButton("Có", (dialog, which) ->
                    deleteFood(foodModel)).setNegativeButton("Không", (dialog, which) -> {
            }).setIcon(android.R.drawable.ic_dialog_alert).show();
        }

    }

    private void deleteFood(FoodModel foodModel) {
        int result = databaseHandler.deleteProduct(foodModel.getId());
        if (result == -1) {
            ShowMessageHelper.showMessage(getContext(), "Xóa thất bại");
        } else {
            list.remove(foodModel);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllFoods();
    }
}
