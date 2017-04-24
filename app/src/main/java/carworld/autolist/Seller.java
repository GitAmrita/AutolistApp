package carworld.autolist;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by amritachowdhury on 4/24/17.
 */

public class Seller implements Parcelable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    private String name;
    private ContactType contactType;
    private String contactDetails;

    public Seller(String name, ContactType contactType, String contactDetails) {
        this.name = name;
        this.contactType = contactType;
        this.contactDetails = contactDetails;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString((contactType == null) ? "" : contactType.name());
        dest.writeString(contactDetails);
    }

    public static final Parcelable.Creator<Seller> CREATOR = new Parcelable.Creator<Seller>() {
        public Seller createFromParcel(Parcel in) {
            return new Seller(in);
        }

        public Seller[] newArray(int size) {
            return new Seller[size];
        }
    };

    private Seller(Parcel in) {
        name = in.readString();
        try {
            contactType = ContactType.valueOf(in.readString());
        } catch (IllegalArgumentException x) {
            contactType = null;
        }
        contactDetails = in.readString();
    }

    public enum ContactType {
        EMAIL,
        PHONE;
    }
}
