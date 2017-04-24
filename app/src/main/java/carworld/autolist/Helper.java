package carworld.autolist;

import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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


}
