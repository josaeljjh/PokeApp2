package com.jjh.pokeapp2.views.welcome.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.facebook.login.LoginManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jjh.pokeapp2.R
import com.jjh.pokeapp2.di.repository.FirebaseRepository
import com.jjh.pokeapp2.utils.extensions.configBottom
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var navController: NavController
    private val firebase: FirebaseRepository by inject()
    private var currentFragment: Int = -1
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        config()
    }

    private fun config() {
        navController = findNavController(R.id.nav_host_fragment)
        //configuracion de menu footer
        bottomNavigationBar.setOnNavigationItemSelectedListener(this)
        bottomNavigationBar.configBottom()
      /* NavigationUI.setupWithNavController(
            bottomNavigationBar,
            navController
        )*/
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(currentFragment == item.itemId){ return true }
        when(item.itemId){
            R.id.bottomRegion ->{
                currentFragment = item.itemId
                navController.navigate(R.id.action_mainFragment_to_viewRegion)
                return true
            }
            R.id.bottomEquipo ->{
                currentFragment = item.itemId
                navController.navigate(R.id.action_mainFragment_to_viewEquipo)
                return true
            }
            R.id.bottomCerrar ->{
                currentFragment = -1
                firebase.firebaseAuth.signOut()
                LoginManager.getInstance().logOut()
                navController.navigate(R.id.action_mainFragment_to_viewLogin)
                return true
            }
        }
        return false
    }
}