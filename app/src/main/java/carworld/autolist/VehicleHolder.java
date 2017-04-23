package carworld.autolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by amritachowdhury on 4/22/17.
 */

// http://stacktips.com/tutorials/android/how-to-use-picasso-library-in-android
public  class VehicleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @Bind(R.id.image)
    protected ImageView image;
    @Bind(R.id.description)
    protected TextView description;
    @Bind(R.id.price)
    protected TextView price;
    @Bind(R.id.mileage)
    protected TextView mileage;
    @Bind(R.id.location)
    protected TextView location;
    @Bind(R.id.installment)
    protected TextView installment;

    private Vehicle vehicle;

    private static final String PHOTO_KEY = "PHOTO";

    public VehicleHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
        v.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DebugStatements.printDebugStatements(vehicle.getDescription());
            /*Context context = itemView.getContext();
            Intent showPhotoIntent = new Intent(context, VehicleDetailActivity.class);
            showPhotoIntent.putExtra(PHOTO_KEY, vehicle);
            context.startActivity(showPhotoIntent);*/
    }

    public void bindVehicle(Vehicle v) {
        vehicle = v;
        populateImageView(v.getThumbnailUrl());
       // image.setImageResource(R.drawable.splash_1);
       // Picasso.with(image.getContext()).load(v.getThumbnailUrl()).into(image);
        price.setText(vehicle.getPrice());
        description.setText(vehicle.getDescription());
        mileage.setText(vehicle.getMileage());
        location.setText(getLocation(vehicle));
        installment.setText(getInstallment(vehicle));
    }

    private void populateImageView(String imageUrl) {
        Picasso.with(image.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(image);
    }

    private String getLocation(Vehicle vehicle) {
        return vehicle.getCity() + ", " + vehicle.getState();
    }

    private String getInstallment(Vehicle vehicle) {
        return "Est $" + String.valueOf(vehicle.getInstallment()) + " /mo";
    }
}
