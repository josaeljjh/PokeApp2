package com.jjh.pokeapp2.utils.extensions


import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.bancohipotecario.app.core.util.BottomDialogFragment
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

fun FragmentActivity.configureDialogFragment(dialog: Any,
                                             isBottomSheet: Boolean = true,
                                             cancelable: Boolean = false) {
    val receivedDialog = if (isBottomSheet) dialog as BottomDialogFragment else dialog as DialogFragment
    receivedDialog.setStyle(
        DialogFragment.STYLE_NO_TITLE,
        if (isBottomSheet) {
            R.style.BottomSheetDialog
        } else {
            android.R.style.Theme_Black_NoTitleBar_Fullscreen
        }
    )
    receivedDialog.isCancelable = cancelable
    receivedDialog.show(supportFragmentManager, "")
}

fun FragmentActivity.loading():BottomDialogFragment{
   val mBottomSheetDialog = BottomDialogFragment(
        R.layout.sheet_dialog,
        fun(_) {
            //view.Title.text = "Qué servicio programarás"
        })
     configureDialogFragment(mBottomSheetDialog, true, cancelable = false)
    return mBottomSheetDialog
}