package com.strongtogether.googlemapsjetpackcompose.viewmodel

import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class MapViewModel: ViewModel() {

    // State to hold the user's location as LatLng (latitude and longitude)
    private val _userLocation = mutableStateOf<LatLng?>(null)
    val userLocation: State<LatLng?> = _userLocation

    // State to hold the selected place location as LatLng
    private val _selectedLocation = mutableStateOf<LatLng?>(null)
    val selectedLocation: State<LatLng?> = _selectedLocation


    // Function to fetch the user's location and update the state
    fun fetchUserLocation(context: Context, fusedLocationClient: FusedLocationProviderClient) {
        // Check if the location permission is granted
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            try {
                // Fetch the last known location
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    location?.let {
                        // Update the user's location in the state
                        val userLatLng = LatLng(it.latitude, it.longitude)
                        _userLocation.value = userLatLng
                    }
                }
            } catch (e: SecurityException) {
                Timber.e("Permission for location access was revoked: ${e.localizedMessage}")
            }
        } else {
            Timber.e("Location permission is not granted.")
        }
    }

    // Function to geocode the selected place and update the selected location state
    fun selectLocation(selectedPlace: String, context: Context) {
        viewModelScope.launch {
            val geocoder = Geocoder(context)
            val addresses = withContext(Dispatchers.IO) {
                // Perform geocoding on a background thread
                geocoder.getFromLocationName(selectedPlace, 1)
            }
            if (!addresses.isNullOrEmpty()) {
                // Update the selected location in the state
                val address = addresses[0]
                val latLng = LatLng(address.latitude, address.longitude)
                _selectedLocation.value = latLng
            } else {
                Timber.tag("MapScreen").e("No location found for the selected place.")
            }
        }
    }
}