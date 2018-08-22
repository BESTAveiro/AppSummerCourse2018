package bestaveiro.appsummercourse;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMarkerDragListener {

    private final String TAG = "MapsActivity";
    protected Activity this_activity = this;
    private LatLng current_location=null;
    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    double end_latitude, end_longitude;

    private Context mContext = MapsActivity.this;

    private static final int REQUEST = 112;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO:clean up
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //TODO: checkLocationPermissions();
        }

        if (!CheckGooglePlayServices()) {
            Log.d("onCreate", "Finishing test case since Google Play Services are not available");
            finish();
        } else {
            Log.d("onCreate", "Google Play Services available.");
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        FloatingActionButton floating_button = findViewById(R.id.fab);
        floating_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(MapsActivity.this);
                builderSingle.setIcon(R.drawable.logo);
                builderSingle.setTitle("Pick a Place");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this_activity, android.R.layout.select_dialog_singlechoice);
                arrayAdapter.add("University of Aveiro");
                arrayAdapter.add("DETI");
                arrayAdapter.add("Recidencies");
                arrayAdapter.add("Super-market");

                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (requestPermissionLocation()) {
                            MarkerOptions markerOptions = new MarkerOptions();
                            Geocoder geocoder = new Geocoder(this_activity);
                            LatLng position = null;
                            Log.d(TAG,"Current latitude:"+current_location.latitude);
                            Log.d(TAG,"Current latitude:"+current_location.longitude);
                            LatLng curr_position = getCurrentLocation();
                            mMap.clear();
                            switch (i) {
                                case 0:
                                    position = new LatLng(40.630327, -8.657603);
                                    mMap.addMarker(new MarkerOptions().position(position).title("University of Aveiro"));
                                    break;
                                case 1:
                                    position = new LatLng(40.633344, -8.659171);
                                    mMap.addMarker(new MarkerOptions().position(position).title("DETI"));
                                    break;
                                case 2:
                                    position = new LatLng(40.635722, -8.644898);
                                    mMap.addMarker(new MarkerOptions().position(position).title("Recidencies"));
                                    break;
                                case 3:
                                    break;

                            }
                            markerOptions.position(curr_position);
                            markerOptions.position(position);
                            mMap.addMarker(markerOptions);

                            mMap.addMarker(markerOptions);
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));
                            mMap.animateCamera(CameraUpdateFactory.zoomIn());
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

                            String url = getMapsApiDirectionsUrl(i);
                            ReadTask downloadTask = new ReadTask();
                            downloadTask.execute(url);
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curr_position,
                                    13));
                            if (ActivityCompat.checkSelfPermission(this_activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this_activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: something?
                                // return;
                                Log.d(TAG, "Permissions required");
                            }
                            mMap.setMyLocationEnabled(true);

                            String strName = arrayAdapter.getItem(i);
                            AlertDialog.Builder builderInner = new AlertDialog.Builder(this_activity);
                            builderInner.setMessage(strName);
                            builderInner.setTitle("Your Selected Item is");
                            builderInner.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            builderInner.show();
                        }
                    }
                });
                builderSingle.show();
            }
        });
    }


    private String getMapsApiDirectionsUrl(int index){
        String url = "https://maps.googleapis.com/maps/api/directions/";
        String output = "json";
        String sensor="sensor=false";
        String destination=null;
        switch (index){
            case 0://University of Aveiro
                destination="destination="+40.630245+","+-8.657538;
                break;
            case 1://DETI
                destination="destination="+40.633311+","+-8.659461;
                break;
            case 2://Mario Sacramento
                destination="destination="+40.635795+","+-8.645371;
                break;
            case 3:
                destination="destination="+40.630245+","+-8.657538;
                break;
        }
        LatLng curr_location=getCurrentLocation();
        String origin="origin="+curr_location.latitude+","+curr_location.longitude;
        if(!destination.isEmpty())
            return url+output+"?"+origin+"&"+destination+"&"+sensor;
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

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

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest= new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.setDraggable(true);
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        end_latitude = marker.getPosition().latitude;
        end_longitude= marker.getPosition().longitude;

        Log.d("end_lat",""+end_latitude);
        Log.d("end_lng",""+end_longitude);
    }

    private int MY_PERMISSIONS_REQUEST_FINE_LOCATION=99;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMarkerDragListener(this);
        getCurrentLocation();

    }


    private LatLng getCurrentLocation()
    {
        if (ContextCompat.checkSelfPermission(this_activity,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this_activity,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(this_activity,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_FINE_LOCATION);
            }
        }

        if(requestPermissionLocation()) {
            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location location) {
                    mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("It's Me!"));
                    current_location= new LatLng(location.getLatitude(), location.getLongitude());
                    Log.d(TAG, "location=" + location.getLongitude() + "," + location.getLatitude());
                }
            });
        }
        return current_location;
    }

    private boolean requestPermissionLocation(){
        if(Build.VERSION.SDK_INT>=23){
            String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
            if(!hasPermissions(mContext,PERMISSIONS)){
                ActivityCompat.requestPermissions((Activity) mContext,PERMISSIONS,REQUEST);
            } else {
                //call get location
            }
        }
        else {
            //call get location
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        switch (requestCode){
            case REQUEST: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //call get location here
                } else {
                    Toast.makeText(mContext,"The app was not allowed to access your location",Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean CheckGooglePlayServices(){
        GoogleApiAvailability googleAPI=GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);

        if(result != ConnectionResult.SUCCESS){
            if(googleAPI.isUserResolvableError(result)){
                googleAPI.getErrorDialog(this,result,0).show();
            }
            return false;
        }
        return true;
    }

    protected synchronized void buildGoogleApiClient(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    /*NEW TUTORIAL*/
    private class ReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... url) {
            String data = "";
            try {
                Log.d("ReadTask","Initialize");
                HttpConnection http = new HttpConnection();
                Log.d("ReadTask","httpConnectionDone");
                Log.d("ReadTask","url: "+url[0]);
                data = http.readUrl(url[0]);
                Log.d("ReadTask","DONE");
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            new ParserTask().execute(result);
            Log.d("ReadTask","onPostExecute DONE");
        }
    }

    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(
                String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                PathJSONParser parser = new PathJSONParser();
                routes = parser.parse(jObject);
                Log.d(TAG,"route size="+routes.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> routes) {
            ArrayList<LatLng> points = null;
            PolylineOptions polyLineOptions = null;

            // traversing through routes

            for (int i = 0; i < routes.size(); i++) {
                points = new ArrayList<LatLng>();
                polyLineOptions = new PolylineOptions();
                List<HashMap<String, String>> path = routes.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                polyLineOptions.addAll(points);
                polyLineOptions.width(10);
                polyLineOptions.color(Color.BLUE);
            }

            mMap.addPolyline(polyLineOptions);
        }
    }

}
