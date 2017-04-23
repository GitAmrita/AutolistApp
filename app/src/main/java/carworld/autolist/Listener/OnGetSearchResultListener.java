package carworld.autolist.Listener;

import java.util.List;

import carworld.autolist.Vehicle;

/**
 * Created by amritachowdhury on 4/23/17.
 */

public interface OnGetSearchResultListener {
    void getVehicles(List<Vehicle> vehicles);
}
