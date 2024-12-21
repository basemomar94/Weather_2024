package com.bassem.weathercompose.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient

fun Context.getLastKnownLocation(
    fusedLocationClient: FusedLocationProviderClient,
    onLocationRetrieved: (latitude: String?, longitude: String?) -> Unit
) {
    if (ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        try {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    onLocationRetrieved(location.latitude.toString(), location.longitude.toString())
                } else {
                    onLocationRetrieved(null, null)
                }
            }.addOnFailureListener {
                onLocationRetrieved(null, null)
            }
        } catch (e: SecurityException) {
            onLocationRetrieved(null, null)
        }
    } else {
        onLocationRetrieved(null, null)
    }
}
