package dao.custom;

import dao.CrudDAO;
import model.Orders;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Orders, String> {

    int getOrderCount() throws SQLException, ClassNotFoundException;

    String getLastOrderId() throws SQLException, ClassNotFoundException;

}
