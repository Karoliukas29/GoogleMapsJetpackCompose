package com.strongtogether.googlemapsjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.strongtogether.googlemapsjetpackcompose.screens.MapScreen
import com.strongtogether.googlemapsjetpackcompose.ui.theme.GoogleMapsJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoogleMapsJetpackComposeTheme {
            MapScreen()
            }
        }
    }
}

