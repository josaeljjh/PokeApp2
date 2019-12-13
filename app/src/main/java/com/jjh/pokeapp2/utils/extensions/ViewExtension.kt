package com.jjh.pokeapp2.utils.extensions

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.snackbar.Snackbar
import com.jjh.pokeapp2.R
import com.jjh.pokeapp2.utils.CustomTypefaceSpan

fun View?.snackbarConexion(mensaje: String) {
    val snackbar = Snackbar.make(this!!, mensaje, Snackbar.LENGTH_LONG)
    val snackBarView = snackbar.view
    snackBarView.setBackgroundColor(ContextCompat.getColor(this.context, R.color.colorApp))
    val textView: TextView = snackBarView.findViewById(R.id.snackbar_text)
    textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_wifi_off, 0, 0, 0)
    textView.compoundDrawablePadding = resources.getDimensionPixelOffset(R.dimen._10sdp)
    //textView.typeface = Typeface.createFromAsset(contexto.assets, "font/frutiger_lt_for_bns_bold.ttf")
    val myCustomFont: Typeface? = ResourcesCompat.getFont(this.context, R.font.roboto_bold)
    textView.typeface = myCustomFont
    textView.setTextColor(ContextCompat.getColor(this.context, R.color.colorBlanco))
    snackbar.show()
}

fun View?.snackbar(mensaje: String) {
    val snackbar = Snackbar.make(this!!, mensaje, Snackbar.LENGTH_LONG)
    val snackBarView = snackbar.view
    snackBarView.setBackgroundColor(ContextCompat.getColor(this.context, R.color.colorApp))
    val textView: TextView = snackBarView.findViewById(R.id.snackbar_text)
    //textView.typeface = Typeface.createFromAsset(contexto.assets, "font/frutiger_lt_for_bns_bold.ttf")
    val myCustomFont: Typeface? = ResourcesCompat.getFont(this.context, R.font.roboto_bold)
    textView.typeface = myCustomFont
    textView.setTextColor(ContextCompat.getColor(this.context, R.color.colorBlanco))
    snackbar.show()
}

fun BottomNavigationView?.configBottom() {
    //remover animacion de menu footer
    this?.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED

    //BottomNavigationViewHelper().disableShiftMode(navigation)
    val font: Typeface? = ResourcesCompat.getFont(this!!.context, R.font.roboto_bold)
    val typefaceSpan = CustomTypefaceSpan("", font!!)
    for (i in 0 until this.menu.size()) {
        //fuentes de menu
        val menuItem = this.menu.getItem(i)
        val spannableTitle = SpannableStringBuilder(menuItem.title)
        spannableTitle.setSpan(typefaceSpan, 0, spannableTitle.length, 0)
        menuItem.title = spannableTitle
        //iconos de menu
        val menuView = this.getChildAt(0) as BottomNavigationMenuView
        val iconView = menuView.getChildAt(i).findViewById<View>(R.id.icon)
        val layoutParams = iconView.layoutParams
        val displayMetrics = resources.displayMetrics
        layoutParams.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25f, displayMetrics).toInt()
        layoutParams.width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25f, displayMetrics).toInt()
        iconView.layoutParams = layoutParams
    }
}