package app.foodfinderapp.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import app.foodfinderapp.Application
import app.foodfinderapp.MapFragment
import app.foodfinderapp.ui.MainActivity
import app.foodfinderapp.R
import java.util.*

class LocationActivity : AppCompatActivity() {
    private val REQUEST_LOCATION_PERMISSION = 1
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    private var mapFragment: MapFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_map)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = LocationListener { location ->
            val latitude = location.latitude
            val longitude = location.longitude
            val address = getAddress(latitude, longitude)

            // update map fragment
            val fragmentManager = supportFragmentManager
            mapFragment = fragmentManager.findFragmentById(R.id.map) as? MapFragment
            if (mapFragment != null) {
                mapFragment?.updateLocation(address)
            } else {
                mapFragment = MapFragment.newInstance(address)
                mapFragment?.let {
                    fragmentManager.beginTransaction()
                        .add(R.id.map, it)
                        .commit()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        } else {
            startLocationUpdates()
        }
    }

    override fun onStop() {
        super.onStop()
        locationManager.removeUpdates(locationListener)
    }

    private fun startLocationUpdates() {
        try {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0,
                0f,
                locationListener
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    private fun getAddress(latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        val address = addresses!![0].getAddressLine(0)
        return address
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates()
            } else {
                Toast.makeText(
                    this,
                    "You need to allow the application to access the location permission to get the current location information.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}