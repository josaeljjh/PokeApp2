package com.jjh.pokeapp2.views.welcome.login

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*
import com.jjh.pokeapp2.R
import com.jjh.pokeapp2.utils.NightModeButton
import com.jjh.pokeapp2.utils.extensions.configureStatusBarColor
import com.jjh.pokeapp2.utils.extensions.setBackground
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        config()
        switchMode()
        onClickListener()
    }

    fun config() {
        val modeType = getDefaultNightMode()
        if (modeType == MODE_NIGHT_YES) {
            configureStatusBarColor(R.color.light_black, false)
            animacionNoche()
        } else {
            configureStatusBarColor(R.color.blanco_oscuro, true)
            //switchDarkMode.setNight(false)
        }
        //configureStatusBarColor(R.color.blanco_oscuro, true)
        logoPoke.setBackground(R.drawable.pokemon_icon)
        logoUser.setBackground(R.drawable.player)
    }

    fun switchMode() {
        switchDarkMode.setOnSwitchListener(object : NightModeButton.OnSwitchListener {
            override fun onSwitchListener(isNight: Boolean) {
                if (isNight) {
                    Toast.makeText(this@LoginActivity, isNight.toString(), Toast.LENGTH_SHORT)
                        .show()
                    configureStatusBarColor(R.color.light_black, false)
                } else {
                    Toast.makeText(this@LoginActivity, isNight.toString(), Toast.LENGTH_SHORT)
                        .show()
                    configureStatusBarColor(R.color.blanco_oscuro, true)
                }
            }
        })
    }

    fun onClickListener() {
        btnFacebookCust.setOnClickListener {
            //setDefaultNightMode(MODE_NIGHT_YES)
            setDefaultNightMode(MODE_NIGHT_YES)
            delegate.localNightMode = MODE_NIGHT_YES
            delegate.applyDayNight()
            recreate()
            configureStatusBarColor(R.color.light_black, false)
            switchDarkMode.setNight(true)
        }
        btnGoogle.setOnClickListener {
            //setDefaultNightMode(MODE_NIGHT_NO)

            setDefaultNightMode(MODE_NIGHT_NO)
            delegate.localNightMode = MODE_NIGHT_NO
            delegate.applyDayNight()
            recreate()
            configureStatusBarColor(R.color.blanco_oscuro, true)
            switchDarkMode.setNight(false)
        }

        boton.setOnClickListener {
            //animacionNoche()
        }
        botonDark.setOnClickListener {
            if (botonDark.isChecked) {
                animacionNoche()
            } else {
                animacionDia()
            }
        }
    }

    fun modeDark(mode: String) {
        when (mode) {
            "NIGHT_NO" -> setDefaultNightMode(MODE_NIGHT_NO)
            "NIGHT_YES" -> setDefaultNightMode(MODE_NIGHT_YES)
            "SYSTEM" -> setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    fun animacionNoche() {
        setDefaultNightMode(MODE_NIGHT_YES)

        // Swith animation
        val rotation =
            PropertyValuesHolder.ofFloat(View.ROTATION, 360f, 0f)
        val translationX =
            PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 0f, switch2.width / 2f)

        val valueAnimator =
            ObjectAnimator.ofPropertyValuesHolder(imgDia, rotation, translationX)
        ObjectAnimator.ofArgb(Color.parseColor("#dadada"), Color.parseColor("#353535"))
        valueAnimator.duration = 400
        valueAnimator.start()
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {}
            override fun onAnimationEnd(animator: Animator) {
                imgDia.translationX = switch2.width / 2f
            }
            override fun onAnimationCancel(animator: Animator) {}
            override fun onAnimationRepeat(animator: Animator) {}
        })
        valueAnimator.addUpdateListener { animation ->
            switch2.setBackgroundColor(
                (animation.animatedValue as Float).toInt()
            )
        }
        imgDia.setImageDrawable(getDrawable(R.drawable.night_icon))

    }

    fun animacionDia() {
        setDefaultNightMode(MODE_NIGHT_NO)

        // Swith animation
        val rotation =
            PropertyValuesHolder.ofFloat(View.ROTATION, 0f, 360f)
        val translationX =
            PropertyValuesHolder.ofFloat(View.TRANSLATION_X, switch2.width / 2f, 0f)

        val valueAnimator =
        ObjectAnimator.ofPropertyValuesHolder(imgDia, rotation, translationX)
        ObjectAnimator.ofArgb(Color.parseColor("#353535"), Color.parseColor("#dadada"))
        valueAnimator.duration = 400
        valueAnimator.start()
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {}
            override fun onAnimationEnd(animator: Animator) {
             imgDia.translationX = 0f
            }
            override fun onAnimationCancel(animator: Animator) {}
            override fun onAnimationRepeat(animator: Animator) {}
        })

        valueAnimator.addUpdateListener { animation ->
            switch2.setBackgroundColor(
                (animation.animatedValue as Float).toInt()
            )
        }

        imgDia.setImageDrawable(getDrawable(R.drawable.day_icon))
    }
}
