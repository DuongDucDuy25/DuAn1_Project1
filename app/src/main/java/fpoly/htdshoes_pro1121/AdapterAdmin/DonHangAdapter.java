package fpoly.htdshoes_pro1121.AdapterAdmin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import fpoly.htdshoes_pro1121.ChiTietDonHangActivity;
import fpoly.htdshoes_pro1121.Dao.DonHangDao;
import fpoly.htdshoes_pro1121.Model.DonHang;
import fpoly.htdshoes_pro1121.Model.KhachHang;
import fpoly.htdshoes_pro1121.Model.SanPham;
import fpoly.htdshoes_pro1121.R;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DonHang> list;
    private DonHangDao dao;
    private HashMap<Integer, String> mapTenSanPham;
    private HashMap<Integer, String> mapTenKhachHang;

    public DonHangAdapter(Context context, ArrayList<DonHang> list, DonHangDao dao) {
        this.context = context;
        this.list = list;
        this.dao = dao;

        mapTenSanPham = new HashMap<>();
        mapTenKhachHang = new HashMap<>();
    }

    public void setListSanPham(ArrayList<SanPham> listSanPham) {
        mapTenSanPham.clear();
        for (SanPham sanPham : listSanPham) {
            mapTenSanPham.put(sanPham.getMaSanPham(), sanPham.getTenSanPham());
        }
        notifyDataSetChanged();
    }
    public void setListKhachHang(ArrayList<KhachHang> listKhachHang){
        mapTenKhachHang.clear();
        for (KhachHang khachHang : listKhachHang) {
            mapTenKhachHang.put(khachHang.getMaKH(), khachHang.getHoTen());
        }
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public DonHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_donhangadmin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangAdapter.ViewHolder holder, int position) {
        DonHang donHang = list.get(position);

        int trangThai = donHang.getTrangThai();

        holder.tvMaDonHang.setText(String.valueOf(donHang.getMaDH()));
        int maKhachHang = donHang.getMaKH();
        String tenKhachHang = mapTenKhachHang.get(maKhachHang);
        holder.tvMaKH.setText(tenKhachHang);
        holder.tvDate.setText(donHang.getDate());
        holder.tvSoDonHang.setText(String.valueOf(donHang.getSoDonHang()));
        holder.tvDonGia.setText(String.valueOf(donHang.getDonGia()));
        int maSanPham = donHang.getMaSanPham();
        String tenSanPham = mapTenSanPham.get(maSanPham);
        holder.tvMaSanPham.setText(tenSanPham);

        if (trangThai == 1) {
            holder.tvTrangThai.setText("Chờ Xác Nhận");
        } else {
            // Xử lý trạng thái khác (nếu cần)
            holder.tvTrangThai.setText(String.valueOf(trangThai));
        }

        holder.lndonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietDonHangActivity.class);
                intent.putExtra("MA_DON_HANG", donHang.getMaDH());
                intent.putExtra("TEN_SAN_PHAM", tenSanPham);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaDonHang,tvMaKH, tvDate, tvSoDonHang, tvDonGia, tvTrangThai, tvMaSanPham;
        LinearLayout lndonHang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaDonHang = itemView.findViewById(R.id.tvMaDH);
            tvMaKH = itemView.findViewById(R.id.tvMaKH);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvSoDonHang = itemView.findViewById(R.id.tvsoDH);
            tvDonGia = itemView.findViewById(R.id.tvDonGia);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            tvMaSanPham = itemView.findViewById(R.id.tvMaSanPham);
            lndonHang = itemView.findViewById(R.id.DonHang);
        }
    }
}