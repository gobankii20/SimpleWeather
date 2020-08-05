package com.vitavat.simpleweather.utils

import android.Manifest
import androidx.fragment.app.FragmentActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.vitavat.simpleweather.R

class CheckPermission constructor(var fragmentActivity: FragmentActivity) {

    fun checkPermissionLocation(clickCallback: (Boolean) -> Unit) {
        Dexter.withActivity(fragmentActivity)
            .withPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (!hasDeniedPermission(report)) {
                        clickCallback.invoke(true)
                    } else {
                        fragmentActivity.dialogMessage(fragmentActivity.resources.getString(R.string.permission_location))
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>, token: PermissionToken) {
                    token.continuePermissionRequest()
                }

                private fun hasDeniedPermission(report: MultiplePermissionsReport): Boolean {
                    val denyPermission = report.deniedPermissionResponses
                    return denyPermission != null && denyPermission.isNotEmpty()
                }
            }).check()
    }
}