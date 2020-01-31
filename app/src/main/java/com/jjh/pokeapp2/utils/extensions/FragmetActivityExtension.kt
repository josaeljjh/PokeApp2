package com.jjh.pokeapp2.utils.extensions


import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.jjh.pokeapp2.utils.BottomDialogFragment
import com.jjh.pokeapp2.R
import com.jjh.pokeapp2.models.PokemonDetalleResponse
import kotlinx.android.synthetic.main.sheet_basic.view.*
import kotlinx.android.synthetic.main.sheet_detalle.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        } catch (e: Exception) {
        }
        versionapp = versionmobile
        return versionapp
    } catch (e: Exception) {
    }
    return ""
}

fun FragmentActivity.configureDialogFragment(
    dialog: Any,
    isBottomSheet: Boolean = true,
    cancelable: Boolean = false
) {
    val receivedDialog =
        if (isBottomSheet) dialog as BottomDialogFragment else dialog as DialogFragment
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

fun FragmentActivity.loading(): BottomDialogFragment {
    val mBottomSheetDialog = BottomDialogFragment(
        R.layout.sheet_dialog,
        fun(_) {
            //view.Title.text = "Qué servicio programarás"
        })
    configureDialogFragment(mBottomSheetDialog, true, cancelable = false)
    return mBottomSheetDialog
}

fun FragmentActivity.sheetDialog(
    title: String = "",
    message: String = ""){
    var mBottomSheetDialog: BottomDialogFragment? = null
    mBottomSheetDialog = BottomDialogFragment(
        R.layout.sheet_basic,
        fun(view) {
            view.textTitulo.text = title
            view.textDetalle.text = message
            view.bt_close.setSafeOnClickListener{
                mBottomSheetDialog?.dismiss()
            }
        })
    configureDialogFragment(mBottomSheetDialog, true, cancelable = true)
}

fun FragmentActivity.sheetDialogDetalle(detalle: PokemonDetalleResponse?, imagen:String
   ){
    var mBottomSheetDialog: BottomDialogFragment? = null
    mBottomSheetDialog = BottomDialogFragment(
        R.layout.sheet_detalle,
        fun(view) {
            view.imgDetalle.setBackgroundUrl(imagen)
            view.nombre.text = detalle?.name?.capitalize()
            view.btnClose.setSafeOnClickListener{
                mBottomSheetDialog?.dismiss()
            }
            /*view.btnAgregar.setSafeOnClickListener{
                mBottomSheetDialog?.let { dialog -> saveAction.invoke(dialog) }
            }*/
        })
    configureDialogFragment(mBottomSheetDialog, true, cancelable = true)
}

fun FragmentActivity.launch(action: suspend () -> Unit) {
    lifecycleScope.launch {
        withContext(Dispatchers.Main) {
            action.invoke()
        }
    }
}

fun FragmentActivity.setTransparentStatusBar() {
    window.apply {
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        statusBarColor = Color.TRANSPARENT
    }
}

//Inline function for starting an Activity
inline fun <reified T : FragmentActivity> FragmentActivity.launchActivity(
    closeCurrent: Boolean = false,
    noinline init: Intent.() -> Unit = {}
) {
    val i = Intent(this, T::class.java)
    i.init()
    startActivity(i)
    overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out)
    if (closeCurrent) {
        finish()
    }
}