package model;

public class Customer {

    private String cust_id;
    private String cust_Title;
    private String cust_name;
    private String cust_address;
    private String city;
    private String province;
    private String postalCode;

    public Customer() {
    }

    public Customer(String cust_id, String cust_Title, String cust_name, String cust_address, String city,
                    String province, String postalCode) {
        this.cust_id = cust_id;
        this.cust_Title = cust_Title;
        this.cust_name = cust_name;
        this.cust_address = cust_address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getCust_Title() {
        return cust_Title;
    }

    public void setCust_Title(String cust_Title) {
        this.cust_Title = cust_Title;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_address() {
        return cust_address;
    }

    public void setCust_address(String cust_address) {
        this.cust_address = cust_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
