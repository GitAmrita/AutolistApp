package carworld.autolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import carworld.autolist.Listener.InfiniteScrollListener;
import carworld.autolist.Listener.OnSelectPriceRangeListener;

/* https://www.raywenderlich.com/126528/android-recyclerview-tutorial*/
public class MainActivity extends AppCompatActivity {

    private final int TEST_VAL = 20;

    // Store a member variable for the listener
    private InfiniteScrollListener scrollListener;
    private Adapter adapter;
    private List<Vehicle> vehicles;

    @OnClick(R.id.filter)
    protected void priceFilter() {
         new PriceDialog(this, new OnSelectPriceRangeListener() {
            @Override
            public void getPriceRange(int minPrice, int maxPrice) {
                DebugStatements.printDebugStatements(String.valueOf(minPrice));
                DebugStatements.printDebugStatements(String.valueOf(maxPrice));
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
        adapter = new Adapter(vehicles);
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
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);
    }

    // Append the next page of data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
    public void loadNextDataFromApi(int offset) {
        // Send an API request to retrieve appropriate paginated data
        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
        //  --> Deserialize and construct new model objects from the API response
        //  --> Append the new data objects to the existing set of items inside the array of items
        //  --> Notify the adapter of the new items made with `notifyItemRangeInserted()`

        DebugStatements.printDebugStatements(String.valueOf(offset));
        for (int i = TEST_VAL + offset; i < TEST_VAL + offset + TEST_VAL; i++) {
            Vehicle temp = new Vehicle(i);
            vehicles.add(temp);
        }
        adapter.notifyDataSetChanged();
    }

    public void initData() {
        vehicles = new ArrayList<>();
        for (int i = 0; i <  TEST_VAL; i++) {
            Vehicle temp = new Vehicle(i + 1);
            vehicles.add(temp);
        }
    }
}
