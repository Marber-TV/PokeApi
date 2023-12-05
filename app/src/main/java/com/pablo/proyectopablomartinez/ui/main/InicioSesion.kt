package com.pablo.proyectopablomartinez.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.pablo.proyectopablomartinez.R
import com.pablo.proyectopablomartinez.databinding.FragmentInicioSesionBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class InicioSesion : Fragment(R.layout.fragment_inicio_sesion) {

    private lateinit var binding: FragmentInicioSesionBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInicioSesionBinding.bind(view)
        binding.apply {
            (requireActivity() as AppCompatActivity)
                .supportActionBar?.title = "Wishlist"

            button.setOnClickListener {
                if (editText.text.toString().isEmpty()) {
                    editText.error = "Introduce un nombre de usuario"
                    return@setOnClickListener
                }
                if (editText2.text.toString().isEmpty()) {
                    editText2.error = "Introduce la contraseña"
                    return@setOnClickListener
                }
                findNavController().navigate(
                    R.id.action_inicioSesion_to_mainFragment,
                    bundleOf(MainFragment.USER to editText.text.toString())
                )
            }
        }
    }
}
