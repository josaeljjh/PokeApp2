package com.jjh.pokeapp2.views.home.region

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jjh.pokeapp2.R
import com.jjh.pokeapp2.databinding.ItemRegionBinding
import com.jjh.pokeapp2.interfaces.InterfaceGlobal
import com.jjh.pokeapp2.models.*
import com.jjh.pokeapp2.repository.InfoRepository
import com.jjh.pokeapp2.utils.ApiException
import com.jjh.pokeapp2.utils.Cons
import com.jjh.pokeapp2.utils.DynamicBindingAdapter
import com.jjh.pokeapp2.utils.NoInternetException
import com.jjh.pokeapp2.utils.extensions.launchAPIRequest
import com.jjh.pokeapp2.utils.extensions.setSafeOnClickListener
import com.jjh.pokeapp2.views.home.HomeHost

class ViewModelRegion(
    private val repository: InfoRepository
) : ViewModel() {

    val regionResponse = MutableLiveData<RegionResponseModel>()
    val pokemonResponse = MutableLiveData<PokemonResponseModel>()
    private var regions: RegionResponseModel? = null
    private var pokedex: PokedexResponseModel? = null
    private var pokemon: PokemonResponseModel? = null
    private var data: ArrayList<Result>? = null
    //val regiones: LiveData<RegionResponseModel> get() = regionResponse
    private var networkListener: InterfaceGlobal.NetworkListener =
        HomeHost()
    private var main: InterfaceGlobal.mainGlobal =
        HomeHost()
    val pokeHome = R.drawable.pokemon_icon
    val logo_map = R.drawable.map
    private var sufijo:String = "region/"

    fun obtenerRegiones() {
        launchAPIRequest {
            try {
                regions = repository.getRegions(sufijo)
                regionResponse.postValue(regions)
            } catch (e: ApiException) {
                main.dismissLoading()
                networkListener.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                main.dismissLoading()
                networkListener.onFailure(e.message!!)
            }
        }
    }

    fun getAdapterOptions(): DynamicBindingAdapter<Result> {
        data = regions.let { it?.results }
        var adapter: DynamicBindingAdapter<Result>? = null
        try {
            adapter = DynamicBindingAdapter(
                R.layout.item_region,
                data!!,
                fun(vh, view, op, _) {
                    view as ItemRegionBinding
                    view.region = op
                    val url = "https://pokeapi.co/api/v2/region/"
                    val urlRegion = op.url.replace(url, "")

                    vh.itemView.setSafeOnClickListener{
                        //vh.itemView.snackbar(op.nombre)
                        Cons.region = op.name
                        obtenerPokedex(sufijo+urlRegion)
                    }
                })
        } catch (e: Exception) {
        }
        return adapter!!
    }

    private fun obtenerPokedex(sufijo:String) {
        main.showLoading()
        launchAPIRequest {
            try {
                pokedex = repository.getPokedex(sufijo)
                Cons.idRegion = pokedex?.id.toString()
                obtenerPokemon(pokedex?.pokedexes?: ArrayList())
            } catch (e: ApiException) {
                main.dismissLoading()
                networkListener.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                main.dismissLoading()
                networkListener.onFailure(e.message!!)
            }
        }
    }

    private fun obtenerPokemon(pokedexes: List<Pokedexe>) {
        if(pokedexes.isNotEmpty()){
            val url = "https://pokeapi.co/api/v2/"
            val urlPokemon = pokedexes[0].url.replace(url, "")

            launchAPIRequest {
                try {
                    pokemon = repository.getPokemon(urlPokemon)
                    pokemonResponse.postValue(pokemon)
                } catch (e: ApiException) {
                    main.dismissLoading()
                    networkListener.onFailure(e.message!!)
                } catch (e: NoInternetException) {
                    main.dismissLoading()
                    networkListener.onFailure(e.message!!)
                }
            }
        }else{
            pokemonResponse.postValue(null)
        }

    }
}