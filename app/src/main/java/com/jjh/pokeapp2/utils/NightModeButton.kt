package com.jjh.pokeapp2.utils

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import com.jjh.pokeapp2.R

class NightModeButton : RelativeLayout {

    //Context
    lateinit var mContext: Context
    lateinit var mLayoutInflater: LayoutInflater

    //Views
    lateinit var switchIV: ImageView
    lateinit var switchRL: CardView

    //Listener
    private var onSwitchListener: OnSwitchListener? = null
    var inAnimation = false

    var isNight = false

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    fun init(context: Context) {
        mContext = context
        mLayoutInflater = LayoutInflater.from(context)

        val rootView = mLayoutInflater.inflate(R.layout.night_mode_button_layout, this, true)
        switchRL = rootView.findViewById(R.id.switchRL)
        switchIV = rootView.findViewById(R.id.switchIV)


        switchRL.setOnClickListener { v ->
            Animacion(true)
        }

    }

    fun Animacion(click: Boolean?) {
        if (isNight && !inAnimation) {
            isNight = false
            inAnimation = true
            ObjectAnimator
                .ofFloat(switchIV, "rotation", 0f,360f)
                .setDuration(400)
                .start()
            ObjectAnimator
                .ofFloat(switchIV, "translationX", switchRL.width /2f, 0f)
                .setDuration(400)
                .start()
            val handler = Handler()
            handler.postDelayed(
                { switchIV.setImageDrawable(mContext.getDrawable(R.drawable.day_icon)) },
                350
            )
            val valueAnimator =
                ValueAnimator.ofArgb(Color.parseColor("#353535"), Color.parseColor("#dadada"))
            valueAnimator.duration = 400
            valueAnimator.start()
            valueAnimator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {}
                override fun onAnimationEnd(animator: Animator) {
                    inAnimation = false
                    //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                override fun onAnimationCancel(animator: Animator) {}
                override fun onAnimationRepeat(animator: Animator) {}
            })
            valueAnimator.addUpdateListener { animation -> switchRL.setCardBackgroundColor(animation.animatedValue as Int) }
            if (click!!) {
                nightModeButtonClicked(isNight)
            }

        } else {
            if (!inAnimation) {
                isNight = true
                ObjectAnimator
                    .ofFloat(switchIV, "rotation", 360f, 0f)
                    .setDuration(400)
                    .start()
                ObjectAnimator
                    .ofFloat(switchIV, "translationX", 0f, switchRL.width / 2f)
                    .setDuration(400)
                    .start()
                val valueAnimator =
                    ValueAnimator.ofArgb(Color.parseColor("#dadada"), Color.parseColor("#353535"))
                valueAnimator.duration = 400
                valueAnimator.start()
                valueAnimator.addListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animator: Animator) {}
                    override fun onAnimationEnd(animator: Animator) {
                        inAnimation = false
                        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    override fun onAnimationCancel(animator: Animator) {}
                    override fun onAnimationRepeat(animator: Animator) {}
                })
                valueAnimator.addUpdateListener { animation ->
                    switchRL.setCardBackgroundColor(
                        animation.animatedValue as Int
                    )
                }
                switchIV.setImageDrawable(mContext.getDrawable(R.drawable.night_icon))
                if (click!!) {
                    nightModeButtonClicked(isNight)
                }
            }
        }
    }

    fun setNight(night: Boolean?) {
        isNight = !night!!
        Animacion(isNight)
    }

    interface OnSwitchListener {
        fun onSwitchListener(isNight: Boolean)
    }

    fun setOnSwitchListener(onSwitchListener: OnSwitchListener) {
        this.onSwitchListener = onSwitchListener
    }

    private fun nightModeButtonClicked(isNight: Boolean) {
        if (onSwitchListener != null) {
            onSwitchListener!!.onSwitchListener(isNight)
        }
    }

    fun animacionNoche(){
            isNight = true
            inAnimation = false
            ObjectAnimator
                .ofFloat(switchIV, "rotation", 360f, 0f)
                .setDuration(400)
                .start()
            ObjectAnimator
                .ofFloat(switchIV, "translationX", 0f, switchRL.width / 2f)
                .setDuration(400)
                .start()
            val valueAnimator =
                ValueAnimator.ofArgb(Color.parseColor("#dadada"), Color.parseColor("#353535"))
            valueAnimator.duration = 400
            valueAnimator.start()
            valueAnimator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {}
                override fun onAnimationEnd(animator: Animator) {
                    inAnimation = false
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                override fun onAnimationCancel(animator: Animator) {}
                override fun onAnimationRepeat(animator: Animator) {}
            })
            valueAnimator.addUpdateListener { animation ->
                switchRL.setCardBackgroundColor(
                    animation.animatedValue as Int
                )
            }
            switchIV.setImageDrawable(mContext.getDrawable(R.drawable.night_icon))

    }
}