package fpoly.htdshoes_pro1121.fragment_admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fpoly.htdshoes_pro1121.AdapterAdmin.DonHangAdapter;
import fpoly.htdshoes_pro1121.AdapterAdmin.HoanThanhDonAdminAdapter;
import fpoly.htdshoes_pro1121.Dao.DonHangDao;
import fpoly.htdshoes_pro1121.Model.DonHang;
import fpoly.htdshoes_pro1121.R;


public class HoanThanhDonFragment extends Fragment {
    private RecyclerView rcHoanThanhDon;
    private DonHangDao dao;
    private androidx.appcompat.widget.SearchView searchView;
    private ArrayList<DonHang> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoan_thanh_don, container, false);
        rcHoanThanhDon = view.findViewById(R.id.rcHoanThanhDon);
        dao = new DonHangDao(getContext());
        list = new ArrayList<>();

        // Lấy danh sách đơn hàng có trạng thái là 1
        List<DonHang> allDonHang = dao.getlistdata();
        for (DonHang donHang : allDonHang) {
            if (donHang.getTrangThai() == 5) {
                list.add(donHang);
            }
        }

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcHoanThanhDon.setLayoutManager(manager);
        HoanThanhDonAdminAdapter adapter = new HoanThanhDonAdminAdapter(getContext(), list, dao);
        rcHoanThanhDon.setAdapter(adapter);
        return view;
    }
}