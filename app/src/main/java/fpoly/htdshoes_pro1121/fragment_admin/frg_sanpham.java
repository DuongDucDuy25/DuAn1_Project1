package fpoly.htdshoes_pro1121.fragment_admin;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.htdshoes_pro1121.AdapterAdmin.SanPhamAdminAdapter;
import fpoly.htdshoes_pro1121.Dao.SanPhamDao;
import fpoly.htdshoes_pro1121.Model.SanPham;
import fpoly.htdshoes_pro1121.R;


public class frg_sanpham extends Fragment {

    private RecyclerView rcSanPham;
    private SanPhamDao dao;
    private androidx.appcompat.widget.SearchView searchView;
    private LinearLayout btnThemSP, btnSuaSp;
    private ArrayList<SanPham> list;
    private ArrayList<SanPham> temstlist;
    private EditText edSearch;



    public frg_sanpham() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frg_sanpham, container, false);

        rcSanPham = view.findViewById(R.id.rcSanPham);
        btnThemSP = view.findViewById(R.id.btnThemSanPham);
        edSearch = view.findViewById(R.id.edSearch);
        dao = new SanPhamDao(getContext());
         list = dao.getlistdata();
         temstlist = dao.getlistdata();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcSanPham.setLayoutManager(manager);
        SanPhamAdminAdapter adapter = new SanPhamAdminAdapter(getContext(), list, dao);
        rcSanPham.setAdapter(adapter);



        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                list.clear();
                for (SanPham pm:temstlist) {
                    if(String.valueOf(pm.getTenSanPham()).
                            contains(charSequence.toString())){
                        list.add(pm);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaLogAdd();
            }
        });

        return view;
    }

    private void loadData() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        this.searchView = (androidx.appcompat.widget.SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<SanPham> list = dao.getlistdata();
                ArrayList<SanPham> filteredList = new ArrayList<>();

                for (SanPham sanPham : list) {
                    if (sanPham.getTenSanPham().toLowerCase().contains(newText.toLowerCase())) {
                        filteredList.add(sanPham);
                    }
                }

                SanPhamAdminAdapter adapter = new SanPhamAdminAdapter(getContext(), filteredList, dao) {
                    @Override
                    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                        super.onBindViewHolder(holder, position);
                        holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                    }
                };

                rcSanPham.setAdapter(adapter);

                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);

    }

    private void showDiaLogAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_addsanpham, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edTenSanPham = view.findViewById(R.id.edNameSanPham);
        EditText edMaCTSP = view.findViewById(R.id.edMaCTSP);
        EditText edMaTL = view.findViewById(R.id.edMaTL);
        EditText edSoLuong = view.findViewById(R.id.edSoLuong);
        EditText edGiaTien = view.findViewById(R.id.edGiaTien);
        Button btnAdd = view.findViewById(R.id.btnAddSanPham);
        Button btnCancel = view.findViewById(R.id.btnCancelSanPham);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edTenSanPham.getText().toString();
                String mactsp = edMaCTSP.getText().toString();
                String matl = edMaTL.getText().toString();
                String soluong = edSoLuong.getText().toString();
                String giatien = edGiaTien.getText().toString();

                if (ten.length() == 0 || mactsp.length() == 0 || matl.length() == 0 || soluong.length() == 0) {
                    Toast.makeText(getContext(), "Nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                } else {
                    SanPham sanpham = new SanPham(ten, Integer.parseInt(mactsp), Integer.parseInt(matl), Integer.parseInt(soluong), Integer.parseInt(giatien));
                    boolean check = dao.addSanPham(sanpham);
                    if (check) {
                        Toast.makeText(getContext(), "Thêm Thành Công !", Toast.LENGTH_SHORT).show();
                        loadData();
                        alertDialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

}