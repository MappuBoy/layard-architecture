package bo;

import bo.custom.impl.CustomerBOImpl;
import bo.custom.impl.ItemBOImpl;
import bo.custom.impl.OrderDetailBOImpl;
import dao.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance(){
        return null == boFactory ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER, ITEM, ORDER, ORDERDETAIL, QUERY;
    }

    public SuperBO getBo(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDERDETAIL:
                return new OrderDetailBOImpl();
            /*case QUERY:
                return new QueryDAOImpl();*/
            default:
                return null;
        }
    }
}
