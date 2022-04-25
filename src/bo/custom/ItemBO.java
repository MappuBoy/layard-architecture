package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.ItemDTO;
import model.Item;
import model.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {

    boolean addItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;

    ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

    int getItemCount() throws SQLException, ClassNotFoundException;

    boolean updateStockQuentity(OrderDetail orderDetail) throws SQLException, ClassNotFoundException;


}
