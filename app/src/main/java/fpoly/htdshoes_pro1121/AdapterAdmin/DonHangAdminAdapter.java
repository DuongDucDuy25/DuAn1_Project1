package fpoly.htdshoes_pro1121.AdapterAdmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpoly.htdshoes_pro1121.Model.DonHang;
import fpoly.htdshoes_pro1121.R;

public class DonHangAdminAdapter extends RecyclerView.Adapter<DonHangAdminAdapter.DonHangViewHolder>{
    private List<DonHang> donHangList;

    public DonHangAdminAdapter(List<DonHang> donHangList) {
        this.donHangList = donHangList;
    }
    @NonNull
    @Override
    public DonHangAdminAdapter.DonHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdonhang, parent, false);
        return new DonHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangAdminAdapter.DonHangViewHolder holder, int position) {
        DonHang donHang = donHangList.get(position);
        if (donHang != null) {
            holder.tvMaDonHang.setText(donHang.getMaDonHang());
            holder.tvDate.setText(donHang.getDate());
            holder.tvSoDonHang.setText(donHang.getSoDonHang());
            holder.tvDonGia.setText(donHang.getDonGia());
            holder.tvTrangThai.setText(donHang.getTrangthai());
        }
    }

    @Override
    public int getItemCount() {
        return donHangList.size();
    }
    public void updateDonHangList(List<DonHang> newDonHangList) {
        this.donHangList = newDonHangList;
        notifyDataSetChanged();
    }

    public class DonHangViewHolder extends RecyclerView.ViewHolder{

        TextView tvMaDonHang, tvDate, tvSoDonHang, tvDonGia, tvTrangThai;
        public DonHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaDonHang = itemView.findViewById(R.id.tvMaDonHang);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvSoDonHang = itemView.findViewById(R.id.tvsoDonHang);
            tvDonGia = itemView.findViewById(R.id.tvDonGia);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
        }
    }
}
