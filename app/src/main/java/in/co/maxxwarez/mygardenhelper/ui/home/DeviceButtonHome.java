package in.co.maxxwarez.mygardenhelper.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import in.co.maxxwarez.mygardenhelper.R;

public class DeviceButtonHome extends Fragment {

    public DeviceButtonHome () {
        // Required empty public constructor
    }


    public static DeviceButtonHome newInstance () {
        DeviceButtonHome fragment = new DeviceButtonHome();
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_device_button_home, container, false);

        return v;
    }
}