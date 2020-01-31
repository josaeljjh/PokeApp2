package com.jjh.pokeapp2.views.home.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.jjh.pokeapp2.R
import com.jjh.pokeapp2.models.Result
import com.jjh.pokeapp2.repository.FirebaseRepository
import com.jjh.pokeapp2.interfaces.InterfaceGlobal
import com.jjh.pokeapp2.utils.DynamicBindingAdapter
import com.jjh.pokeapp2.utils.extensions.configureRecyclerBinding
import com.jjh.pokeapp2.utils.extensions.snackbar
import com.jjh.pokeapp2.views.home.HomeHost
import com.jjh.pokeapp2.views.home.region.ViewModelRegion
import kotlinx.android.synthetic.main.fragment_team.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class TeamFragment : Fragment() {
    lateinit var navController: NavController
    private val firebase: FirebaseRepository by inject()
    private val viewModel by viewModel<ViewModelRegion>()
    private var optionsAdapter: DynamicBindingAdapter<Result>? = null
    private var regions: ArrayList<Result>? = null
    private var main: InterfaceGlobal.mainGlobal =
        HomeHost()
    private var controller: LayoutAnimationController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        config()
        observerRegion()
    }


    private fun config() {
        controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        viewModel.obtenerRegiones()
    }

    private fun observerRegion() {
        viewModel.regionResponse.observe(viewLifecycleOwner, Observer { region ->
            if (region.results.isNotEmpty()) {
                val regiones = Result(url = "",name = "prueba")
                region.results.add(regiones)
                region.results.add(regiones)
                region.results.add(regiones)
                region.results.add(regiones)
                region.results.add(regiones)
                region.results.add(regiones)
                updateListOptions()
            } else {
                header.snackbar("Error al consultar regiones.")
            }
        })
    }

    private fun updateListOptions() {
        optionsAdapter = viewModel.getAdapterOptions()
        optionsAdapter?.let { dynamic ->
            listEquipos?.configureRecyclerBinding(dynamic)
            listEquipos.setHasFixedSize(false)
            listEquipos.layoutAnimation = controller
            listEquipos.adapter?.notifyDataSetChanged()
            listEquipos.scheduleLayoutAnimation()
            dynamic.notifyDataSetChanged()

        }
    }
}
