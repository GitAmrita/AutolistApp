package carworld.autolist;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import carworld.autolist.Listener.InfiniteScrollListener;
import carworld.autolist.Listener.OnGetSearchResultListener;
import carworld.autolist.Listener.OnSelectPriceRangeListener;
import carworld.autolist.Network.VehicleNetworkUsage;

/* https://www.raywenderlich.com/126528/android-recyclerview-tutorial*/
public class MainActivity extends AppCompatActivity {

    private InfiniteScrollListener scrollListener;
    private Adapter adapter;
    private List<Vehicle> vehicles;
    private int minPriceRange;
    private int maxPriceRange;
    private boolean isFiltered;
    private int page;
    private VehicleNetworkUsage usage;
    ProgressDialog prgDialog;

    @OnClick(R.id.filter)
    protected void priceFilter() {
        scrollListener.resetState();
         new PriceDialog(this, minPriceRange / 1000, maxPriceRange / 1000, new OnSelectPriceRangeListener() {
            @Override
            public void getPriceRange(int minPrice, int maxPrice) {
                vehicles.clear();
                minPriceRange = minPrice;
                maxPriceRange = maxPrice;
                isFiltered = true;
                page = 1;
                populateSearchResults();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.vehicleList);
        adapter = new Adapter(this, vehicles);
       // recyclerView.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        scrollListener = new InfiniteScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadNextDataFromApi(page);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
    }

    // Append the next page of data into the adapter
    public void loadNextDataFromApi(int offset) {
        page = offset;
        populateSearchResults();
    }

    public void initData() {
        prgDialog = new ProgressDialog(this);
        prgDialog.setMessage(getString(R.string.wait));
        prgDialog.setCancelable(false);
        vehicles = new LinkedList<>();
        usage = new VehicleNetworkUsage();
        minPriceRange = 0;
        maxPriceRange = 0;
        isFiltered = false;
        page = 1;
        populateSearchResults();
    }

    private void populateSearchResults() {
        prgDialog.show();
        usage.getVehicleList(page, minPriceRange, maxPriceRange, isFiltered, new OnGetSearchResultListener() {
            @Override
            public void getVehicles(List<Vehicle> v) {
                prgDialog.hide();
                for (Vehicle temp : v) {
                    vehicles.add(temp);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}
