package dto;

public class ItemDTO {
    private String Item_Code;
    private String description;
    private String packSize;
    private int qtyOnHand;
    private double unitPrice;
    private String Item_Type;

    public ItemDTO() {
    }

    public ItemDTO(String item_Code, String description, String packSize, int qtyOnHand, double unitPrice, String item_Type) {
        Item_Code = item_Code;
        this.description = description;
        this.packSize = packSize;
        this.qtyOnHand = qtyOnHand;
        this.unitPrice = unitPrice;
        Item_Type = item_Type;
    }

    public String getItem_Code() {
        return Item_Code;
    }

    public void setItem_Code(String item_Code) {
        Item_Code = item_Code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getItem_Type() {
        return Item_Type;
    }

    public void setItem_Type(String item_Type) {
        Item_Type = item_Type;
    }

    @Override
    public String toString() {
        return "Item{" +
                "Item_Code='" + Item_Code + '\'' +
                ", description='" + description + '\'' +
                ", packSize='" + packSize + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                ", unitPrice=" + unitPrice +
                ", Item_Type='" + Item_Type + '\'' +
                '}';
    }
}
