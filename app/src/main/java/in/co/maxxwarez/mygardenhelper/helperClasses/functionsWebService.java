package in.co.maxxwarez.mygardenhelper.helperClasses;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class functionsWebService {
    private final static String TAG = "SkyNet";
    OkHttpClient httpClient;
    String webServiceUrl;

    public functionsWebService(String serviceName){
        httpClient = new OkHttpClient();
        webServiceUrl = serviceName;
    }

    public String webGet() throws IOException, JSONException {
        Request request = new Request.Builder()
                .url(webServiceUrl)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = httpClient.newCall(request).execute();
        String result = response.body().string();
        return result;
    }
}