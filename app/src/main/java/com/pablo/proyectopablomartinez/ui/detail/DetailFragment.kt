package com.pablo.proyectopablomartinez.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.pablo.proyectopablomartinez.R
import com.pablo.proyectopablomartinez.databinding.FragmentDetailBinding
import com.pablo.proyectopablomartinez.model.Pokemon
import java.util.Locale
class DetailFragment : Fragment(R.layout.fragment_detail) {

    companion object {
        const val POKEMON_KEY = "pokemon"
    }

    private lateinit var binding: FragmentDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        val position = arguments?.getInt(POKEMON_KEY) ?: -1 // Obtener el valor del bundle como Int

        if (position != -1) {
            val poke = PokemonProvider.pokemons[position] // Obtener el Pokemon con el índice proporcionado
            poke.let {
                binding.nombreDetalle.text = it.name.capitalize(Locale.ROOT)
                binding.idDetalle.text = it.id.toString()
                val url =
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${it.id}.png"
                Glide.with(binding.root)
                    .load(url)
                    .into(binding.imagenDetalle)
            }
        }

        binding.atras.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}
