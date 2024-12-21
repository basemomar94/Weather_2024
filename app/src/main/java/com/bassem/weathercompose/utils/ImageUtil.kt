package com.bassem.weathercompose.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Paint
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore

fun Bitmap.saveImageWithText(
    text: String
): Bitmap {
    val width = this.width
    val height = this.height

    val resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = android.graphics.Canvas(resultBitmap)

    canvas.drawBitmap(this, 0f, 0f, null)

    val paint = Paint().apply {
        textSize = width * 0.05f
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
    }

    val x = width / 2f
    val y = height - (paint.textSize + 20f)

    canvas.drawText(text, x, y, paint)

    return resultBitmap
}


fun Bitmap.saveBitmapToGallery(context: Context) {
    val resolver = context.contentResolver
    val contentValues = ContentValues().apply {
        put(
            MediaStore.MediaColumns.DISPLAY_NAME,
            "WeatherOverlay_${System.currentTimeMillis()}.png"
        )
        put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
    }


    val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    uri?.let {
        resolver.openOutputStream(it).use { outputStream ->
            if (outputStream != null) {
                val highQualityBitmap = this.copy(Bitmap.Config.ARGB_8888, true)
                highQualityBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                highQualityBitmap.recycle()
            }
        }
    }
}

fun Context.createImageUri(): Uri? {
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "captured_image_${System.currentTimeMillis()}.jpg")
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
    }
    return this.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
}




