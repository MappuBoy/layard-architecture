package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDetailDAO;
import model.OrderDetail;
import model.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public boolean add(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO order_detail VALUES(?,?,?,?)";
        return CrudUtil.executeUpdate(query, orderDetail.getOrderId(), orderDetail.getItem_Code(),
                orderDetail.getOrderQty(), orderDetail.getDiscount());
    }

    @Override
    public boolean update(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        String query = "UPDATE order_detail set Item_Code=?, OrderQty=?, Discount=? WHERE OrderId=?";
        return CrudUtil.executeUpdate(query, orderDetail.getItem_Code(), orderDetail.getOrderQty(),
                orderDetail.getDiscount(), orderDetail.getOrderId());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM order_detail WHERE OrderId=?";
        return CrudUtil.executeUpdate(query, s);
    }

    @Override
    public OrderDetail search(String s) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM order_detail WHERE OrderId=?";
        ResultSet resultSet = CrudUtil.executeQuery(query, s);
        while (resultSet.next()) {
            return new OrderDetail(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getInt(3), resultSet.getDouble(4));
        }
        return null;
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM order_detail";
        ResultSet resultSet = CrudUtil.executeQuery(query);

        ArrayList<OrderDetail> orderDetailArrayList = new ArrayList<>();

        while (resultSet.next()) {
            orderDetailArrayList.add(new OrderDetail(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getInt(3), resultSet.getDouble(4)));
        }
        return orderDetailArrayList;
    }
}
