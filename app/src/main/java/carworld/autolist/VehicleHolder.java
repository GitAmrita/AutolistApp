package carworld.autolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

import static carworld.autolist.Helper.getInstallment;
import static carworld.autolist.Helper.getLocation;
import static carworld.autolist.Helper.populateImageView;

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
    @Bind(R.id.favorite)
    protected CheckBox favorite;

    private Vehicle vehicle;

    public VehicleHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
        v.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Context context = itemView.getContext();
        Intent vehicleDetailIntent = new Intent(context, VehicleDetailActivity.class);
        vehicleDetailIntent.putExtra(Config.recycleView.SELECTED_VEHICLE, vehicle);
        context.startActivity(vehicleDetailIntent);
    }

    public void bindVehicle(Vehicle v) {
        vehicle = v;
        populateImageView(image, vehicle.getThumbnailUrl());
        price.setText(vehicle.getPrice());
        description.setText(vehicle.getDescription());
        mileage.setText(vehicle.getMileage());
        location.setText(getLocation(vehicle));
        installment.setText(getInstallment(vehicle));
    }

}
