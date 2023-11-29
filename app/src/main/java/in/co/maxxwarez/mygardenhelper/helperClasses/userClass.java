package in.co.maxxwarez.mygardenhelper.helperClasses;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import in.co.maxxwarez.mygardenhelper.MainActivity;

public class userClass extends AsyncTask<String, String, String> {

    ProgressDialog progressDialog;
    String apiUrl = "https://getuser-chfzbeamua-uc.a.run.app?userID=CYpYgtRWNTTuNYDbH9jfVZPYXbn2    ";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // display a progress dialog for good user experiance

    }

    @Override
    protected String doInBackground(String... params) {

        // implement API in background and store the response in current variable
        String current = "";
        try {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(apiUrl);

                urlConnection = (HttpURLConnection) url
                        .openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader isw = new InputStreamReader(in);

                int data = isw.read();
                while (data != -1) {
                    current += (char) data;
                    data = isw.read();
                    System.out.print(current);

                }
                // return the data to onPostExecute method
                return current;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Exception: " + e.getMessage();
        }
        return current;
    }

    @Override
    protected void onPostExecute(String s) {

        Log.d("data", s.toString());
        // dismiss the progress dialog after receiving data from API



    }

}
