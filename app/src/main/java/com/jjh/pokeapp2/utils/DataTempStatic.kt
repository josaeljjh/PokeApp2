package com.jjh.pokeapp2.utils

import android.Manifest

class DataTempStatic {

    companion object {
        var isSesion = false
        var reloadRegister = false
        var tokenNotification = ""
        var tokenApp = ""
        val URL_PRIVACY_POLICY = "${getUrlBase()}privacy"
        val URL_TERMS_OF_SERVICE = "${getUrlBase()}terms"

        fun getUrlBase(): String {
            var url = Cons.BASE_URL_PROD
            if (Cons.ISTEST) {
                url = Cons.BASE_URL_DEV
            }
            return Crypto.decryptNat(url)
        }
        //permission
        val permissions = arrayOf(
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_SMS,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        //data tour
       /* val titles = arrayOf(
            R.string.tour_slide_1_title,
            R.string.tour_slide_2_title,
            R.string.tour_slide_3_title,
            R.string.tour_slide_4_title
        )
        val texts = arrayOf(
            R.string.tour_slide_1_text,
            R.string.tour_slide_2_text,
            R.string.tour_slide_3_text,
            R.string.tour_slide_4_text
        )

        val backgrounds = arrayOf(
            R.drawable.img_tour_step_1,
            R.drawable.img_tour_step_2,
            R.drawable.img_tour_step_3,
            R.drawable.img_tour_step_4
        )

        fun updateTokenApp() {
            tokenApp = getStringPreference(Cons.TOKEN_APP)
        }*/
    }


}