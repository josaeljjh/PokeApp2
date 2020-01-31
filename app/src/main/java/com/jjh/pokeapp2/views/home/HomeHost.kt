package com.jjh.pokeapp2.views.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.facebook.login.LoginManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jjh.pokeapp2.R
import com.jjh.pokeapp2.interfaces.InterfaceGlobal
import com.jjh.pokeapp2.repository.FirebaseRepository
import com.jjh.pokeapp2.utils.Cons
import com.jjh.pokeapp2.utils.Cons.sheetDialog
import com.jjh.pokeapp2.utils.extensions.*
import kotlinx.android.synthetic.main.activity_home_host.*
import org.koin.android.ext.android.inject


class HomeHost : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,InterfaceGlobal.mainGlobal,
    InterfaceGlobal.NetworkListener {

    lateinit var navController: NavController
    private val firebase: FirebaseRepository by inject()
    private var currentFragment: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_host)
        config()
    }

    private fun config() {
        setTransparentStatusBar()
        master.marginUpdate()
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        //configuracion de menu footer
        bottomNavigationBar.setOnNavigationItemSelectedListener(this)
        bottomNavigationBar.configBottom()
        /* NavigationUI.setupWithNavController(
              bottomNavigationBar,
              navController
          )*/
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (currentFragment == item.itemId) {
            return true
        }
        when (item.itemId) {
            R.id.bottomRegion -> {
                currentFragment = item.itemId
                navController.navigate(R.id.action_mainFragment_to_viewRegion)
                return true
            }
            R.id.bottomEquipo -> {
                currentFragment = item.itemId
                navController.navigate(R.id.action_mainFragment_to_viewEquipo)
                return true
            }
            R.id.bottomCerrar -> {
                currentFragment = -1
                firebase.firebaseAuth.signOut()
                LoginManager.getInstance().logOut()
                navController.navigate(R.id.action_mainFragment_to_viewLogin)
                return true
            }
        }
        return false
    }

    override fun showLoading() {
        sheetDialog = Cons.activity?.loading()
    }

    override fun dismissLoading() {
        sheetDialog.let { it?.dismiss() }
    }

    override fun showBottomNavigation() {
        Cons.activity?.bottomNavigationBar?.makeVisible()
        Cons.activity?.bottomNavigationBar?.menu?.findItem(R.id.bottomRegion)?.isChecked = true
    }

    override fun hideBottomNavigation() {
        Cons.activity?.bottomNavigationBar?.makeGone()
    }

    override fun onDestroy() {
        super.onDestroy()
        sheetDialog.let { it?.dismiss() }
    }

    override fun onSuccess(success: Boolean) {
    }

    override fun onFailure(message: String) {
        launch {
            Cons.activity?.master.snackbarConexion(message)
        }
    }

    override fun onError(message: String) {
        launch {
            Cons.activity?.master.snackbar(message)
        }
    }
}