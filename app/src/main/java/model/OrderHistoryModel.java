package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderHistoryModel {
    String documentID;
    String dateOrder;
    String totalPrice;
    int id;
    int phuongthucthanhtoan;
    List<CartModel> foods;
    String diachinhanhang;
    int trangthaidonhang;
    long timestamp;

    public OrderHistoryModel() {
    }

    public OrderHistoryModel(int id, int phuongthucthanhtoan, String dateOrder, String diachinhanhang, List<CartModel> foods, int trangthaidonhang) {
        this.id = id;
        this.dateOrder = dateOrder;
        this.foods = foods;
        this.phuongthucthanhtoan = phuongthucthanhtoan;
        this.diachinhanhang = diachinhanhang;
        this.trangthaidonhang = trangthaidonhang;
    }

    public String getDocumentID() {
        return documentID;
    }

    public int getId() {
        return id;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartModel> getFoods() {
        return foods;
    }

    public void setFoods(List<CartModel> foods) {
        this.foods = foods;
    }

    public int getPhuongthucthanhtoan() {
        return phuongthucthanhtoan;
    }

    public void setPhuongthucthanhtoan(int phuongthucthanhtoan) {
        this.phuongthucthanhtoan = phuongthucthanhtoan;
    }

    public String getDiachinhanhang() {
        return diachinhanhang;
    }

    public void setDiachinhanhang(String diachinhanhang) {
        this.diachinhanhang = diachinhanhang;
    }

    public int getTrangthaidonhang() {
        return trangthaidonhang;
    }

    public void setTrangthaidonhang(int trangthaidonhang) {
        this.trangthaidonhang = trangthaidonhang;
    }

    public String getDate_order() {
        return dateOrder;
    }

    public long getDate_order_timestamp() {
        // Chuyển đổi String dateOrder thành timestamp
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date date = sdf.parse(this.dateOrder);
            return date.getTime(); // Trả về timestamp
        } catch (ParseException e) {
            e.printStackTrace();
            return 0; // Trường hợp xảy ra lỗi
        }
    }


    public void setDate_order_timestamp(long timestamp) {
        this.timestamp = timestamp; // Thiết lập giá trị timestamp
    }
}
