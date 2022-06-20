package com.gradle.shopifyapp

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.gradle.shopifyapp.databinding.ActivityMapsBinding
import com.gradle.shopifyapp.settings.SettingsActivity
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference
import java.io.IOException
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, MapDialogCommunicator {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    var fusedLocationProviderClient: FusedLocationProviderClient? = null
    val LOCATION_PERMISSION_ID = 10
    var currentLocation : Location? = null

    lateinit var mapDialog: MapDialog

    lateinit var preference: MyPreference

    lateinit var latLocation : String
    lateinit var longLocation : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        preference = MyPreference.getInstance(this)!!
        mapDialog = MapDialog(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        fetchLocation()
    }

    @SuppressLint("MissingPermission")
    private fun fetchLocation() {
        if (checkPermissions()) {
            Log.d("TAG", "fetchLocation: 97 ${locationEnabled()}")
            if (locationEnabled()) {
                Log.d("TAG", "fetchLocation: 99")
                fusedLocationProviderClient?.lastLocation?.addOnCompleteListener { location ->
                    if (location != null){
                        this.currentLocation = location.result
                        val mapFragment = supportFragmentManager
                            .findFragmentById(R.id.map) as SupportMapFragment
                        mapFragment.getMapAsync(this)
                    }
                    // requestNewLocationData()
                }
            } else {
                displayPromptForEnablingGPS(this)
            }
        } else {
            requestLocationPermission()
        }
    }

    private fun checkPermissions(): Boolean {
        return (ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun locationEnabled(): Boolean {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_ID
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocation()
            } else {
                requestLocationPermission()
            }
        }
    }


    fun displayPromptForEnablingGPS(activity: Activity) {
        val builder = AlertDialog.Builder(activity)
        val action = Settings.ACTION_LOCATION_SOURCE_SETTINGS
        val message = "Do you want open GPS setting?"
        builder.setMessage(message)
            .setPositiveButton(
                "OK"
            ) { d, id ->
                activity.startActivity(Intent(action))
                d.dismiss()
            }
            .setNegativeButton(
                "Cancel"
            ) { d, id -> d.cancel() }
        builder.create().show()
    }


    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnMapClickListener {
            mMap.clear()
            Log.d("TAG", "onMapReady: CLICK LAT ${it.latitude}")
            Log.d("TAG", "onMapReady: CLICK LONG ${it.longitude}")

            latLocation = it.latitude.toString()
            longLocation = it.longitude.toString()


            mMap.addMarker(
                MarkerOptions().position(it).draggable(true).title("my location")
            )
            mMap.moveCamera(CameraUpdateFactory.newLatLng(it))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it, 16.0f))
            mapDialog.show(supportFragmentManager, "MyCustomFragment")
        }
        fusedLocationProviderClient!!.lastLocation.addOnCompleteListener { task ->
            val mLocation = task.result
            if (mLocation != null) {
                val myHome = LatLng(mLocation.latitude, mLocation.longitude)
                Log.d("TAG", "onMapReady: LAT ${mLocation.latitude}")
                Log.d("TAG", "onMapReady: LONG ${mLocation.longitude}")
                mMap.addMarker(
                    MarkerOptions().position(myHome).draggable(true).title("my location")
                )
                mMap.moveCamera(CameraUpdateFactory.newLatLng(myHome))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myHome, 16.0f))
                // mMap.uiSettings.isZoomControlsEnabled = true;

            }
        }
    }

    //after clicking on save button on dialog of map after choosing the location
    override fun pickedLocation() {
        Log.d("TAG", "pickedLocation: inside PICKED LOCATION ")
        getAddressFromLocation(latLocation,longLocation)
    }

    fun getAddressFromLocation(lat : String, long : String)  {
        var countryName : String
        var cityName : String
        var zipCode : String
        var address : String
        val geocoder = Geocoder(this, Locale.getDefault()) //get default language
        try {

            val addresses = geocoder.getFromLocation(lat.toDouble(), long.toDouble(), 1)
            address = "${addresses[0].featureName} ${addresses[0].thoroughfare} ${addresses[0].locality} ${addresses[0].subAdminArea}"
            cityName = addresses[0].adminArea
            countryName = addresses[0].countryName
            zipCode = addresses[0].postalCode

           // Log.d("TAG", "getAddressFromLocation: $addresses")

            Log.d("TAG", "country: $countryName")
            Log.d("TAG", "city: $cityName")
            Log.d("TAG", "zip code: $zipCode")
            Log.d("TAG", "address: $address")



            var resultIntent = intent.getStringExtra(Constants.FROMSETTINGSFRAGMENT)
            Log.d("TAG", "getAddressFromLocation: FROM----> $resultIntent")

            val intent = Intent(this,SettingsActivity::class.java)
            intent.putExtra(Constants.ADDRESS, address)
            intent.putExtra(Constants.CITY, cityName)
            intent.putExtra(Constants.COUNTRY, countryName)
            intent.putExtra(Constants.ZIPCODE, zipCode)
            intent.putExtra(Constants.FROMMAPSTO, resultIntent)
            startActivity(intent)

            finish()


        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}