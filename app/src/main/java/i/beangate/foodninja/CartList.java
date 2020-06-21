package i.beangate.foodninja;

public class CartList {
    private String item_name;
    private int item_id,item_qty;
    private double item_price,total_amt;


    public CartList(int item_id, double total_amt, String item_name, double item_price, int item_qty) {
        this.item_name = item_name;
        this.item_id = item_id;
        this.item_qty = item_qty;
        this.item_price = item_price;
        this.total_amt = total_amt;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getItem_qty() {
        return item_qty;
    }

    public void setItem_qty(int item_qty) {
        this.item_qty = item_qty;
    }

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public double getTotal_amt() {
        return total_amt;
    }

    public void setTotal_amt(double total_amt) {
        this.total_amt = total_amt;
    }
}
