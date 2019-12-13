package com.jjh.pokeapp2.utils.extensions

import android.content.Context
import android.content.SharedPreferences
import com.jjh.pokeapp2.utils.Cons

/**
 * Shared Preferences Handling
 */
fun Context.sharedPreferencesInstance(): SharedPreferences {
    return getSharedPreferences(Cons.APP_NAME, Context.MODE_PRIVATE)
}

fun Context.setPreference(key: String, value: Any) {
    val editor = sharedPreferencesInstance().edit()

    when (value) {
        is String -> editor.putString(key, value)
        is Int -> editor.putInt(key, value)
        is Boolean -> editor.putBoolean(key, value)
        is Float -> editor.putFloat(key, value)
        is Long -> editor.putLong(key, value)
        else -> editor.putString(key, value.toString())
    }

    editor.apply()
    //log("setPreference | $key = $value")
}

fun Context.getStringPreference(key: String): String {
    return sharedPreferencesInstance().getString(key, "")!!
}

fun Context.getIntegerPreference(key: String): Int {
    return sharedPreferencesInstance().getInt(key, 0)
}

fun Context.getBooleanPreference(key: String): Boolean {
    return sharedPreferencesInstance().getBoolean(key, false)
}

fun Context.getFloatPreference(key: String): Float {
    return sharedPreferencesInstance().getFloat(key, 0.0f)
}

fun Context.getLongPreference(key: String): Long {
    return sharedPreferencesInstance().getLong(key, 0L)
}

fun Context.clearPreferences() {
    val editor = sharedPreferencesInstance().edit()
    editor.clear()
    editor.apply()
    //log("Preferences cleared.")
}