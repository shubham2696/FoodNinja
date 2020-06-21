package i.beangate.foodninja;

public class Food_Item {
    private String item_name,item_type,image_url;
    private int item_id,item_qty;
    private double item_price,item_rating;

    public Food_Item(String item_name,double item_price, double item_rating, String image_url,int item_id,String item_type, int item_qty ) {
        this.item_name = item_name;
        this.item_type = item_type;
        this.image_url = image_url;
        this.item_id = item_id;
        this.item_qty = item_qty;
        this.item_price = item_price;
        this.item_rating = item_rating;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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

    public double getItem_rating() {
        return item_rating;
    }

    public void setItem_rating(double item_rating) {
        this.item_rating = item_rating;
    }
}
