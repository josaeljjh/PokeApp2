package com.jjh.pokeapp2.views.home.region

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jjh.pokeapp2.R
import com.jjh.pokeapp2.interfaces.InterfaceGlobal
import com.jjh.pokeapp2.models.Result
import com.jjh.pokeapp2.repository.FirebaseRepository
import com.jjh.pokeapp2.utils.DynamicBindingAdapter
import com.jjh.pokeapp2.utils.extensions.configureRecyclerBinding
import com.jjh.pokeapp2.utils.extensions.snackbar
import com.jjh.pokeapp2.views.home.HomeHost
import kotlinx.android.synthetic.main.fragment_region.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegionFragment : Fragment() {

    lateinit var navController: NavController
    private val firebase: FirebaseRepository by inject()
    private val viewModel by viewModel<ViewModelRegion>()
    private var optionsAdapter: DynamicBindingAdapter<Result>? = null
    private var regions: ArrayList<Result>? = null
    private var main: InterfaceGlobal.mainGlobal =
        HomeHost()
    private var controller: LayoutAnimationController? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_region, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        config()
        observerRegion()
        observerPokemon()
    }

    private fun config() {
        main.showBottomNavigation()
        controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        viewModel.obtenerRegiones()
    }

    private fun observerRegion() {
        viewModel.regionResponse.observe(viewLifecycleOwner, Observer { region ->
            if (region.results.isNotEmpty()) {
                updateListOptions()
            } else {
                headerHome.snackbar("Error al consultar regiones.")
            }
        })
    }

    private fun updateListOptions() {
        optionsAdapter = viewModel.getAdapterOptions()
        optionsAdapter?.let { dynamic ->
            listRegion?.configureRecyclerBinding(dynamic)
            listRegion.setHasFixedSize(false)
            listRegion.layoutAnimation = controller
            listRegion.adapter?.notifyDataSetChanged()
            listRegion.scheduleLayoutAnimation()
            dynamic.notifyDataSetChanged()
        }
    }

    private fun observerPokemon() {
        viewModel.pokemonResponse.observe(viewLifecycleOwner, Observer { pokemon ->
            main.dismissLoading()
            if(pokemon != null){
                if (pokemon.pokemon_entries.isNotEmpty()) {
                    val bundle = bundleOf("datos" to pokemon)
                    navController.navigate(R.id.action_mainFragment_to_viewSelect,bundle)
                } else {
                    headerHome.snackbar("Error al consultar pokemon.")
                }
            }else {
                headerHome.snackbar("Error al consultar pokemon.")
            }

        })
    }
}
