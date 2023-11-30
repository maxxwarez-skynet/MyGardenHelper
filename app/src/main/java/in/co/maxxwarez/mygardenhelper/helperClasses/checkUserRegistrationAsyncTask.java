package in.co.maxxwarez.mygardenhelper.helperClasses;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;

import in.co.maxxwarez.mygardenhelper.Login;

public class checkUserRegistrationAsyncTask extends AsyncTask<Object, Boolean, String> {

    Login callerActivity;
    private final static String TAG = "SkyNet";

    @Override
    protected String doInBackground(Object... params) {
        String serviceUrl = (String) params[0];
        callerActivity = (Login) params[1];

        functionsWebService webService = new functionsWebService(serviceUrl);
        try {
            return webService.webGet();
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        callerActivity.checkUserRegistration(response);
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }



}