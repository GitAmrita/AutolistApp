package carworld.autolist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    @Bind(R.id.favorite)
    protected CheckBox favorite;
    @Bind(R.id.isFavorited)
    protected TextView favorited;
    @Bind(R.id.isFavoritedLabel)
    protected TextView isFavoritedLabel;
    @Bind(R.id.contact)
    protected Button contactDealer;


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
        bodyType.setText(Helper.capitalizeFirstLetter(vehicle.getBodyType()));
        displayColor.setText(vehicle.getDisplayColor());
        condition.setText(Helper.capitalizeFirstLetter(vehicle.getCondition()));
        favorite.setChecked(vehicle.isFavorite());
        favorite.setEnabled(false);
        if (vehicle.isFavorite()) {
            favorited.setVisibility(View.VISIBLE);
            isFavoritedLabel.setVisibility(View.VISIBLE);
        } else {
            favorited.setVisibility(View.GONE);
            isFavoritedLabel.setVisibility(View.GONE);
        }
        if (vehicle.getSellerDetails().getContactType() == Seller.ContactType.EMAIL) {
            contactDealer.setCompoundDrawablesWithIntrinsicBounds(R.drawable.contact_email, 0, 0, 0);
        } else {
            contactDealer.setCompoundDrawablesWithIntrinsicBounds(R.drawable.contact_phone, 0, 0, 0);
        }
    }

    @OnClick(R.id.share)
    protected void shareVehicleDetails() {
        emailIntent(super.getString(R.string.share_vehicle_email_subject),
                super.getString(R.string.share_vehicle_email_body), "");
    }

    @OnClick(R.id.contact)
    protected void contactVehicleDealer() {
        switch(vehicle.getSellerDetails().getContactType()) {
            case EMAIL:
                emailIntent(super.getString(R.string.contact_dealer_email_subject),
                        super.getString(R.string.contact_dealer_email_body),
                        vehicle.getSellerDetails().getContactDetails());
                break;
            case PHONE:
                phoneIntent(vehicle.getSellerDetails().getContactDetails());
        }
    }

    private void emailIntent(String subject, String body, String emailTo) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + emailTo));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, this.getEmailBody(body));
        if (intent.resolveActivity(getPackageManager()) != null) {
            super.startActivity(Intent.createChooser(intent, super.getString(R.string.email)));
        }
    }

    private String getEmailBody(String body) {
        String email = body;
        email = String.format(email, vehicle.getThumbnailUrl());
        return email;
    }

    private void phoneIntent(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        if (callIntent.resolveActivity(getPackageManager()) != null) {
            super.startActivity(callIntent);
        }
    }
}
