package in.co.maxxwarez.mygardenhelper.helperClasses;
import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

import in.co.maxxwarez.mygardenhelper.Login;
import in.co.maxxwarez.mygardenhelper.ui.home.HomeFragment;

public class userHelper {
    private static final String TAG = "SkyNet";
    static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    static final DatabaseReference ref = FirebaseDatabase.getInstance("https://mygardenhelper.firebaseio.com").getReference();
    public HashMap<String, HashMap<String, String>> userObject;

    public userHelper () {
    }

    public String getUID () {
        return user.getUid();
    }

    public String getEmailID () {
        return user.getEmail();
    }

    public String getDisplayName () {
        return user.getDisplayName();
    }

    public HashMap getHome(){ return getUser();}

    public boolean isLoggedIn () {
        if (user != null)
            return true;
        else
            return false;
    }


    public HashMap<String, HashMap<String, String>> getUser () {
        Query query = ref.child("users").child(this.getUID()).child("homes");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    HashMap <String, HashMap<String, String>> userObj = new HashMap<String, HashMap<String,String>>();
                    userObj = (HashMap<String, HashMap<String, String>>) dataSnapshot.getValue();
                    userObject = (HashMap<String, HashMap<String, String>>) dataSnapshot.getValue();
                    Log.i(TAG, "HashMap userHelper " + userObject.toString());
                }
                return ;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return userObject;
    }

    public static class userCreate extends AsyncTask<Object, Boolean, String> {

        Login callerActivity;
        private final static String TAG = "SkyNet";

        @Override
        protected String doInBackground(Object... params) {
            String userID = (String) params[0];
            callerActivity = (Login) params[1];
            Query query = ref.child("users").child(user.getUid());
            Log.i(TAG, "Login03: " + query);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.exists()){
                        Log.i(TAG, "Login04: ");
                        ref.child("users").child(user.getUid()).child("displayName").setValue(user.getDisplayName());
                        ref.child("users").child(user.getUid()).child("email").setValue(user.getEmail());

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
          return "true" ;
        }


        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            callerActivity.CreateUserTask(response);
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }



    }
}