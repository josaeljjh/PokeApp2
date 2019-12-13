package com.jjh.pokeapp2.views.welcome.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jjh.pokeapp2.R
import com.jjh.pokeapp2.di.repository.FirebaseRepository
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(){

    lateinit var navController: NavController
    private val firebase: FirebaseRepository by inject()
    private val viewModel by viewModel<ViewModelHome>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        config()
        viewModel.obtenerRegiones()
        //onClickListener()
        //observer()
    }

    private fun config() {
        (activity as MainActivity).bottomNavigationBar.visibility = View.VISIBLE
        (activity as MainActivity).bottomNavigationBar.menu.findItem(R.id.bottomRegion).isChecked = true
    }
}
