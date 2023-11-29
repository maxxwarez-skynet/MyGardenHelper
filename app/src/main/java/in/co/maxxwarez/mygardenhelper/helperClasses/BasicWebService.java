package in.co.maxxwarez.mygardenhelper.helperClasses;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class BasicWebService{
    private final static String TAG = "SkyNet";
    OkHttpClient httpClient;
    String webServiceUrl;

    public BasicWebService(String serviceName){
        httpClient = new OkHttpClient();
        webServiceUrl = serviceName;
    }

    public String webGet() throws IOException, JSONException {
        Request request = new Request.Builder()
                .url(webServiceUrl)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = httpClient.newCall(request).execute();
        String res = response.body().string();
        return res;
    }
}