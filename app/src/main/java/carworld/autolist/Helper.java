package carworld.autolist;

import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by amritachowdhury on 4/23/17.
 */

public class Helper {
    public static String getLocation(Vehicle vehicle) {
        return vehicle.getCity() + ", " + vehicle.getState();
    }

    public static String getInstallment(Vehicle vehicle) {
        return "Est $" + String.valueOf(vehicle.getInstallment()) + " /mo";
    }

    public static void populateImageView(ImageView image, String imageUrl) {
        Picasso.with(image.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(image);
    }

    public static String capitalizeFirstLetter(String input) {
        if (TextUtils.isEmpty(input)) {
            return "";
        }
        return (input.substring(0, 1).toUpperCase() + input.substring(1));
    }

    public static String getSHA512SecurePassword(String passwordToHash, String salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (Exception e){
            return passwordToHash;
        }
        return generatedPassword;
    }
}
