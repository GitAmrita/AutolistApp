package carworld.autolist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static carworld.autolist.Helper.getInstallment;
import static carworld.autolist.Helper.getLocation;
import static carworld.autolist.Helper.populateImageView;

/**
 * Created by amritachowdhury on 4/23/17.
 */

public class VehicleDetailActivity extends AppCompatActivity {
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
    @Bind(R.id.body_type)
    protected TextView bodyType;
    @Bind(R.id.display_color)
    protected TextView displayColor;
    @Bind(R.id.condition)
    protected TextView condition;


    private Vehicle vehicle;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        vehicle = intent.getParcelableExtra(Config.recycleView.SELECTED_VEHICLE);
        drawComponents();
    }

    private void drawComponents() {
        populateImageView(image, vehicle.getThumbnailUrl());
        price.setText(vehicle.getPrice());
        description.setText(vehicle.getDescription());
        mileage.setText(vehicle.getMileage());
        location.setText(getLocation(vehicle));
        installment.setText(getInstallment(vehicle));
        bodyType.setText(vehicle.getBodyType());
        displayColor.setText(vehicle.getDisplayColor());
        condition.setText(vehicle.getCondition());
    }

    @OnClick(R.id.share)
    protected void shareVehicleDetails() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, super.getString(R.string.email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, this.getEmailBody());
        if (intent.resolveActivity(getPackageManager()) != null) {
            super.startActivity(Intent.createChooser(intent, super.getString(R.string.email)));
        }
    }

    private String getEmailBody() {
        String email = super.getString(R.string.email_body);
        email = String.format(email, vehicle.getThumbnailUrl());
        return email;
    }
}
