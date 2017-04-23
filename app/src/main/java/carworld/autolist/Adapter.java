package carworld.autolist;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by amritachowdhury on 4/22/17.
 */

public class Adapter extends RecyclerView.Adapter<VehicleHolder> {
    private List<Vehicle> vehicles;



    public Adapter(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public VehicleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vehicle_row, parent, false);
        return new VehicleHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(VehicleHolder holder, int position) {
        Vehicle item = vehicles.get(position);
        holder.bindVehicle(item);

    }

    @Override
    public int getItemCount() {
        //The adapter will work out how many items to display.
        return  vehicles.size();
    }
}
