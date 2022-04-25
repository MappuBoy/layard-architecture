package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import model.Item;
import model.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean add(Item item) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO item VALUES(?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(query, item.getItem_Code(), item.getDescription(), item.getPackSize(),
                item.getQtyOnHand(), item.getUnitPrice(), item.getItem_Type());
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        String query = "UPDATE item set description=?, packSize=?, qtyOnHand=?, unitPrice=?, Item_Type=? WHERE Item_Code=?";
        return CrudUtil.executeUpdate(query, item.getDescription(), item.getPackSize(),
                item.getQtyOnHand(), item.getUnitPrice(), item.getItem_Type(), item.getItem_Code());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM item WHERE Item_Code=?";
        return CrudUtil.executeUpdate(query, s);
    }

    @Override
    public Item search(String s) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM item WHERE Item_Code=?";
        ResultSet resultSet = CrudUtil.executeQuery(query, s);
        while (resultSet.next()) {
            return new Item(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getInt(4),
                    resultSet.getDouble(5), resultSet.getString(6));
        }
        return null;
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM item";
        ResultSet resultSet = CrudUtil.executeQuery(query);

        ArrayList<Item> itemsArrayList = new ArrayList<>();

        while (resultSet.next()) {
            itemsArrayList.add(new Item(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getInt(4),
                    resultSet.getDouble(5), resultSet.getString(6)));
        }
        return itemsArrayList;
    }

    @Override
    public int getItemCount() throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(Item_Code) FROM item";
        ResultSet resultSet = CrudUtil.executeQuery(query);
        while (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean updateStockQuentity(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        String sql="UPDATE item SET qtyOnHand=qtyOnHand-? WHERE Item_Code=?";
        return CrudUtil.executeUpdate(sql, orderDetail.getOrderQty(),orderDetail.getItem_Code());
    }
}
