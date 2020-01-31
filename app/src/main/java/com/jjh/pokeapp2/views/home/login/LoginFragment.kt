package com.jjh.pokeapp2.views.home.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.jjh.pokeapp2.R
import com.jjh.pokeapp2.interfaces.InterfaceGlobal
import com.jjh.pokeapp2.repository.FirebaseRepository
import com.jjh.pokeapp2.utils.Cons
import com.jjh.pokeapp2.utils.extensions.*
import com.jjh.pokeapp2.views.home.HomeHost
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(){

    lateinit var navController: NavController
    private lateinit var googleApiClient: GoogleSignInClient
    val GOOGLE_LOG_IN_RC = 1
    private var callbackManager: CallbackManager? = null
    private val viewModel by viewModel<ViewModelLogin>()
    private val firebase: FirebaseRepository by inject()
    private var main: InterfaceGlobal.mainGlobal = HomeHost()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        config()
        onClickListener()
        observer()
    }

    private fun config() {
        Cons.activity = activity
        switchDark.isChecked = activity?.getBooleanPreference(Cons.IS_NIGHT) as Boolean
        main.hideBottomNavigation()
        logoPoke.setBackground(R.drawable.pokemon_icon)
        logoUser.setBackground(R.drawable.player)
        callbackManager = CallbackManager.Factory.create()
        configureGoogle()
        sesion()

       /* DataBindingUtil.setContentView<FragmentLoginBinding>(
            activity!!,
            R.layout.fragment_login
        ).apply {
            lifecycleOwner = this@LoginFragment
            viewmodel = viewModel
        }*/
    }

    private fun onClickListener() {
        btnFacebookCust.setOnClickListener {

        }
        btnGoogle.setOnClickListener {
            main.showLoading()
            startActivityResult()
        }
        switchDark.setOnClickListener {
            if (switchDark.isChecked) {
                modeDark(Cons.NIGHT_NO)
            } else {
                modeDark(Cons.NIGHT_YES)
            }
        }
    }

    private fun modeDark(mode: String) {
        when (mode) {
            Cons.NIGHT_NO -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                activity?.setPreference(Cons.IS_NIGHT, true)
            }
            Cons.NIGHT_YES -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                activity?.setPreference(Cons.IS_NIGHT, false)
            }
            Cons.SYSTEM -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }

    //configuracion btn google
    private fun configureGoogle() {
        // Configure Google Sign In
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleApiClient = GoogleSignIn.getClient(activity!!, googleSignInOptions)
    }

    private fun startActivityResult() {
        val signInIntent = googleApiClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_LOG_IN_RC)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Login Facebook
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == GOOGLE_LOG_IN_RC) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                // Google Sign In was successful, authenticate with Firebase
                viewModel.firebaseGoogle(result.signInAccount!!)
            } else {
                main.dismissLoading()
                btnGoogle.snackbarConexion("Comprueba tu conexión.")
            }
        }
    }

    private fun observer() {
        viewModel.resultGoogle.observe(viewLifecycleOwner, Observer { isSuccess ->
            isSuccess?.let {
                if (isSuccess) {
                    /*val user = firebase.firebaseAuth.currentUser
                    println("User account ID ${user?.uid}")
                    println("Display Name : ${user?.displayName}")
                    println("Email : ${user?.email}")
                    println("Photo URL : ${user?.photoUrl}")
                    println("Provider ID : ${user?.providerId}")*/
                    navController.navigate(R.id.action_mainFragment_to_viewHomeFragment)
                } else {
                    btnGoogle.snackbar("Error.")
                }
            }
        })
    }

    //verificacion sesion
    private fun sesion() {
        val user = firebase.firebaseAuth.currentUser
        user?.getIdToken(true)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val idToken = task.result!!.token
                if (idToken != null) {
                    navController.navigate(R.id.action_mainFragment_to_viewHomeFragment)
                }
            } else {
                btnGoogle.snackbarConexion("Comprueba tu conexión.")
            }
        }
    }

    override fun onDestroy() {
        main.dismissLoading()
        super.onDestroy()
    }

    override fun onDestroyView() {
        main.dismissLoading()
        super.onDestroyView()
    }
}