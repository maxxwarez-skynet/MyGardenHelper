package in.co.maxxwarez.mygardenhelper.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import in.co.maxxwarez.mygardenhelper.helperClasses.userHelper;

import in.co.maxxwarez.mygardenhelper.databinding.FragmentHomeBinding;
import in.co.maxxwarez.mygardenhelper.helperClasses.getHomeAsyncTask;

public class HomeFragment extends Fragment {

    userHelper userHelper = new userHelper();
    ProgressDialog dialog;
    private FragmentHomeBinding binding;

    private final static String TAG = "SkyNet";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initializeDialog("Please wait while we fetch your data.");
        startGetHomeTask();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initializeDialog(String message) {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage(message);
        dialog.show();
    }

    private void startGetHomeTask() {
        final String WEB_SERVICE_URL = "https://getHome-chfzbeamua-uc.a.run.app/?userID="+userHelper.getUID();
        getHomeAsyncTask getHomeTask = new getHomeAsyncTask();
        getHomeTask.execute(WEB_SERVICE_URL,this);
    }

    public void getHome(String s){
        dialog.dismiss();
        if(s.equals("true")){
            Log.i(TAG, "Login: User  registered" + s);

        }
        else{
            Log.i(TAG, "Login: User not registered" + s);
        }
    }
}