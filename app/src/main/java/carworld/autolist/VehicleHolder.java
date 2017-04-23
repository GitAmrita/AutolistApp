package carworld.autolist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by amritachowdhury on 4/22/17.
 */


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
           /* Context context = itemView.getContext();
            Intent showPhotoIntent = new Intent(context, VehicleActivity.class);
            showPhotoIntent.putExtra(PHOTO_KEY, vehicle);
            context.startActivity(showPhotoIntent);*/
    }

    public void bindVehicle(Vehicle v) {
        vehicle = v;
        image.setImageResource(R.drawable.splash_1);
        //  Picasso.with(mItemImage.getContext()).load(photo.getUrl()).into(mItemImage);
        price.setText(vehicle.getPrice());
        description.setText(vehicle.getDescription());
        mileage.setText(vehicle.getMileage());
        location.setText(getLocation(vehicle));
        installment.setText(getInstallment(vehicle));
    }

    private String getLocation(Vehicle vehicle) {
        return vehicle.getCity() + ", " + vehicle.getState();
    }

    private String getInstallment(Vehicle vehicle) {
        return "Est $" + String.valueOf(vehicle.getInstallment()) + " /mo";
    }
}
