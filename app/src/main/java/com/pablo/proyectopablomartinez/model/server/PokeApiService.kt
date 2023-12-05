package com.pablo.proyectopablomartinez.model.server

import com.pablo.proyectopablomartinez.model.Pokemon
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {


    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: Int): Call<Pokemon>

    @GET("pokemon")
    fun getPokemonsList(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokeApiResponse>

    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"

        fun create(): PokeApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokeApiService::class.java)
        }
    }
}