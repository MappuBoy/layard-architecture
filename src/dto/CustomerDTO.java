package dto;

public class CustomerDTO {
    private String Cust_id;
    private String Cust_Title;
    private String Cust_name;
    private String Cust_address;
    private String City;
    private String Province;
    private String PostalCode;

    public CustomerDTO() {
    }

    public CustomerDTO(String cust_id, String cust_Title, String cust_name, String cust_address, String city, String province, String postalCode) {
        Cust_id = cust_id;
        Cust_Title = cust_Title;
        Cust_name = cust_name;
        Cust_address = cust_address;
        City = city;
        Province = province;
        PostalCode = postalCode;
    }

    public String getCust_id() {
        return Cust_id;
    }

    public void setCust_id(String cust_id) {
        Cust_id = cust_id;
    }

    public String getCust_Title() {
        return Cust_Title;
    }

    public void setCust_Title(String cust_Title) {
        Cust_Title = cust_Title;
    }

    public String getCust_name() {
        return Cust_name;
    }

    public void setCust_name(String cust_name) {
        Cust_name = cust_name;
    }

    public String getCust_address() {
        return Cust_address;
    }

    public void setCust_address(String cust_address) {
        Cust_address = cust_address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }
}
