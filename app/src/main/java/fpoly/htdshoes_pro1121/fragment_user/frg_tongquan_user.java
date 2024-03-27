package fpoly.htdshoes_pro1121.fragment_user;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import fpoly.htdshoes_pro1121.AdapterUser.SanPham_user_Adapter;
import fpoly.htdshoes_pro1121.Dao.SanPhamDao;
import fpoly.htdshoes_pro1121.Model.SanPham;
import fpoly.htdshoes_pro1121.R;

public class frg_tongquan_user extends Fragment {

    private RecyclerView rcvSanPham;
    private SanPhamDao dao;
    private Spinner spinnerCategories;
    private ArrayList<SanPham> list;

    public frg_tongquan_user() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frg_tongquan_user, container, false);
        rcvSanPham = view.findViewById(R.id.rcv_dssp);
        spinnerCategories = view.findViewById(R.id.spinner_categories);
        dao = new SanPhamDao(getContext());

        // Set up Spinner
        setUpSpinner();

        // Load all products by default
        loadData(0); // Pass 0 to indicate loading all products

        return view;
    }

    private void setUpSpinner() {
        // Create an array containing all categories plus "Tất cả sản phẩm"
        String[] categories = getResources().getStringArray(R.array.shoe_categories);
        String[] categoriesWithAll = new String[categories.length + 1];
        categoriesWithAll[0] = "Tất cả sản phẩm";
        System.arraycopy(categories, 0, categoriesWithAll, 1, categories.length);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, categoriesWithAll);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(adapter);

        // Handle spinner item selection
        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // Load all products
                    loadData(0);
                } else {
                    // Load products by category
                    int categoryId = position; // No need to add 1 because position 0 is "Tất cả sản phẩm"
                    loadData(categoryId);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void loadData(int categoryId) {
        if (categoryId == 0) {
            // Load all products
            list = dao.getlistdata();
        } else {
            // Load products by category
            list = dao.getListDataByCategoryId(categoryId);
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rcvSanPham.setLayoutManager(gridLayoutManager);

        SanPham_user_Adapter adapter = new SanPham_user_Adapter(getContext(), list);
        rcvSanPham.setAdapter(adapter);
    }
}

