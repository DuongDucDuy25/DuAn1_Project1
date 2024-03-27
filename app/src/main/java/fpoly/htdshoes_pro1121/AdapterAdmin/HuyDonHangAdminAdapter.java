package fpoly.htdshoes_pro1121.AdapterAdmin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.htdshoes_pro1121.Dao.DonHangDao;
import fpoly.htdshoes_pro1121.Model.DonHang;
import fpoly.htdshoes_pro1121.R;

public class HuyDonHangAdminAdapter extends RecyclerView.Adapter<HuyDonHangAdminAdapter.ViewHolder>{
    private Context context;
    private ArrayList<DonHang> list;
    private ArrayList<DonHang> listDonhang;
    private DonHangDao dao;

    public HuyDonHangAdminAdapter(Context context, ArrayList<DonHang> list, DonHangDao dao) {
        this.context = context;
        this.list = list;
        this.listDonhang = new ArrayList<>(list);
        this.dao = dao;
    }
    @NonNull
    @Override
    public HuyDonHangAdminAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_donhangadmin,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HuyDonHangAdminAdapter.ViewHolder holder, int position) {
        DonHang donHang = list.get(position);
        int trangThai = donHang.getTrangThai();

        holder.tvMaDonHang.setText(String.valueOf(donHang.getMaDH()));
        holder.tvDate.setText(donHang.getDate());
        holder.tvSoDonHang.setText(String.valueOf(donHang.getSoDonHang()));
        holder.tvDonGia.setText(String.valueOf(donHang.getDonGia()));

        if (trangThai == 4) {
            holder.tvTrangThai.setText("Hủy Đơn Hàng");
        } else {
            // Xử lý trạng thái khác (nếu cần)
            holder.tvTrangThai.setText(String.valueOf(trangThai));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaDonHang,tvDate,tvSoDonHang,tvDonGia,tvTrangThai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaDonHang = itemView.findViewById(R.id.tvMaDH);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvSoDonHang = itemView.findViewById(R.id.tvsoDH);
            tvDonGia = itemView.findViewById(R.id.tvDonGia);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);

        }
    }
}
