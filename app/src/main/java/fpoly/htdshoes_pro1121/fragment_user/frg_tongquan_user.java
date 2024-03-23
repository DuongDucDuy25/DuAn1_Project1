package fpoly.htdshoes_pro1121.fragment_user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import fpoly.htdshoes_pro1121.AdapterAdmin.SanPhamAdminAdapter;
import fpoly.htdshoes_pro1121.AdapterUser.SanPham_user_Adapter;
import fpoly.htdshoes_pro1121.Dao.SanPhamDao;
import fpoly.htdshoes_pro1121.Model.SanPham;
import fpoly.htdshoes_pro1121.R;


public class frg_tongquan_user extends Fragment {

    private RecyclerView rcvSanPham;
    private SanPhamDao dao;
    private androidx.appcompat.widget.SearchView searchView;
    private LinearLayout btnThemSP, btnSuaSp;
    private ArrayList<SanPham> list;

    public frg_tongquan_user() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_frg_tongquan_user, container, false);
        rcvSanPham = view.findViewById(R.id.rcv_dssp);
        dao = new SanPhamDao(getContext());
        loadData();
        return view;
    }
    private void loadData() {
        ArrayList<SanPham> list = dao.getlistdata();
    //    int spanCount = 2;


     //   int orientation = manager.HORIZONTAL;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rcvSanPham.setLayoutManager(gridLayoutManager);

        SanPham_user_Adapter adapter = new SanPham_user_Adapter(getContext(), list, dao);
        rcvSanPham.setAdapter(adapter);


    }
}