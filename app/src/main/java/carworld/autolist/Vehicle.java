package carworld.autolist;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by amritachowdhury on 4/22/17.
 */

public class Vehicle implements Parcelable {


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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Seller getSellerDetails() {
        return sellerDetails;
    }

    public void setSellerDetails(Seller sellerDetails) {
        this.sellerDetails = sellerDetails;
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
    private boolean isFavorite;
    private Seller sellerDetails;

    public Vehicle(String price, String description, String mileage, String state, String city,
                   int installment, String condition, String bodyType, String displayColor,
                   String thumbnailUrl, Seller sellerDetails) {
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
        this.isFavorite = false;
        this.sellerDetails = new Seller(sellerDetails.getName(), sellerDetails.getContactType(),
                sellerDetails.getContactDetails());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(price);
        dest.writeString(description);
        dest.writeString(mileage);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeInt(installment);
        dest.writeString(condition);
        dest.writeString(bodyType);
        dest.writeString(displayColor);
        dest.writeString(thumbnailUrl);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
        dest.writeParcelable(this.sellerDetails, flags);
    }

    public static final Parcelable.Creator<Vehicle> CREATOR = new Parcelable.Creator<Vehicle>() {
        public Vehicle createFromParcel(Parcel in) {
            return new Vehicle(in);
        }

        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

    private Vehicle(Parcel in) {
        price = in.readString();
        description = in.readString();
        mileage = in.readString();
        city = in.readString();
        state = in.readString();
        installment = in.readInt();
        condition = in.readString();
        bodyType = in.readString();
        displayColor = in.readString();
        thumbnailUrl = in.readString();
        isFavorite = in.readByte() != 0;
        this.sellerDetails = in.readParcelable(Seller.class.getClassLoader());
    }
}
