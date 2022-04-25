package model;

public class Orders {
    private String OrderId;
    private String Cust_id;
    private String Order_Date;
    private Double Cost;


    public Orders() {
    }

    public Orders(String orderId, String cust_id, String order_Date,Double cost) {
        OrderId = orderId;
        Cust_id = cust_id;
        Order_Date = order_Date;
        Cost= cost;
    }



    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getCust_id() {
        return Cust_id;
    }

    public void setCust_id(String cust_id) {
        Cust_id = cust_id;
    }

    public String getOrder_Date() {
        return Order_Date;
    }

    public void setOrder_Date(String order_Date) {
        Order_Date = order_Date;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "OrderId='" + OrderId + '\'' +
                ", Cust_id='" + Cust_id + '\'' +
                ", Order_Date='" + Order_Date + '\'' +
                '}';
    }

    public Double getCost() {
        return Cost;
    }

    public void setCost(Double cost) {
        this.Cost = cost;
    }
}
