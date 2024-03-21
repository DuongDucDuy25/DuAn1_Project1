package fpoly.htdshoes_pro1121.AdapterUser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpoly.htdshoes_pro1121.Model.KhachHang;
import fpoly.htdshoes_pro1121.R;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.KhachHangViewHolder> {

    private Context context;
    private List<KhachHang> khachHangList;
    private OnKhachHangClickListener listener;
    private OnKhachHangActionListener actionListener;

    public KhachHangAdapter(Context context, List<KhachHang> khachHangList, OnKhachHangClickListener listener, OnKhachHangActionListener actionListener) {
        this.context = context;
        this.khachHangList = khachHangList;
        this.listener = listener;
        this.actionListener = actionListener;
    }

    @NonNull
    @Override
    public KhachHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_khach_hang, parent, false);
        return new KhachHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachHangViewHolder holder, int position) {
        KhachHang khachHang = khachHangList.get(position);
        holder.txtHoTen.setText(khachHang.getHoTen());
        holder.txtDiaChi.setText(khachHang.getDiaChi());
        holder.txtSdt.setText(khachHang.getSdt());

        // Thiết lập sự kiện click cho ImageView edit
        holder.imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionListener != null) {
                    actionListener.onEditKhachHang(position);
                }
            }
        });

        // Thiết lập sự kiện click cho ImageView delete
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionListener != null) {
                    actionListener.onDeleteKhachHang(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return khachHangList.size();
    }

    public class KhachHangViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtHoTen, txtDiaChi, txtSdt;
        ImageView imageViewEdit, imageViewDelete;

        public KhachHangViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHoTen = itemView.findViewById(R.id.textViewHoTen);
            txtDiaChi = itemView.findViewById(R.id.textViewDiaChi);
            txtSdt = itemView.findViewById(R.id.textViewSdt);
            imageViewEdit = itemView.findViewById(R.id.imageViewEdit);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                listener.onKhachHangClick(position);
            }
        }
    }

    // Interface để xử lý sự kiện khi click vào một khách hàng
    public interface OnKhachHangClickListener {
        void onKhachHangClick(int position);
    }

    // Interface để xử lý sự kiện khi click vào ImageView edit và delete
    public interface OnKhachHangActionListener {
        void onEditKhachHang(int position);
        void onDeleteKhachHang(int position);
    }
}
