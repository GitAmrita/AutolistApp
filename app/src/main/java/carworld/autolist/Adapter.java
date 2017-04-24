package carworld.autolist;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by amritachowdhury on 4/22/17.
 */

public class Adapter extends RecyclerView.Adapter<VehicleHolder> {
    private List<Vehicle> vehicles;
    private MainActivity context;

    public Adapter(MainActivity context, List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        this.context = context;
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
        if (position == Config.recycleView.ASK_SEARCH_RESULT_SAVED) {
            Log.e("bapi", "adapter");
            new SaveSearchResultDialog(context);
        }
        holder.bindVehicle(item);

    }

    @Override
    public int getItemCount() {
        return  vehicles.size();
    }
}
