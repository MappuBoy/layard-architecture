package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import model.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer, String> {

    int getCustomerCount() throws SQLException, ClassNotFoundException;

}
