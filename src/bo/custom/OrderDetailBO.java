package bo.custom;

import bo.SuperBO;
import dto.OrdersDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailBO extends SuperBO {

    boolean placeOrder(OrdersDTO ordersDTO) throws ClassNotFoundException, SQLException, Exception;

    int getOrderCount() throws SQLException, ClassNotFoundException;

    ArrayList<OrdersDTO> getAllOrders() throws SQLException, ClassNotFoundException;

    String getLastOrderId() throws SQLException, ClassNotFoundException;


}
