package carworld.autolist;

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
}
