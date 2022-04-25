package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import model.Item;
import model.OrderDetail;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item, String> {
    int getItemCount() throws SQLException, ClassNotFoundException;

    boolean updateStockQuentity(OrderDetail orderDetail) throws SQLException, ClassNotFoundException;
}
