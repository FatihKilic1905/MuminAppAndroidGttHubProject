package com.fatihkilic.muminappandroid.ui.dashboard;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.FragmentDashboardBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class DashboardFragment extends Fragment implements SensorEventListener {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    private ImageView compassImage;
    private float[] mGravity = new float[3];
    private float[] mGeoMagnetic = new float[3];
    private float azimuth = 0f;
    private float currectAzimuth = 0f;
    private SensorManager mSensorManager;

    private AdView mAdView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        compassImage = binding.compass;
        mSensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = binding.adView;
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();

        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);

        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    public void onPause() {
        super.onPause();

        mSensorManager.unregisterListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        final float alpha = 0.97f;

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            mGravity[0] = alpha*mGravity[0] + (1-alpha) * event.values[0];
            mGravity[1] = alpha*mGravity[1] + (1-alpha) * event.values[1];
            mGravity[2] = alpha*mGravity[2] + (1-alpha) * event.values[2];
        }

        if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {

            mGeoMagnetic[0] = alpha*mGeoMagnetic[0] + (1-alpha) * event.values[0];
            mGeoMagnetic[1] = alpha*mGeoMagnetic[1] + (1-alpha) * event.values[1];
            mGeoMagnetic[2] = alpha*mGeoMagnetic[2] + (1-alpha) * event.values[2];
        }

        float R[] = new float[9];
        float I[] = new float[9];
        boolean success = SensorManager.getRotationMatrix(R,I,mGravity,mGeoMagnetic);

        if (success) {


            float orientation[] = new float[3];
            SensorManager.getOrientation(R,orientation);
            azimuth = (float) Math.toDegrees(orientation[0]);
            azimuth = (azimuth+360)%360;

            Animation anim = new RotateAnimation(-currectAzimuth,-azimuth,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            currectAzimuth = azimuth;
            anim.setDuration(500);
            anim.setRepeatCount(0);
            anim.setFillAfter(true);

            compassImage.startAnimation(anim);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}