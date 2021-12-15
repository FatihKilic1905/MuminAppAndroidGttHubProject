package com.fatihkilic.muminappandroid.ui.dashboard;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class DashboardFragment extends Fragment implements SensorEventListener {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    private ImageView compassImage;
    private float[] mGravity = new float[3];
    private float[] mGeoMagnetic = new float[3];
    private float azimuth = 0f;
    private float currectAzimuth = 0f;
    private SensorManager mSensorManager;

    LocationManager locationManager;
    LocationListener locationListener;
    ActivityResultLauncher<String> permisionLauncher;
    float bearingTo;

    private AdView mAdView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        registerLauncher();

        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {

               Location from = new Location(LocationManager.GPS_PROVIDER);
                Location to = new Location(LocationManager.GPS_PROVIDER);
                from.setLatitude(location.getLatitude());
                from.setLongitude(location.getLongitude());
                to.setLongitude(39.826206);
                to.setLatitude(21.422487);

                System.out.println("enlem" + location.getLatitude());
                System.out.println("enlem" + location.getLongitude());

                bearingTo = from.bearingTo(to);

                System.out.println("bearing " + bearingTo);



                System.out.println("location" + location.toString());
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

                System.out.println("enabled");

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

                System.out.println("disabled");

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }
        };



        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),Manifest.permission.ACCESS_FINE_LOCATION)) {

                Snackbar.make(binding.getRoot(), "Pusula konumunuzu kullanmak istiyor", Snackbar.LENGTH_INDEFINITE).setAction("İzin ver", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        permisionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);

                    }
                }).show();

            } else {

                permisionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);

            }

        } else {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1,locationListener);

        }

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


    private void registerLauncher () {

        permisionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {

                if (result) {

                    if (ContextCompat.checkSelfPermission(requireActivity(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1,locationListener);
                    }

                } else {

                    Toast.makeText(requireActivity(),"Pusula izin verilmeden çalışmaz",Toast.LENGTH_LONG).show();

                }

            }
        });

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
            azimuth = (azimuth+180)%360;

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