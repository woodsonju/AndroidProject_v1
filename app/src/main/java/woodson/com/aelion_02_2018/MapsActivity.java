package woodson.com.aelion_02_2018;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private StationBean stationBean;
    private PositionBean positionBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        MonAT monAT = new MonAT();
        monAT.execute();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    // mMap.setInfoWindowAdapter();

        // Add a marker in Sydney and move the camera
        LatLng toulouse = new LatLng(43.59999, 1.43333);
        mMap.addMarker(new MarkerOptions().position(toulouse).title("Marker in Toulouse"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(toulouse));

    }

    public class MonAT extends AsyncTask {
        ArrayList <StationBean> result ;
        String url = "https://api.jcdecaux.com/vls/v1/stations?contract=Toulouse&apiKey=b633e73d1cadd575af117c62de8e705332416d9b";

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                result =  WSUtils.getStations();
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }


        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            LatLngBounds.Builder latLngBounds= new LatLngBounds.Builder();
            for(StationBean st: result)
            {
                LatLng pos = new LatLng(st.getPosition().getLat(), st.getPosition().getLng());
                mMap.addMarker(new MarkerOptions().position(pos).title(st.name.toString()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));

                latLngBounds.include(pos);
            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds.build(), 100));
            Toast.makeText(MapsActivity.this, "Asyntask effecuté",   Toast.LENGTH_LONG).show();

        }
    }
}
