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

import fpoly.htdshoes_pro1121.AdapterAdmin.DangVanChuyenAdminAdapter;
import fpoly.htdshoes_pro1121.AdapterAdmin.DonHangAdapter;
import fpoly.htdshoes_pro1121.Dao.DonHangDao;
import fpoly.htdshoes_pro1121.Dao.SanPhamDao;
import fpoly.htdshoes_pro1121.Model.DonHang;
import fpoly.htdshoes_pro1121.Model.SanPham;
import fpoly.htdshoes_pro1121.R;


public class DangVanChuyenFragment extends Fragment {
    private RecyclerView rcDangVanChuyen;
    private DonHangDao dao;
    private androidx.appcompat.widget.SearchView searchView;
    private ArrayList<DonHang> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_van_chuyen, container, false);
        rcDangVanChuyen = view.findViewById(R.id.rcDangVanChuyen);
        dao = new DonHangDao(getContext());
        list = new ArrayList<>();
        ArrayList<SanPham> listSanPham = new SanPhamDao(getContext()).getlistdata(); // Lấy danh sách sản phẩm


        // Lấy danh sách đơn hàng có trạng thái là 1
        List<DonHang> allDonHang = dao.getlistdata();
        for (DonHang donHang : allDonHang) {
            if (donHang.getTrangThai() == 2) {
                list.add(donHang);
            }
        }

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcDangVanChuyen.setLayoutManager(manager);
        DangVanChuyenAdminAdapter adapter = new DangVanChuyenAdminAdapter(getContext(), list, dao);
        adapter.setListSanPham(listSanPham);
        rcDangVanChuyen.setAdapter(adapter);
        return view;
    }
}