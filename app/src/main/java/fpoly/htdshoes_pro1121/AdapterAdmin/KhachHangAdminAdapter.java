package fpoly.htdshoes_pro1121.AdapterAdmin;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.htdshoes_pro1121.Dao.KhachHangDao;
import fpoly.htdshoes_pro1121.Dao.SanPhamDao;
import fpoly.htdshoes_pro1121.Model.KhachHang;
import fpoly.htdshoes_pro1121.Model.SanPham;
import fpoly.htdshoes_pro1121.R;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHolder> implements Filterable {
    private Context context;
    private ArrayList<KhachHang> list;
    private ArrayList<KhachHang> listKhachHang;
    private KhachHangDao dao;
    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public KhachHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanpham,parent,false);
        return new KhachHangAdapter.ViewHolder(view);
    }
    public KhachHangAdapter(Context context, ArrayList<KhachHang> list, KhachHangDao dao) {
        this.context = context;
        this.listKhachHang = new ArrayList<>(list);
        this.list = list;
        this.dao = dao;

    }

    @Override
    public void onBindViewHolder(@NonNull KhachHangAdapter.ViewHolder holder, int position) {
        holder.tvMaKH.setText("Mã :" + String.valueOf(list.get(position).getMaKH()));
        holder.tvTenKH.setText("Tên :" +list.get(position).getTenKH());
        holder.tvDiaChi.setText("Địa Chỉ :" +list.get(position).getDiaChi());
        holder.tvSDT.setText("SDT :" +list.get(position).getSdt());

        holder.KhachHangAdmin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvMaKH,tvTenKH,tvDiaChi,tvSDT;
        LinearLayout KhachHangAdmin;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaKH = itemView.findViewById(R.id.tvMaKH);
            tvTenKH = itemView.findViewById(R.id.tvTenKH);
            tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
            tvSDT = itemView.findViewById(R.id.tvSDT);
            KhachHangAdmin = itemView.findViewById(R.id.KhachHangAdmin);
        }
    }

}
