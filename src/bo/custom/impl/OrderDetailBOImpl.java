package bo.custom.impl;

import bo.custom.OrderDetailBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailDAO;
import db.DBConnection;
import dto.OrderDetailDTO;
import dto.OrdersDTO;
import model.OrderDetail;
import model.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO {

    OrderDAO order = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean placeOrder(OrdersDTO ordersDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        Orders orders = new Orders(ordersDTO.getOrderId(), ordersDTO.getCust_id(), ordersDTO.getOrder_Date(),
                ordersDTO.getCost());

        boolean addOrder = order.add(orders);

        try {
            if (addOrder) {
                for (OrderDetailDTO orderDetailDTO : ordersDTO.getOrderDetailDTOList()) {

                    OrderDetail orderDetail = new OrderDetail(orderDetailDTO.getOrderId(), orderDetailDTO.getItem_Code(),
                            orderDetailDTO.getOrderQty(), orderDetailDTO.getDiscount());

                    boolean addOrderDetail = orderDetailDAO.add(orderDetail);

                    if (addOrderDetail) {
                        boolean updateStockQuentity = itemDAO.updateStockQuentity(orderDetail);
                        if (updateStockQuentity) {
                            connection.commit();
                        }
                    }
                }
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public int getOrderCount() throws SQLException, ClassNotFoundException {
        return order.getOrderCount();
    }

    @Override
    public ArrayList<OrdersDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList<Orders> ordersArrayList = order.getAll();

        ArrayList<OrdersDTO> ordersDTOS = new ArrayList<>();

        ordersArrayList.forEach(orders -> {
            ordersDTOS.add(new OrdersDTO(orders.getOrderId(), orders.getCust_id(), orders.getOrder_Date(),
                    orders.getCost()));
        });

        return ordersDTOS;
    }

    @Override
    public String getLastOrderId() throws SQLException, ClassNotFoundException {
        return order.getLastOrderId();
    }
}
