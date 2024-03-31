package fpoly.htdshoes_pro1121.AdapterAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import fpoly.htdshoes_pro1121.Dao.ChiTietDonHangDao;
import fpoly.htdshoes_pro1121.Dao.DonHangDao;
import fpoly.htdshoes_pro1121.Model.ChiTietDonHang;
import fpoly.htdshoes_pro1121.Model.DonHang;
import fpoly.htdshoes_pro1121.Model.KhachHang;
import fpoly.htdshoes_pro1121.Model.SanPham;
import fpoly.htdshoes_pro1121.R;

public class ChiTietDonHangAdapter extends RecyclerView.Adapter<ChiTietDonHangAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ChiTietDonHang> list;
    private ChiTietDonHangDao dao;
    private HashMap<Integer, String> mapTenSanPham;
    private HashMap<Integer, String> mapTenKhachHang;
    private ArrayList<DonHang> listDonHang;

    public ChiTietDonHangAdapter(Context context, ArrayList<ChiTietDonHang> list, ChiTietDonHangDao dao, ArrayList<DonHang> listDonHang) {
        this.context = context;
        if (list != null) {
            this.list = list;
        } else {
            this.list = new ArrayList<>(); // Khởi tạo một ArrayList mới
        }
        this.dao = dao;
        this.listDonHang = listDonHang;
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

    public void setListKhachHang(ArrayList<KhachHang> listKhachHang) {
        mapTenKhachHang.clear();
        for (KhachHang khachHang : listKhachHang) {
            mapTenKhachHang.put(khachHang.getMaKH(), khachHang.getHoTen());
        }
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ChiTietDonHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_chitietdonhang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietDonHangAdapter.ViewHolder holder, int position) {
        ChiTietDonHang chiTietDonHang = list.get(position);
        int trangThai = chiTietDonHang.getStatus();
        int maCTDH = chiTietDonHang.getMaCTDH();

        // Tìm đơn hàng tương ứng
        DonHang donHang = findDonHangByMaCTDH(maCTDH);
        if (donHang != null) {
            int maDH = donHang.getMaDH(); // Lấy mã DH từ đơn hàng
            holder.tvMaCTDH.setText(String.valueOf(maDH)); // Hiển thị mã CTDH là mã DH
            int maKhachhang = chiTietDonHang.getMaKH();
            String tenKhachHang = mapTenKhachHang.get(maKhachhang);
            holder.tvMaKH.setText(tenKhachHang);
            holder.tvMaDH.setText(String.valueOf(maDH)); // Hiển thị mã DH
            holder.tvSoLuong.setText(String.valueOf(chiTietDonHang.getSoLuong())); // Hiển thị số lượng
            // Cập nhật các thông tin khác của chi tiết đơn hàng
            int maSanPham = chiTietDonHang.getMaSanPham();
            String tenSanPham = mapTenSanPham.get(maSanPham);
            holder.tvMaSanPham.setText(tenSanPham);
            if (trangThai == 1) {
                holder.tvStatus.setText("Chờ Xác Nhận");
            } else {
                // Xử lý trạng thái khác (nếu cần)
                holder.tvStatus.setText(String.valueOf(trangThai));
            }
            // ...
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaCTDH, tvMaKH, tvMaDH, tvSoLuong, tvMaSanPham, tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaCTDH = itemView.findViewById(R.id.tvMaCTDH);
            tvMaKH = itemView.findViewById(R.id.tvMaKH);
            tvMaDH = itemView.findViewById(R.id.tvMaDH);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            tvMaSanPham = itemView.findViewById(R.id.tvMaSanPham);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }

    private DonHang findDonHangByMaCTDH(int maCTDH) {
        for (DonHang donHang : listDonHang) {
            if (donHang.getMaDH() == maCTDH) { // Tìm kiếm theo mã CTDH
                return donHang;
            }
        }
        return null;
    }
}
