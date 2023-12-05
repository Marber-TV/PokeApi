package com.pablo.proyectopablomartinez.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.pablo.proyectopablomartinez.ui.detail.PokemonProvider
import com.pablo.proyectopablomartinez.R
import com.pablo.proyectopablomartinez.databinding.FragmentBuscarBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Buscar : Fragment(R.layout.fragment_buscar) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentBuscarBinding.bind(view).apply {
            buPoke.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                    val a =PokemonProvider.addPokemon(idPoke.text.toString().toInt())
                    if (a!=null){
                        Toast.makeText(context, "Pokemon añadido", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context, "Pokemon no encontrado", Toast.LENGTH_SHORT).show()
                    }
                    parentFragmentManager.popBackStack()

                }
            }
        }
    }
}