package bhagavan.volleydirectlibraryproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Request;

import java.util.HashMap;
import java.util.Map;

import bhagavan.mylibrary.MessageCallBack;
import bhagavan.mylibrary.VolleyRequest;

public class MainActivity extends AppCompatActivity implements MessageCallBack {
TextView textView;
    VolleyRequest request;
    String service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        String url = "http://silkrummy.com/webservice/pokabungaApi.php?rquest=getAppconfiguration";

        Map<String, String> params = new HashMap<String, String>();
        params.put("auth_key", "123456123456");

        //valleyRequestForData();
        request = new VolleyRequest(MainActivity.this);
        request.callback = this;
        service = "getAppconfiguration";
        request.requestPostService(url, Request.Method.POST,params,service,1);
    }

    @Override
    public void onSuccess(String result, String service, int uniqueIdForService) {
        if(!(result.trim().equals("null"))) {
            if (service.trim().equals("getAppconfiguration")) {
                textView.setText(result);
            } else {
                // handle here another service response..
            }
        }else{
            // some thing went wrong....
        }

    }
    }

