package carworld.autolist;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.List;


/**
 * Created by amritachowdhury on 4/22/17.
 */
//http://stackoverflow.com/questions/32427889/checkbox-in-recyclerview-keeps-on-checking-different-items
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
        final Vehicle item = vehicles.get(position);
        holder.favorite.setOnCheckedChangeListener(null);
        holder.favorite.setChecked(item.isFavorite());
        holder.favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setFavorite(isChecked);
            }
        });
        if (position == Config.recycleView.ASK_SEARCH_RESULT_SAVED) {
            new SaveSearchResultDialog(context);
        }
        holder.bindVehicle(item);

    }

    @Override
    public int getItemCount() {
        return  vehicles.size();
    }
}
