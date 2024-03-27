package fpoly.htdshoes_pro1121.fragment_admin;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fpoly.htdshoes_pro1121.AdapterAdmin.DonHangAdapter;
import fpoly.htdshoes_pro1121.AdapterAdmin.SanPhamAdminAdapter;
import fpoly.htdshoes_pro1121.Dao.DonHangDao;
import fpoly.htdshoes_pro1121.Dao.SanPhamDao;
import fpoly.htdshoes_pro1121.Model.DonHang;
import fpoly.htdshoes_pro1121.Model.SanPham;
import fpoly.htdshoes_pro1121.R;

public class ChoXacNhanFragment extends Fragment {
    private RecyclerView rcChoXacNhan;
    private DonHangDao dao;
    private androidx.appcompat.widget.SearchView searchView;
    private ArrayList<DonHang> list;
    private EditText edSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cho_xac_nhan, container, false);
        rcChoXacNhan = view.findViewById(R.id.rcChoXacNhan);
        dao = new DonHangDao(getContext());
        list = new ArrayList<>();

        // Lấy danh sách đơn hàng có trạng thái là 1
        List<DonHang> allDonHang = dao.getlistdata();
        for (DonHang donHang : allDonHang) {
            if (donHang.getTrangThai() == 1) {
                list.add(donHang);
            }
        }

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcChoXacNhan.setLayoutManager(manager);
        DonHangAdapter adapter = new DonHangAdapter(getContext(), list, dao);
        rcChoXacNhan.setAdapter(adapter);
        return view;
    }

    // Phương thức để cập nhật danh sách đơn hàng

}