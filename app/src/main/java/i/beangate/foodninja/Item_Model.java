package i.beangate.foodninja;

/**
 * Created by shubham on 11/5/2017.
 */

public class Item_Model {

    private String name, imageurl,type;
    private double rating;

    public Item_Model(String name, String type,String imageurl,double rating) {
        this.name = name;
        this.imageurl = imageurl;
        this.type = type;
        this.rating=rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
