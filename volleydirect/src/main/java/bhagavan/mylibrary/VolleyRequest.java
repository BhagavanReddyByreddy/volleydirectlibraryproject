package bhagavan.mylibrary;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bhagavan on 1/9/2017.
 */

public class VolleyRequest{
    Activity context;
    ProgressDialog pDialog;
    String finalResult = null;


    public MessageCallBack callback;

    public VolleyRequest(Activity applicationContext) {
        this.context = applicationContext;
    }


    public void requestPostService(String url, int post, final Map<String, String> params, final String service, final int uniqueIdForService) {
        // Tag used to cancel the request
        String  tag_string_req = "string_req";

        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq = new StringRequest(post,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("onResponse", response.toString());
                finalResult = response;
                pDialog.hide();
                callback.onSuccess(response,service,uniqueIdForService);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("onErrorResponse", "Error: " + error.getMessage());
                pDialog.hide();
                callback.onSuccess("null",service,uniqueIdForService);
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> newParams = new HashMap<String,String>();
                Map tmp = new HashMap(params);
                tmp.keySet().removeAll(newParams.keySet());
                newParams.putAll(tmp);
                return newParams;
            }
        };

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

}
