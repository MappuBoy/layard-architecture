package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customer = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customer.add(new Customer(customerDTO.getCust_id(), customerDTO.getCust_Title(), customerDTO.getCust_name(), customerDTO.getCust_address(),
                customerDTO.getCity(), customerDTO.getProvince(), customerDTO.getPostalCode()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customer.update(new Customer(customerDTO.getCust_id(), customerDTO.getCust_Title(), customerDTO.getCust_name(), customerDTO.getCust_address(),
                customerDTO.getCity(), customerDTO.getProvince(), customerDTO.getPostalCode()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customer.delete(id);
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer searchCustomer = customer.search(id);
        return new CustomerDTO(searchCustomer.getCust_id(), searchCustomer.getCust_Title(), searchCustomer.getCust_name(),
                searchCustomer.getCust_address(), searchCustomer.getCity(), searchCustomer.getProvince(),
                searchCustomer.getPostalCode());
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {

        ArrayList<Customer> customerArrayList = customer.getAll();

        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        customerArrayList.forEach(customer1 -> {
            customerDTOS.add(new CustomerDTO(customer1.getCust_id(), customer1.getCust_Title(), customer1.getCust_name(),
                    customer1.getCust_address(),
                    customer1.getCity(), customer1.getProvince(), customer1.getPostalCode()));
        });

        /*for (Customer customer1 : customerArrayList) {
            customerDTOS.add(new CustomerDTO(customer1.getCust_id(),customer1.getCust_Title(),customer1.getCust_name(),
                    customer1.getCust_address(),
                    customer1.getCity(),customer1.getProvince(),customer1.getPostalCode()));
        }*/

        return customerDTOS;
    }

    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        return customer.getCustomerCount();
    }
}
