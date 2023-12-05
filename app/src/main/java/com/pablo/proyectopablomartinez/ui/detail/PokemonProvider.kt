package com.pablo.proyectopablomartinez.ui.detail

import android.content.Context
import android.widget.Toast
import com.pablo.proyectopablomartinez.model.Pokemon
import com.pablo.proyectopablomartinez.model.server.PokeApiService
import com.pablo.proyectopablomartinez.ui.detail.PokemonProvider.pokemons
import kotlinx.coroutines.delay
import retrofit2.Response
import retrofit2.awaitResponse

object PokemonProvider {

    var pokemons: MutableList<Pokemon> = mutableListOf()
    private var cargado = false
    var contador = 0

    suspend fun getPokemons(): List<Pokemon> {
        val lista = pokemons
        val service = PokeApiService.create()
        if (!cargado) {
            cargado = true
            for (i in 1..150) {
                val response = service.getPokemon(i).awaitResponse()
                if (response.isSuccessful) {
                    val pokemon = response.body()
                    if (pokemon != null) {
                        lista.add(pokemon)
                        contador++
                    }
                }
            }
        }
        return lista
    }


    suspend fun getSetPokemons(limit: Int, contexto: Context): List<Pokemon> {
        val lista = pokemons
        if (limit + contador + 1 > 1017) {
            Toast.makeText(contexto, "No hay más pokemons", Toast.LENGTH_SHORT).show()
        } else {
            val service = PokeApiService.create()
            for (i in contador + 1..limit + contador + 1) {
                val response = service.getPokemon(i).awaitResponse()
                if (response.isSuccessful) {
                    val pokemon = response.body()
                    if (pokemon != null) {
                        lista.add(pokemon)
                        contador++
                    }
                }
            }

        }
        return lista
    }




    suspend fun addLista(id: Int) {
        delay(500)
        val response = getPokemon(id)
        if (response.isSuccessful) {
            val pokemon = response.body()
            if (pokemon != null) {
                pokemons.add(pokemon)
            }
        }
    }


    suspend fun getPokemon(id: Int): Response<Pokemon> {
        if(id>1017){
            Toast.makeText(null, "No hay más pokemons", Toast.LENGTH_SHORT).show()
        }
        val service = PokeApiService.create()
        return service.getPokemon(id).awaitResponse()
    }


    suspend fun deletePokemon(position: Int) {
        delay(1000)
        if (position in pokemons.indices) {
            pokemons.removeAt(position)
        } else {
            // Manejo de errores aquí
        }
    }


    // Método para añadir un Pokémon a la lista
    suspend fun addPokemon(id: Int): Pokemon? {
        val service = PokeApiService.create()
        val response = service.getPokemon(id).awaitResponse()
        if (response.isSuccessful) {
            val pokemon = response.body()
            if (pokemon != null) {
                pokemons.add(pokemon)
                return pokemon
            }
        }
        return response.body()
    }


    fun removePokemon(pokemon: Pokemon, lista: MutableList<Pokemon>) {
        lista.remove(pokemon)
    }

    suspend fun modifyPokemon(id: Int, name: String) {
        delay(1000)
        pokemons.forEach { pokemon ->
            if (pokemon.id == id) {
                pokemon.name = name
            }
        }
    }

    fun getPokemonList(): List<Pokemon> {
        return pokemons
    }
}