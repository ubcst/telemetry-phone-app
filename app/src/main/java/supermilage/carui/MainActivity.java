package supermilage.carui;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 23940;
    private LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if the Android API version >= 23
        // Starting with API 23, users grant permissions to apps while the app is running
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
            if( ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION )
                    != PackageManager.PERMISSION_GRANTED ) {
                if( ActivityCompat.shouldShowRequestPermissionRationale( this, Manifest.permission.ACCESS_COARSE_LOCATION ) ) {
                    // Show explanation
                } else {
                    // Request permission
                    ActivityCompat.requestPermissions( this, new String[]{ Manifest.permission.ACCESS_COARSE_LOCATION },
                            MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION );
                }
            } else {
                // Register listener for LocationManager
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }
        } else {
            // Register listener for LocationManager
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
    }

    @Override
    public void onRequestPermissionsResult( int requestCode, String permissions[], int[] grantResults ) {
        switch( requestCode ) {
            case MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION: {
                if( grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ) {

                } else {
                    Toast.makeText( getApplication(), "Permission required", Toast.LENGTH_LONG ).show();
                }

                return;
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
