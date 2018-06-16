package przemo.javaapp;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SupportMapFragment mapFragment;
    private Polyline gpsTrack;
    private GoogleMap map;
    private LocationManager locationManager;
    private LocationListener listener;
    private Button b;
    private Spinner spinner;
    private TextView distance;
    private TextView speed;
    private TextView time;
    private int Distance;
    private int UserId;
    private int TotalSecs;
    private long ActId;
    private float maxSpeed;
    private boolean IsStarted;
    List<String> list;
    DatabaseHelper myDb;
    MediaPlayer mp1;


    Location mCurrentLocation;
    Location mFirstLocation;
    Location mBeforeLocation;

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mp1 = MediaPlayer.create(this, R.raw.ding);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<String>();
        maxSpeed = 0;
        myDb = new DatabaseHelper(this);
        UserId = Integer.parseInt(getIntent().getStringExtra("UserId"));
        b = (Button) findViewById(R.id.start);
        spinner = (Spinner) findViewById(R.id.spinner);
        distance = (TextView) findViewById(R.id.distance);
        speed = (TextView) findViewById(R.id.speed);
        time = (TextView) findViewById(R.id.time);
        IsStarted = false;
        mFirstLocation = null;
        Distance = 0;
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if(IsStarted) {
                    if (mFirstLocation == null) {
                        mFirstLocation = mBeforeLocation = mCurrentLocation = location;
                    } else {
                        mBeforeLocation = mCurrentLocation;
                        mCurrentLocation = location;
                    }
                    int totalSecs = (int) ((mCurrentLocation.getTime() - mFirstLocation.getTime()) / 1000);
                    TotalSecs = totalSecs;
                    int hours = totalSecs / 3600;
                    int minutes = (totalSecs % 3600) / 60;
                    int seconds = totalSecs % 60;
                    time.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));

                    Distance += mBeforeLocation.distanceTo(mCurrentLocation);
                    distance.setText(Distance + " m");
                    float Speed = (float) (3.6 * (mBeforeLocation.distanceTo(mCurrentLocation) / ((mCurrentLocation.getTime() - mBeforeLocation.getTime()) / 1000)));
                    speed.setText((Float.isNaN(Speed) ? 0.0 : Speed) + " km/h");
                    if(maxSpeed < Speed) {
                        maxSpeed = Speed;
                    }
                    if (mBeforeLocation.getLatitude() != mCurrentLocation.getLatitude() && mBeforeLocation.getLongitude() != mCurrentLocation.getLongitude()
                            || mFirstLocation.getLongitude() == mCurrentLocation.getLongitude() && mFirstLocation.getLatitude() == mCurrentLocation.getLatitude())
                        updateTrack();
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
            }
        };

        if (TextUtils.isEmpty(getResources().getString(R.string.google_maps_api_key))) {
            throw new IllegalStateException("You forgot to supply a Google Maps API key");
        }


            mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
       // mapFragment.
        if (mapFragment != null) {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap map) {
                    loadMap(map);
                }
            });
        } else {
            Toast.makeText(this, "Error - Map Fragment was null!!", Toast.LENGTH_SHORT).show();
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        configure_button();
    }
    void configure_button(){
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }
        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mp1.start();
                if(IsStarted) {
                    IsStarted = false;

                    myDb.updateActivity(ActId, Distance, TotalSecs, maxSpeed);
                    mFirstLocation = mBeforeLocation = mCurrentLocation = null;
                    gpsTrack.remove();
                    ActId = -1;
                } else {
                    long id = myDb.insertActivity(UserId);
                    if(id>0) {
                        IsStarted = true;
                        ActId = id;
                    }
                }
                //noinspection MissingPermission
                locationManager.requestLocationUpdates("gps", 900, 0, listener);
            }
        });
    }


    protected void loadMap(GoogleMap googleMap) {
        map = googleMap;

        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(Color.CYAN);
        polylineOptions.width(4);
        gpsTrack = map.addPolyline(polylineOptions);
        if (map != null) {
            // Map is ready
            Toast.makeText(this, "Map Fragment was loaded properly!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error - Map was null!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        time.setText(String.format("%02d:%02d:%02d", 0, 0, 0));
        distance.setText("0 m");
        speed.setText("0 km/h");
        if (id == R.id.nav_start_activity) {
            b.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.GONE);
        } else if (id == R.id.nav_story_of_activities) {
            list.clear();
            b.setVisibility(View.GONE);
            spinner.setVisibility(View.VISIBLE);
            spinner.setPrompt("Select your favorite story!");
            Cursor cursor = myDb.getAllActivities(UserId);
            while (cursor.moveToNext()) {
                list.add(cursor.getString( cursor.getColumnIndex("ID") ));
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view,
                                           int position, long id) {
                    Object item = adapterView.getItemAtPosition(position);
                    if (item != null) {
                        loadTrack(item.toString());
                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    // TODO Auto-generated method stub

                }
            });

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /*
     * Called when the Activity is no longer visible.
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Display the connection status

        if (mCurrentLocation != null) {
            Toast.makeText(this, "GPS location was found!", Toast.LENGTH_SHORT).show();
            LatLng latLng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
            map.animateCamera(cameraUpdate);
        } else {
            //Toast.makeText(this, "Current location was null, enable GPS on emulator!", Toast.LENGTH_SHORT).show();
        }
    }



        private void updateTrack() {
            List<LatLng> points = gpsTrack.getPoints();
            LatLng lastKnownLatLng = new LatLng(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude());
            map.moveCamera(CameraUpdateFactory.newLatLng(lastKnownLatLng));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(lastKnownLatLng, 20));
            points.add(lastKnownLatLng);
            gpsTrack.setPoints(points);
            myDb.insertActivity_Point(ActId, mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude());
        }
    private void loadTrack(String id) {
        List<LatLng> points = gpsTrack.getPoints();
        points.clear();
        Cursor cursor = myDb.getAllPoints(Integer.parseInt(id));
        LatLng lastKnownLatLng = null;
        while (cursor.moveToNext()) {
            lastKnownLatLng = new LatLng(Double.parseDouble(cursor.getString(cursor.getColumnIndex("Latitude"))), Double.parseDouble(cursor.getString(cursor.getColumnIndex("Longitude"))));
            points.add(lastKnownLatLng);
        }
        if(lastKnownLatLng != null) {
            map.moveCamera(CameraUpdateFactory.newLatLng(lastKnownLatLng));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(lastKnownLatLng, 20));
            gpsTrack.setPoints(points);
        }

        Cursor cursor2 = myDb.getActivities(id);
        cursor2.moveToNext();
        int times=cursor2.getInt(cursor2.getColumnIndex("Duration"));
        int hours = times / 3600;
        int minutes = (times % 3600) / 60;
        int seconds = times % 60;
        time.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        distance.setText(cursor2.getString(cursor2.getColumnIndex("Distance"))+" m");
        speed.setText(cursor2.getString(cursor2.getColumnIndex("MaxSpeed"))+" km/h");

    }


    private boolean isGooglePlayServicesAvailable() {
        // Check that Google Play services is available
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // In debug mode, log the status
            Log.d("Location Updates", "Google Play services is available.");
            return true;
        } else {
            // Get the error dialog from Google Play services
            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                    CONNECTION_FAILURE_RESOLUTION_REQUEST);

            // If Google Play services can provide an error dialog
            if (errorDialog != null) {
                // Create a new DialogFragment for the error dialog
                MainActivity.ErrorDialogFragment errorFragment = new MainActivity.ErrorDialogFragment();
                errorFragment.setDialog(errorDialog);
                errorFragment.show(getSupportFragmentManager(), "Location Updates");
            }

            return false;
        }
    }

    // Define a DialogFragment that displays the error dialog
    public static class ErrorDialogFragment extends android.support.v4.app.DialogFragment {

        // Global field to contain the error dialog
        private Dialog mDialog;

        // Default constructor. Sets the dialog field to null
        public ErrorDialogFragment() {
            super();
            mDialog = null;
        }

        // Set the dialog to display
        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }

        // Return a Dialog to the DialogFragment.
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return mDialog;
        }
    }
}
