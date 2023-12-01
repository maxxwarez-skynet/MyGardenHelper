package in.co.maxxwarez.mygardenhelper.helperClasses;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;

import in.co.maxxwarez.mygardenhelper.ui.home.HomeFragment;

public class getHomeAsyncTask extends AsyncTask<Object, Boolean, String> {

    HomeFragment callerActivity;
    private final static String TAG = "SkyNet";

    @Override
    protected String doInBackground(Object... params) {
        String serviceUrl = (String) params[0];
        callerActivity = (HomeFragment) params[1];

        functionsWebService gethome = new functionsWebService(serviceUrl);
        try {
            return gethome.webGet();
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        Log.d(TAG, "GetHome: " + response);
        callerActivity.getHome(response);
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }



}
