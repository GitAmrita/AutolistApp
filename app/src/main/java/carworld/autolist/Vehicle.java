package carworld.autolist;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by amritachowdhury on 4/22/17.
 */

public class Vehicle {


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getInstallment() {
        return installment;
    }

    public void setInstallment(int installment) {
        this.installment = installment;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(String displayColor) {
        this.displayColor = displayColor;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    private String price;
    private String description;
    private String mileage;
    private String city;
    private String state;
    private int installment;
    private String condition;
    private String bodyType;
    private String displayColor;
    private String thumbnailUrl;

    /*public Vehicle(int itemNo) {
        this.price = "$56,100";
        this.description = "test description : " + String.valueOf(itemNo);
        this.mileage = "49k miles";
        this.city = "Sacramento";
        this.state = "CA";
        this.installment = 67;
    }*/

    public Vehicle(String price, String description, String mileage, String state, String city,
                   int installment, String condition, String bodyType, String displayColor, String thumbnailUrl) {
        this.price = price;
        this.description = description;
        this.mileage = mileage;
        this.state = state;
        this.city = city;
        this.installment = installment;
        this.condition = condition;
        this.bodyType = bodyType;
        this.displayColor = displayColor;
        this.thumbnailUrl = thumbnailUrl;
    }
}
