package carworld.autolist.Network;

import android.util.Log;

import com.loopj.android.http.*;

/**
 * Created by amritachowdhury on 4/23/17.
 */

public class VehicleNetwork {
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, responseHandler);
    }
}
