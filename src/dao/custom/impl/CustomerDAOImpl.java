package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO customer VALUES(?,?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(query, customer.getCust_id(), customer.getCust_Title(), customer.getCust_name(),
                customer.getCust_address(), customer.getCity(), customer.getProvince(), customer.getPostalCode());
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        String query = "UPDATE customer set Cust_Title=?, Cust_name=?, Cust_address=?, City=?, Province=?, " +
                "PostalCode=? WHERE Cust_id=?";
        return CrudUtil.executeUpdate(query, customer.getCust_Title(), customer.getCust_name(),
                customer.getCust_address(), customer.getCity(), customer.getProvince(), customer.getPostalCode(),
                customer.getCust_id());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM customer WHERE Cust_id=?";
        return CrudUtil.executeUpdate(query, s);
    }

    @Override
    public Customer search(String s) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM customer WHERE Cust_id=?";
        ResultSet resultSet = CrudUtil.executeQuery(query, s);
        while (resultSet.next()){
            return new Customer(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                    resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),
                    resultSet.getString(7));
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM customer";
        ResultSet resultSet = CrudUtil.executeQuery(query);

        ArrayList<Customer> customerArrayList = new ArrayList<>();

        while (resultSet.next()){
            customerArrayList.add(new Customer(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                    resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),
                    resultSet.getString(7)));
        }
        return customerArrayList;
    }

    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(Cust_id) FROM customer";
        ResultSet resultSet = CrudUtil.executeQuery(query);
        while (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }
}
