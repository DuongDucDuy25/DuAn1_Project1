package fpoly.htdshoes_pro1121;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.htdshoes_pro1121.AdapterAdmin.KhachHangAdminAdapter;
import fpoly.htdshoes_pro1121.Dao.KhachHangDao;
import fpoly.htdshoes_pro1121.Model.KhachHang;
import fpoly.htdshoes_pro1121.Model.SanPham;

public class KhachHangActivity extends AppCompatActivity {
    private RecyclerView rcKhachHang;
    private KhachHangDao dao;
    private ArrayList<KhachHang> list;
    private Toolbar mtoolbar;
    private androidx.appcompat.widget.SearchView searchView;
    private KhachHangAdminAdapter adapter;
    private FloatingActionButton floatAddKhachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);

        rcKhachHang = findViewById(R.id.rcKhachHang);
        mtoolbar = findViewById(R.id.toolbar);
        floatAddKhachHang = findViewById(R.id.floatAddKhachHang);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Khách Hàng");

        dao = new KhachHangDao(this);
        loadData();
        floatAddKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           showDiaLogAdd();
            }
        });
    }

    private void loadData() {
        list = (ArrayList<KhachHang>) dao.getAllKhachHang();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcKhachHang.setLayoutManager(manager);
        adapter = new KhachHangAdminAdapter(this, list, dao);
        rcKhachHang.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_khachhangadmin, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        searchView = (androidx.appcompat.widget.SearchView) searchMenuItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showDiaLogAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_khachhangadmin, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edTenKhachHang = view.findViewById(R.id.edNameKhachHang);
        EditText edDiaChi = view.findViewById(R.id.edDiaChi);
        EditText edSDT = view.findViewById(R.id.edSDT);
        Button btnAdd = view.findViewById(R.id.btnAddKhachHang);
        Button btnCancel = view.findViewById(R.id.btnCancelKhachHang);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edTenKhachHang.getText().toString();
                String diachi = edDiaChi.getText().toString();
                String sdt = edSDT.getText().toString();

                if (ten.length() == 0 || diachi.length() == 0 || sdt.length() == 0 ) {
                    Toast.makeText(KhachHangActivity.this, "Nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                } else {
                    KhachHang khachHang = new KhachHang(ten, diachi, sdt);
                    long check = dao.insertKhachHang(khachHang);
                    if (check !=-1) {
                        Toast.makeText(KhachHangActivity.this, "Thêm Thành Công !", Toast.LENGTH_SHORT).show();
                        loadData();
                        alertDialog.dismiss();
                    } else {
                        Toast.makeText(KhachHangActivity.this, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
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