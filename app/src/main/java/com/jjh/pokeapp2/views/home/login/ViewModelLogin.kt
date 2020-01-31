package com.jjh.pokeapp2.views.home.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.GoogleAuthProvider
import com.jjh.pokeapp2.repository.FirebaseRepository
import com.jjh.pokeapp2.interfaces.InterfaceGlobal
import com.jjh.pokeapp2.utils.extensions.launchAPIRequest
import com.jjh.pokeapp2.views.home.HomeHost

class ViewModelLogin(
    private val firebase: FirebaseRepository
) : ViewModel() {

    val resultGoogle = MutableLiveData<Boolean>()
    //val showLoading = MutableLiveData<Boolean>()
    private var main: InterfaceGlobal.mainGlobal =
        HomeHost()

    fun firebaseGoogle(signInAccount: GoogleSignInAccount) {
        launchAPIRequest {
            val authCredential = GoogleAuthProvider.getCredential(signInAccount.idToken, null)
            firebase.firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        resultGoogle.postValue(task.isSuccessful)
                    } else {
                        resultGoogle.postValue(task.isCanceled)
                    }
                }
            main.dismissLoading()
        }
    }
}