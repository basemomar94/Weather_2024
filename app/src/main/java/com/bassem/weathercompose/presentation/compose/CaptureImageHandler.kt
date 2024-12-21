package com.bassem.weathercompose.presentation.compose

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import com.bassem.weathercompose.R
import com.bassem.weathercompose.utils.createImageUri

@Composable
fun CaptureImageHandler(onImageCaptured: (Bitmap?) -> Unit) {
    val context = LocalContext.current
    val cameraPermission = Manifest.permission.CAMERA

    val resolver = context.contentResolver
    val tempUri = remember { mutableStateOf<Uri?>(null) }

    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess && tempUri.value != null) {
            val bitmap = resolver.openInputStream(tempUri.value!!)?.use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
            onImageCaptured(bitmap)
        } else {
            onImageCaptured(null)
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val uri = context.createImageUri()
            tempUri.value = uri
            if (uri != null) {
                takePictureLauncher.launch(uri)
            }
        } else {
            onImageCaptured(null)
        }
    }

    Button(
        onClick = {
            if (ActivityCompat.checkSelfPermission(context, cameraPermission) == PackageManager.PERMISSION_GRANTED) {
                val uri = context.createImageUri()
                tempUri.value = uri
                if (uri != null) {
                    takePictureLauncher.launch(uri)
                }
            } else {
                permissionLauncher.launch(cameraPermission)
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.capture_image))
    }
}




