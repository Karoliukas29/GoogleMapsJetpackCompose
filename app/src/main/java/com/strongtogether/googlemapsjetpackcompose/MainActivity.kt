package com.strongtogether.googlemapsjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.strongtogether.googlemapsjetpackcompose.screens.MapScreen
import com.strongtogether.googlemapsjetpackcompose.ui.theme.GoogleMapsJetpackComposeTheme
import com.strongtogether.googlemapsjetpackcompose.viewmodel.MapViewModel

class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoogleMapsJetpackComposeTheme {
                val mapViewModel = MapViewModel()
                MapScreen(mapViewModel)
            }
        }
    }
}

