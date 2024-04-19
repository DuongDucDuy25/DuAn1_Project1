package dsnguoidung;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpoly.htdshoes_pro1121.R;
import model.UserData;

public class DanhSachNguoiDungAdapter extends RecyclerView.Adapter<DanhSachNguoiDungAdapter.DsNguoiDungViewHolder> {

    private List<UserData> list;
    private OnUserDeleteListener deleteListener;

    public DanhSachNguoiDungAdapter(List<UserData> list) {
        this.list = list;
    }

    public void setOnUserDeleteListener(OnUserDeleteListener listener) {
        this.deleteListener = listener;
    }

    @NonNull
    @Override
    public DsNguoiDungViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_nguoidung, parent, false);
        return new DsNguoiDungViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DsNguoiDungViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeUser(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    class DsNguoiDungViewHolder extends RecyclerView.ViewHolder {

        private TextView tentaikhoan;
        private TextView hovaten;
        private TextView diachi;
        private TextView sodienthoai;
        private ImageView imgDelete;

        public DsNguoiDungViewHolder(@NonNull View itemView) {
            super(itemView);
            tentaikhoan = itemView.findViewById(R.id.tentaikhoan);
            hovaten = itemView.findViewById(R.id.hovaten);
            diachi = itemView.findViewById(R.id.diachi);
            sodienthoai = itemView.findViewById(R.id.sodienthoai);
            imgDelete = itemView.findViewById(R.id.imgDelete);
        }

        public void bind(UserData userData) {
            tentaikhoan.setText("Tên tài khoản: " + userData.getUserName());
            hovaten.setText("Họ tên: " + userData.getFullName());
            diachi.setText("Địa chỉ: " + userData.getAddress());
            sodienthoai.setText("Số điện thoại: " + userData.getPhone());

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (deleteListener != null) {
                        deleteListener.onUserDelete(getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface OnUserDeleteListener {
        void onUserDelete(int position);
    }
}