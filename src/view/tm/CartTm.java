package view.tm;

public class CartTm {
    private String Item_Code;
    private int OrderQty;
    private double Discount;

    public CartTm() {
    }

    public CartTm(String item_Code, int orderQty, double discount) {
        Item_Code = item_Code;
        OrderQty = orderQty;
        Discount = discount;
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
}
