package com.jjh.pokeapp2.utils

import com.bancohipotecario.app.core.util.BottomDialogFragment

object Cons {

    const val APP_NAME = "PokeApp"
    const val APP_DB_NAME = "$APP_NAME-db"

    /** Development URL*/
    const val BASE_URL_DEV = "AgG2Q5a1QNEZXx2GuYPTfJgp74yD2zASBde6bT9QG71i30147g2BIBwlahwtg3bv9W+qsNRkJsHhK6j+LN48vBbDWM1ZnRFYhF60PsQVGzOwdzEnmToyw5kySvkgTdYkaeI="
    /** Production URL*/
    const val BASE_URL_PROD = "AgG2Q5a1QNEZXx2GuYPTfJgp74yD2zASBde6bT9QG71i30147g2BIBwlahwtg3bv9W+qsNRkJsHhK6j+LN48vBbDWM1ZnRFYhF60PsQVGzOwdzEnmToyw5kySvkgTdYkaeI="
    /**
     * Application Base URL
     */
    const val ISTEST = true

    var night = false
    var IS_NIGHT= ""
    var NIGHT_NO= "NIGHT_NO"
    var NIGHT_YES = "NIGHT_YES"
    var SYSTEM = "SYSTEM"

    var sheetDialog: BottomDialogFragment? = null
}