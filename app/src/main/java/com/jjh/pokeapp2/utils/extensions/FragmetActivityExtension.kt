package com.jjh.pokeapp2.utils.extensions


import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.jjh.pokeapp2.R

//Activity StatusBarColor
fun FragmentActivity.configureStatusBarColor(
    color: Int = R.color.colorPrimary,
    darkIcons: Boolean = false
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.statusBarColor = ContextCompat.getColor(this, color)
        var flags = window.decorView.systemUiVisibility
        flags = if (darkIcons) {
            flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
        window.decorView.systemUiVisibility = flags
    }
}

fun FragmentActivity.getVersionApp(): String {
    try {
        var versionapp = packageManager.getPackageInfo(packageName, 0).versionName
        var versionmobile = ""
        try {
            versionmobile = versionapp.toLowerCase()
            versionmobile = versionmobile.replace("[^\\d.]".toRegex(), "")
        } catch (e: Exception) {}
        versionapp = versionmobile
        return versionapp
    } catch (e: Exception) {
    }
    return ""
}
