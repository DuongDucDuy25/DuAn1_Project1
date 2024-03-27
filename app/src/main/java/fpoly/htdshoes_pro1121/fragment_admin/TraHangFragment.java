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
import fpoly.htdshoes_pro1121.AdapterAdmin.TraHangAdminAdapter;
import fpoly.htdshoes_pro1121.Dao.DonHangDao;
import fpoly.htdshoes_pro1121.Model.DonHang;
import fpoly.htdshoes_pro1121.R;


public class TraHangFragment extends Fragment {
    private RecyclerView rcTraHang;
    private DonHangDao dao;
    private androidx.appcompat.widget.SearchView searchView;
    private ArrayList<DonHang> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tra_hang, container, false);
        rcTraHang = view.findViewById(R.id.rcTraHang);
        dao = new DonHangDao(getContext());
        list = new ArrayList<>();

        // Lấy danh sách đơn hàng có trạng thái là 1
        List<DonHang> allDonHang = dao.getlistdata();
        for (DonHang donHang : allDonHang) {
            if (donHang.getTrangThai() == 3) {
                list.add(donHang);
            }
        }

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcTraHang.setLayoutManager(manager);
        TraHangAdminAdapter adapter = new TraHangAdminAdapter(getContext(), list, dao);
        rcTraHang.setAdapter(adapter);
        return view;
    }
}