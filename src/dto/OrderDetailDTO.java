package dto;

public class OrderDetailDTO {
    private String OrderId;
    private String Item_Code;
    private int OrderQty;
    private double Discount ;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderId, String item_Code, int orderQty, double discount) {
        OrderId = orderId;
        Item_Code = item_Code;
        OrderQty = orderQty;
        Discount = discount;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getItem_Code() {
        return Item_Code;
    }

    public void setItem_Code(String item_Code) {
        Item_Code = item_Code;
    }

    public int getOrderQty() {
        return OrderQty;
    }

    public void setOrderQty(int orderQty) {
        OrderQty = orderQty;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "OrderId='" + OrderId + '\'' +
                ", Item_Code='" + Item_Code + '\'' +
                ", OrderQty=" + OrderQty +
                ", Discount=" + Discount +
                '}';
    }
}
