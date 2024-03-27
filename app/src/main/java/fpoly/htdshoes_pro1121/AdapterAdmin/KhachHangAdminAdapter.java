package fpoly.htdshoes_pro1121.AdapterAdmin;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.htdshoes_pro1121.Dao.KhachHangDao;
import fpoly.htdshoes_pro1121.Model.KhachHang;
import fpoly.htdshoes_pro1121.Model.SanPham;
import fpoly.htdshoes_pro1121.R;

public class KhachHangAdminAdapter extends RecyclerView.Adapter<KhachHangAdminAdapter.ViewHolder> implements Filterable {
    private Context context;
    private ArrayList<KhachHang> list;
    private ArrayList<KhachHang> listKhachHang;
    private KhachHangDao dao;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_khachhang,parent,false);
        return new ViewHolder(view);
    }
    public KhachHangAdminAdapter(Context context, ArrayList<KhachHang> list, KhachHangDao dao) {
        this.context = context;
        this.listKhachHang = new ArrayList<>(list);
        this.list = list;
        this.dao = dao;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMaKH.setText("Mã :" + String.valueOf(list.get(position).getMaKH()));
        holder.tvTenKH.setText("Tên :" +list.get(position).getHoTen());
        holder.tvDiaChi.setText("Địa Chỉ :" +list.get(position).getDiaChi());
        holder.tvSDT.setText("SDT :" +list.get(position).getSdt());

        holder.KhachHangAdmin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showDialogUpdate(list.get(holder.getAdapterPosition()));
                return false;
            }
        });
        holder.imgDeleteKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDelete(list.get(holder.getAdapterPosition()).getMaKH());
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
        ImageView imgDeleteKhachHang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaKH = itemView.findViewById(R.id.tvMaKH);
            tvTenKH = itemView.findViewById(R.id.tvTenKH);
            tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
            tvSDT = itemView.findViewById(R.id.tvSDT);
            imgDeleteKhachHang = itemView.findViewById(R.id.imgDeleteKhachHang);
            KhachHangAdmin = itemView.findViewById(R.id.KhachHangAdmin);
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()) {
                    list = new ArrayList<>(listKhachHang);
                } else {
                    ArrayList<KhachHang> KhachHangArrayList = new ArrayList<>();
                    for (KhachHang khachHang : listKhachHang) {
                        if (khachHang.getHoTen().toLowerCase().contains(strSearch.toLowerCase())) {
                            KhachHangArrayList.add(khachHang);
                        }
                    }
                    list = KhachHangArrayList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<KhachHang>) results.values;
                notifyDataSetChanged();
            }
        };
    }
    private void showDialogUpdate(KhachHang khachHang){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_khachhangadmin,null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edMaKhachHang = view.findViewById(R.id.edMaKhachHang);
        EditText edTenKhachHang = view.findViewById(R.id.edNameKhachHang);
        EditText eddiachi = view.findViewById(R.id.eddiachi);
        EditText edsdt = view.findViewById(R.id.edsdt);
        Button btnUpdate = view.findViewById(R.id.btnUpdateKhachhang);
        Button btnCancel = view.findViewById(R.id.btnCancelKhachhang);

        edMaKhachHang.setText(String.valueOf(khachHang.getMaKH()));
        edTenKhachHang.setText(khachHang.getHoTen());
        eddiachi.setText(khachHang.getDiaChi());
        edsdt.setText(khachHang.getSdt());
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int makhachhang = khachHang.getMaKH();
                String tenkhachhang = edTenKhachHang.getText().toString();
                String diachi = eddiachi.getText().toString();
                String sdt = edsdt.getText().toString();

                KhachHang khachhangupdate = new KhachHang(makhachhang, tenkhachhang, diachi,sdt);
                int check = dao.updateKhachHang(khachhangupdate);
                if (check != -1){
                    Toast.makeText(context, "Cập nhật thành công !", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list = (ArrayList<KhachHang>) dao.getAllKhachHang();
                    notifyDataSetChanged();
                    alertDialog.dismiss();
                }
                else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void showDialogDelete(int makhachhang){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông Báo");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setMessage("Bạn có muốn xóa quyển sách này không ?");

        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean check = dao.deleteKhachHang(makhachhang);
                if (check){
                    Toast.makeText(context, "Xóa thành công !", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list = (ArrayList<KhachHang>) dao.getAllKhachHang();
                    notifyDataSetChanged();
                }
                else {
                    Toast.makeText(context, "Xóa thất bại !", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setNegativeButton("Cancel",null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
