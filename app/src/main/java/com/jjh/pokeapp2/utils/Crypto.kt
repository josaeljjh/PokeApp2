package com.jjh.pokeapp2.utils

import android.util.Base64
import tgio.rncryptor.RNCryptorNative

object Crypto {
    var rncryptor = RNCryptorNative()
    const val DATA_SHEME = "ampoLnBva2VhcHA="

    fun encryptNat(src: String): String {
        return String(rncryptor.encrypt(src, decryptBase64(DATA_SHEME)))
    }

    fun decryptNat(src: String): String {
        return rncryptor.decrypt(src, decryptBase64(DATA_SHEME))
    }
    fun encryptBase64(string: String): String {
        // encode
        val encodeValue = Base64.encode(string.toByteArray(), Base64.DEFAULT)
        return String(encodeValue)
    }

    fun decryptBase64(string: String): String {
        val decodeValue = Base64.decode(string, Base64.DEFAULT)
        return String(decodeValue)
    }
}