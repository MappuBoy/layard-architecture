package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDAO;
import model.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean add(Orders orders) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO orders VALUES(?,?,?,?)";
        return CrudUtil.executeUpdate(query, orders.getOrderId(), orders.getCust_id(), orders.getOrder_Date(),
                orders.getCost());
    }

    @Override
    public boolean update(Orders orders) throws SQLException, ClassNotFoundException {
        String query = "UPDATE orders set Cust_id=?, Order_Date=?, Price=? WHERE OrderId=?";
        return CrudUtil.executeUpdate(query, orders.getCust_id(), orders.getOrder_Date(),
                orders.getCost(), orders.getOrderId());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM orders WHERE OrderId=?";
        return CrudUtil.executeUpdate(query, s);
    }

    @Override
    public Orders search(String s) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM orders WHERE OrderId=?";
        ResultSet resultSet = CrudUtil.executeQuery(query, s);
        while (resultSet.next()) {
            return new Orders(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getDouble(4));
        }
        return null;
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM orders";
        ResultSet resultSet = CrudUtil.executeQuery(query);

        ArrayList<Orders> customerArrayList = new ArrayList<>();

        while (resultSet.next()) {
            customerArrayList.add(new Orders(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getDouble(4)));
        }
        return customerArrayList;
    }

    @Override
    public int getOrderCount() throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(OrderId) FROM orders";
        ResultSet resultSet = CrudUtil.executeQuery(query);
        while (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public String getLastOrderId() throws SQLException, ClassNotFoundException {
        String query = "SELECT OrderId FROM orders ORDER BY OrderId DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.executeQuery(query);
        while (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }
}
