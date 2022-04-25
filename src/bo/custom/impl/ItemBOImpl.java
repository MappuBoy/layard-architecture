package bo.custom.impl;

import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dto.ItemDTO;
import model.Item;
import model.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean addItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.add(new Item(itemDTO.getItem_Code(),itemDTO.getDescription(),itemDTO.getPackSize(),itemDTO.getQtyOnHand(),
                itemDTO.getUnitPrice(),itemDTO.getItem_Type()));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(itemDTO.getItem_Code(),itemDTO.getDescription(),itemDTO.getPackSize(),itemDTO.getQtyOnHand(),
                itemDTO.getUnitPrice(),itemDTO.getItem_Type()));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException {
        Item searchItem = itemDAO.search(id);
        return new ItemDTO(searchItem.getItem_Code(),searchItem.getDescription(),searchItem.getPackSize(),
                searchItem.getQtyOnHand(),searchItem.getUnitPrice(),searchItem.getItem_Type());
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> itemArrayList = itemDAO.getAll();

        ArrayList<ItemDTO> itemDTOArrayList = new ArrayList<>();

        itemArrayList.forEach(item1 -> {
            itemDTOArrayList.add(new ItemDTO(item1.getItem_Code(),item1.getDescription(),
                    item1.getPackSize(),item1.getQtyOnHand(),item1.getUnitPrice(),item1.getItem_Type()));
        });

        return itemDTOArrayList;
    }

    @Override
    public int getItemCount() throws SQLException, ClassNotFoundException {
        return itemDAO.getItemCount();
    }

    @Override
    public boolean updateStockQuentity(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        return itemDAO.updateStockQuentity(orderDetail);
    }
}
