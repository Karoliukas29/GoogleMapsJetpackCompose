package com.strongtogether.googlemapsjetpackcompose.utils

import android.content.Context
import android.content.pm.PackageManager

object ManifestUtils {
    // Function to retrieve the API key from the manifest file
    fun getApiKeyFromManifest(context: Context): String? {
        return try {
            val applicationInfo = context.packageManager
                .getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
            val bundle = applicationInfo.metaData
            bundle.getString("com.google.android.geo.API_KEY")
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            null
        }
    }
}