package carworld.autolist.Network;

import android.util.Log;

import com.loopj.android.http.*;

/**
 * Created by amritachowdhury on 4/23/17.
 */

public class VehicleNetwork {
    private static AsyncHttpClient client = new AsyncHttpClient();
    //https://autolist-test.herokuapp.com/search?page=1
    //https://autolist-test.herokuapp.com/search?page=1&price_min=5000&price_max=10000

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, responseHandler);
    }
}
