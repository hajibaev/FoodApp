package com.example.cafetestapp.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.cafetestapp.R
import com.example.cafetestapp.databinding.ActivityMainBinding
import com.example.ui_core.extensions.showView
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.toolbar.dateFormat.text = data()
        val navView: BottomNavigationView = binding.mainBottomNavView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        navView.setupWithNavController(navController)
        requestPermission()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            ACCESS_LOCATION_PERMISSION_ID -> {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermission()
                } else {
                    val geocoder = Geocoder(this, Locale.getDefault())
                    val mLocationRequest: LocationRequest = LocationRequest.create()
                    mLocationRequest.interval = 600
                    mLocationRequest.fastestInterval = 500
                    mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                    val mLocationCallback: LocationCallback = object : LocationCallback() {
                        override fun onLocationResult(locationResult: LocationResult) {
                            for (location in locationResult.locations) {
                                location?.let {
                                    val fromLocation = geocoder.getFromLocation(
                                        it.latitude,
                                        it.longitude,
                                        1
                                    )
                                    if (!fromLocation.isNullOrEmpty()) {
                                        fromLocation[0].locality?.let {
                                            binding.toolbar.userLocation.text = it
                                        }
                                    }
                                }
                            }
                        }
                    }
                    LocationServices.getFusedLocationProviderClient(this)
                        .requestLocationUpdates(mLocationRequest, mLocationCallback, null)
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun data(): String {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("d MMMM yyyy")
        return dateFormat.format(currentDate)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ), ACCESS_LOCATION_PERMISSION_ID
        )
    }

    companion object {
        private const val ACCESS_LOCATION_PERMISSION_ID = 1
    }
}

