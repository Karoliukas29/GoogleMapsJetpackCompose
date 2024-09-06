package com.strongtogether.googlemapsjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.android.libraries.places.api.Places
import com.strongtogether.googlemapsjetpackcompose.screens.MapScreen
import com.strongtogether.googlemapsjetpackcompose.ui.theme.GoogleMapsJetpackComposeTheme
import com.strongtogether.googlemapsjetpackcompose.utils.ManifestUtils
import com.strongtogether.googlemapsjetpackcompose.viewmodel.MapViewModel

class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the API key from the manifest file
        val apiKey = ManifestUtils.getApiKeyFromManifest(this)
        // Initialize the Places API with the retrieved API key
        if (!Places.isInitialized() && apiKey != null) {
            Places.initialize(applicationContext, apiKey)
        }

        enableEdgeToEdge()
        setContent {
                MaterialTheme {
                    // Create a Surface container that uses the theme's background color
                    Surface(
                        modifier = Modifier.fillMaxSize(), // Make the surface fill the entire screen
                        color = MaterialTheme.colorScheme.background // Use the background color from the theme
                    ) {
                        // Pass the MapViewModel to the MapScreen composable
                        val mapViewModel = MapViewModel()
                        MapScreen(mapViewModel)
                    }
                }
        }
    }
}

