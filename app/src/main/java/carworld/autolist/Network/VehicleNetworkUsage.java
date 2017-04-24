package carworld.autolist.Network;

import org.json.*;
import com.loopj.android.http.*;
import org.json.JSONArray;

import carworld.autolist.Seller;
import cz.msebera.android.httpclient.Header;

import java.util.LinkedList;
import java.util.List;

import carworld.autolist.Config;
import carworld.autolist.Listener.OnGetSearchResultListener;
import carworld.autolist.Vehicle;

/**
 * Created by amritachowdhury on 4/23/17.
 */

public class VehicleNetworkUsage {
    private static final String URL = Config.api.BASE_URL ;

    private String getVehicleDescription(JSONObject record) {
        String year = "";
        String make = "";
        String model = "";
        try {
            if (record.has("year")) {
                year += record.get("year").toString() + " ";
            }
            if (record.has("make")) {
                make += record.get("make").toString() + " ";
            }
            if (record.has("model")) {
                model += record.get("model").toString() + " ";
            }
            return year + " " + make + " " + model;
        } catch (Exception e) {
            return "";
        }
    }

    private List<Vehicle> getAllVehicles(JSONArray records) {
        List<Vehicle> vehicles = new LinkedList<>();
        try {
            for (int i = 0; i < records.length(); i++) {
                int id = 0;
                String price = "";
                int installment = 0;
                String description = "";
                String mileage = "";
                String city = "";
                String state = "";
                String condition = "";
                String bodyType = "";
                String displayColor = "";
                String thumbnailUrl = "";
                JSONObject record = records.getJSONObject(i);
                if (record.has("id")) {
                    id = record.getInt("id");
                }
                if (record.has("price")) {
                    price = record.get("price").toString();
                }
                description = getVehicleDescription(record);
                if (record.has("mileage")) {
                    mileage = record.get("mileage").toString();
                }
                if (record.has("city")) {
                    city = record.get("city").toString();
                }
                if (record.has("state")) {
                    state = record.get("state").toString();
                }
                if (record.has("price_unformatted")) {
                    int d = record.getInt("price_unformatted");
                    installment = d / Config.priceRange.INSTALLMENT_PERIOD_IN_MONTHS;
                }
                if (record.has("condition")) {
                    condition = record.get("condition").toString();
                }
                if (record.has("body_type")) {
                    bodyType = record.get("body_type").toString();
                }
                if (record.has("display_color")) {
                    displayColor = record.get("display_color").toString();
                }
                if (record.has("thumbnail_url")) {
                    thumbnailUrl = record.get("thumbnail_url").toString();
                }
                Seller seller = getSellerDetails(record, id);
                Vehicle vehicle = new Vehicle(price, description, mileage, state, city, installment,
                        condition, bodyType, displayColor, thumbnailUrl, seller);
                vehicles.add(vehicle);
            }
        } catch (Exception e) {

        }
        return vehicles;
    }

    private Seller getSellerDetails(JSONObject record, int id) {
        try {
            if (record.has("dealer_name")) {
                return populateDealerWithTestData(record.get("dealer_name").toString(), id);
            }
        } catch(Exception e) {
        }
        return null;
    }

    public void getVehicleList(int page, int minPrice, int maxPrice, boolean isFiltered,
                               final OnGetSearchResultListener listener) {
        String url = URL + getQueryParams(page, minPrice, maxPrice, isFiltered);
        VehicleNetwork.get(url, null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.has("records")) {
                        JSONArray records = (JSONArray)response.get("records");
                        List<Vehicle> vehicles = getAllVehicles(records);
                        listener.getVehicles(vehicles);
                    }
                } catch(Exception e) {

                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  String responseString, Throwable throwable) {

            }
        });
    }

    private String getQueryParams(int page, int minPrice, int maxPrice, boolean isFiltered) {
        String query = "?page=" + String.valueOf(page);
        if (isFiltered) {
            query += "&price_min=" + String.valueOf(minPrice);
            query += "&price_max=" + String.valueOf(maxPrice);
        }
        return query;
    }

    private Seller populateDealerWithTestData(String dealerName, int id) {
        if (id % Config.testData.TEST_NO_1 == 0 || id % Config.testData.TEST_NO_2 == 0 ||
                id % Config.testData.TEST_NO_3 == 0) {
            return new Seller(dealerName, Seller.ContactType.PHONE, Config.testData.TEST_PHONE_NUMBER);
        } else {
            return new Seller(dealerName, Seller.ContactType.EMAIL, Config.testData.TEST_EMAIL);
        }
    }
}
