package com.jjh.pokeapp2.utils.extensions

import android.annotation.SuppressLint
import android.os.Build
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jjh.pokeapp2.R

fun ImageView.setVectorForPreLollipop(resourceId: Int) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        this.setImageDrawable(VectorDrawableCompat.create(resources, resourceId, null))

    } else {
        this.setImageDrawable(resources.getDrawable(resourceId, null))
    }
}

fun FloatingActionButton.setVectoForPreLollipop(resourceId: Int){
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        this.setImageDrawable(VectorDrawableCompat.create(resources, resourceId, null))

    } else {
        this.setImageDrawable(resources.getDrawable(resourceId, null))
    }
}

//Fondos de servicio
@SuppressLint("CheckResult")
fun ImageView.setBackgroundUrl(id: String) {
    try {
        val options = RequestOptions()
            .placeholder(R.drawable.pokeball)
            .priority(Priority.NORMAL)
            .format(DecodeFormat.PREFER_RGB_565)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .dontAnimate()
            .fitCenter()

        Glide.with(this)
            .asBitmap()
            .load(id)
            .apply(options)
            .into(this)
    } catch (e: Exception) {
    }
}

@BindingAdapter("image")
fun ImageView.setBackground(id: Int) {
    try {
        val options = RequestOptions()
            .placeholder(R.mipmap.ic_launcher)
            .priority(Priority.NORMAL)
            .format(DecodeFormat.PREFER_RGB_565)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .dontAnimate()
            .fitCenter()

        Glide.with(this)
            .asBitmap()
            .load(id)
            .apply(options)
            .into(this)
    } catch (e: Exception) {
    }
}