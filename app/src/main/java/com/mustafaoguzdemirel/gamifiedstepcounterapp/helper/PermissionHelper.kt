package com.hukukkarar.app.common.helper

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

class PermissionHelper private constructor() {
    val CAM_GALLERY_PERMISSIONS_BELOW_ANDROID_13 = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )

    val CAM_GALLERY_PERMISSIONS_ANDROID_13_AND_ABOVE = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_MEDIA_IMAGES
    )

    fun isCamGalleryPermissionsAllowed(context: Context) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.READ_MEDIA_IMAGES
                    ) == PackageManager.PERMISSION_GRANTED
        else
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED

    fun getPermissionsToRequest() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            CAM_GALLERY_PERMISSIONS_ANDROID_13_AND_ABOVE
        else
            CAM_GALLERY_PERMISSIONS_BELOW_ANDROID_13

    companion object {
        var instance: PermissionHelper? = null
            get() {
                if (field == null) field = PermissionHelper()
                return field
            }
            private set
    }
}
