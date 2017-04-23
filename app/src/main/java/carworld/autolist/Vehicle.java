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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstallment() {
        return installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

    private String price;
    private String description;
    private String mileage;
    private String location;
    private String installment;

    public Vehicle(int itemNo) {
        this.price = "$56,100";
        this.description = "test description : " + String.valueOf(itemNo);
        this.mileage = "49k miles";
        this.location = "Sacramento, CA";
        this.installment = "Est $677/mo";
    }
}
