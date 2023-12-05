package com.pablo.proyectopablomartinez.ui.main

import MainFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pablo.proyectopablomartinez.R
import com.pablo.proyectopablomartinez.databinding.ViewPokemonBinding
import com.pablo.proyectopablomartinez.model.Pokemon
import java.util.Locale

class PokemonAdapter(
    var pokemons: List<Pokemon>,
    private val listener: MainFragment,
    param: (Any) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagen: ImageView = view.findViewById(R.id.imagen)
        val botonBorrar: Button = view.findViewById(R.id.btnBorrar)
        val botonEditar: Button = view.findViewById(R.id.btnEditar)
        val binding = ViewPokemonBinding.bind(view)

        fun bind(pokemon: Pokemon) {
            binding.nombre.text =
                pokemon.name.uppercase(Locale.getDefault()) + " #" + pokemon.id.toString()
            val url =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png"
            Glide.with(binding.root)
                .load(url)
                .into(binding.imagen)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pokemons[position])
        holder.botonBorrar.setOnClickListener {
            listener.onDelete(position)
        }
        holder.botonEditar.setOnClickListener {
            listener.onModify(position)
        }
        holder.imagen.setOnClickListener {
            listener.onDetail(position)
        }
    }

    override fun getItemCount() = pokemons.size
}


