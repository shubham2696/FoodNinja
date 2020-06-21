package i.beangate.foodninja;

public class Food_Categories {
    private String item_type,imageurl;
    private int item_type_id;

    public Food_Categories(int item_type_id,String imageurl,String item_type) {
        this.item_type = item_type;
        this.imageurl = imageurl;
        this.item_type_id = item_type_id;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public int getItem_type_id() {
        return item_type_id;
    }

    public void setItem_type_id(int item_type_id) {
        this.item_type_id = item_type_id;
    }
}

