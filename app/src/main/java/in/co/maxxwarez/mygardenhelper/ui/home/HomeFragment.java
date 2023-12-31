package in.co.maxxwarez.mygardenhelper.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import in.co.maxxwarez.mygardenhelper.R;
import in.co.maxxwarez.mygardenhelper.helperClasses.GlobalVars;
import in.co.maxxwarez.mygardenhelper.helperClasses.userHelper;
import in.co.maxxwarez.mygardenhelper.ui.setup.NoSetUp;
import in.co.maxxwarez.mygardenhelper.ui.setup.SetUpButton;
import in.co.maxxwarez.mygardenhelper.ui.fragments.DetailList;
import in.co.maxxwarez.mygardenhelper.ui.setup.InstructionsDetail;

public class HomeFragment extends Fragment {
    private final static String TAG = "SkyNet";


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    static final DatabaseReference ref = FirebaseDatabase.getInstance("https://mygardenhelper.firebaseio.com").getReference();
    Query query = ref.child("users").child(user.getUid()).child("homes");

    public View onCreateView (@NonNull LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState) {
        userHelper userHelper = new userHelper();
        userHelper.getUser();
       // getHome getHome = new getHome();
      //  Log.i(TAG, "Home Frag 2 " + getHome.execute());

        View v = inflater.inflate(R.layout.fragment_home, container, false);


        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    homeSet();
                }
                else
                {
                    noHomeSet();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return v;
    }

    public void noHomeSet () {
        NoSetUp noSetUp = NoSetUp.newInstance();
        Bundle args = new Bundle();
        args.putString("text", "You don't have a Home setup");
        noSetUp.setArguments(args);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeList, noSetUp).commit();
        setUpButton();
    }

    private void setUpButton () {
        SetUpButton setUpButton = SetUpButton.newInstance();
        Bundle args = new Bundle();
        args.putString("text", "Set Home");
        setUpButton.setArguments(args);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_settings_list, setUpButton).commit();
        setUpInstructionsDetail();
    }

    public void setUpInstructionsDetail () {
        InstructionsDetail instructionsDetail = InstructionsDetail.newInstance();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_details_list, instructionsDetail).commit();
    }

    public void homeSet () {
        HomeList homeList = HomeList.newInstance();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeList, homeList).commit();
        settingsList();
    }

    public void settingsList () {
        HomeSettingsList settingsList = HomeSettingsList.newInstance();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_settings_list, settingsList).commit();
        //detalisList();
    }

    private void detalisList () {
        DetailList detailList = DetailList.newInstance();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_details_list, detailList).commit();
    }


}