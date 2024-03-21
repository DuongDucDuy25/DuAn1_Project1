package fpoly.htdshoes_pro1121.fragment_user;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import fpoly.htdshoes_pro1121.AdapterUser.KhachHangAdapter;
import fpoly.htdshoes_pro1121.Dao.KhachHangDao;
import fpoly.htdshoes_pro1121.Model.KhachHang;
import fpoly.htdshoes_pro1121.R;

public class KhachHangFragment extends Fragment implements KhachHangAdapter.OnKhachHangClickListener, KhachHangAdapter.OnKhachHangActionListener {


    private RecyclerView recyclerView;
    private KhachHangAdapter adapter;
    private List<KhachHang> khachHangList;
    private KhachHangDao khachHangDao;

    private EditText editTextHoTen, editTextDiaChi, editTextSDT;
    private Button buttonCancel, buttonSave;

    private FloatingActionButton fabAddKhachHang;

    private int selectedPosition = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khach_hang, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewKhachHang);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        khachHangList = new ArrayList<>();
        adapter = new KhachHangAdapter(getActivity(), khachHangList, this, this);
        recyclerView.setAdapter(adapter);
        fabAddKhachHang = view.findViewById(R.id.fabAddKhachHang); // Ánh xạ float button


        khachHangDao = new KhachHangDao(getActivity());
        fabAddKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddKhachHangDialog(); // Hiển thị dialog thêm khách hàng khi float button được click
            }
        });

        // Load danh sách khách hàng từ cơ sở dữ liệu
        loadKhachHangs();

        return view;
    }

    // Phương thức này để load danh sách khách hàng từ cơ sở dữ liệu
    private void loadKhachHangs() {
        khachHangList.clear();
        khachHangList.addAll(khachHangDao.getAllKhachHang());
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onKhachHangClick(int position) {
        // Xử lý sự kiện khi click vào một khách hàng
        // Ví dụ: Hiển thị thông tin chi tiết, sửa thông tin, xóa, vv.
        KhachHang khachHang = khachHangList.get(position);
        Toast.makeText(getActivity(), "Clicked on: " + khachHang.getHoTen(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onEditKhachHang(int position) {
        selectedPosition = position;
        KhachHang khachHang = khachHangList.get(position);
        showDialogEditKhachHang(khachHang);
    }

    @Override
    public void onDeleteKhachHang(int position) {
        showDeleteConfirmationDialog(position);
    }
    private void showAddKhachHangDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_khach_hang, null);
        builder.setView(dialogView)
                .setTitle("Thêm Khách Hàng")
                .setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý thêm khách hàng ở đây
                        EditText editTextHoTen = dialogView.findViewById(R.id.editTextHoTen);
                        EditText editTextDiaChi = dialogView.findViewById(R.id.editTextDiaChi);
                        EditText editTextSDT = dialogView.findViewById(R.id.editTextSDT);
                        // Lấy thông tin từ EditTexts
                        String hoTen = editTextHoTen.getText().toString().trim();
                        String diaChi = editTextDiaChi.getText().toString().trim();
                        String sdt = editTextSDT.getText().toString().trim();

                        // Kiểm tra các trường có được nhập hay không
                        if (hoTen.isEmpty() || diaChi.isEmpty() || sdt.isEmpty()) {
                            Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        } else {
                            // Tạo một đối tượng KhachHang mới
                            KhachHang khachHang = new KhachHang(hoTen, diaChi, sdt);

                            // Thêm khách hàng vào cơ sở dữ liệu
                            long result = khachHangDao.insertKhachHang(khachHang);
                            if (result > 0) {
                                // Nếu thêm thành công, cập nhật lại danh sách và thông báo
                                loadKhachHangs();
                                Toast.makeText(getActivity(), "Thêm khách hàng thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Thêm khách hàng thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Đóng dialog khi hủy bỏ
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Hiển thị dialog chỉnh sửa thông tin khách hàng
    private void showDialogEditKhachHang(KhachHang khachHang) {
        // Thực hiện việc inflate layout của dialog_edit_khach_hang
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_khach_hang, null);

        // Ánh xạ các view trong dialog
        editTextHoTen = dialogView.findViewById(R.id.editTextHoTen);
        editTextDiaChi = dialogView.findViewById(R.id.editTextDiaChi);
        editTextSDT = dialogView.findViewById(R.id.editTextSDT);
        buttonCancel = dialogView.findViewById(R.id.buttonCancel);
        buttonSave = dialogView.findViewById(R.id.buttonSave);

        // Set thông tin hiện tại của khách hàng vào EditText
        editTextHoTen.setText(khachHang.getHoTen());
        editTextDiaChi.setText(khachHang.getDiaChi());
        editTextSDT.setText(khachHang.getSdt());

        // Tạo dialog và hiển thị
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        builder.setView(dialogView);
        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.show();

        // Xử lý sự kiện khi click vào nút "Hủy"
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); // Đóng dialog khi click vào nút "Hủy"
            }
        });

        // Xử lý sự kiện khi click vào nút "Lưu"
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin mới từ EditText
                String hoTen = editTextHoTen.getText().toString().trim();
                String diaChi = editTextDiaChi.getText().toString().trim();
                String sdt = editTextSDT.getText().toString().trim();

                // Kiểm tra xem các trường dữ liệu có được nhập đầy đủ không
                if (hoTen.isEmpty() || diaChi.isEmpty() || sdt.isEmpty()) {
                    Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tạo đối tượng KhachHang mới từ các thông tin đã nhập
                KhachHang newKhachHang = new KhachHang();
                newKhachHang.setMaKH(khachHang.getMaKH()); // Giữ nguyên mã khách hàng
                newKhachHang.setHoTen(hoTen);
                newKhachHang.setDiaChi(diaChi);
                newKhachHang.setSdt(sdt);

                // Cập nhật thông tin khách hàng trong cơ sở dữ liệu
                int result = khachHangDao.updateKhachHang(newKhachHang);
                if (result > 0) {
                    // Nếu cập nhật thành công, cập nhật lại danh sách khách hàng
                    khachHangList.set(selectedPosition, newKhachHang);
                    adapter.notifyItemChanged(selectedPosition);
                    Toast.makeText(getActivity(), "Updated khach hang successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Failed to update khach hang", Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss(); // Đóng dialog sau khi cập nhật xong.
            }
        });
    }
    // Hiển thị dialog xác nhận xóa khách hàng
    private void showDeleteConfirmationDialog(int position) {
        KhachHang khachHang = khachHangList.get(position);
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(requireContext());
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa khách hàng " + khachHang.getHoTen() + " không?");
        builder.setPositiveButton("Xóa", (dialog, which) -> {
            // Xác nhận xóa, thực hiện xóa khách hàng
            deleteKhachHang(position);
        });
        builder.setNegativeButton("Hủy", (dialog, which) -> {
            // Người dùng không muốn xóa, đóng dialog
            dialog.dismiss();
        });
        builder.create().show();
    }
    // Xóa khách hàng từ danh sách và cơ sở dữ liệu
    private void deleteKhachHang(int position) {
        KhachHang khachHang = khachHangList.get(position);
        khachHangDao.deleteKhachHang(khachHang.getMaKH());
        khachHangList.remove(position);
        adapter.notifyItemRemoved(position);
        Toast.makeText(requireContext(), "Đã xóa khách hàng " + khachHang.getHoTen(), Toast.LENGTH_SHORT).show();
    }

}

