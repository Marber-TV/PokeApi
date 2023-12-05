import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.pablo.proyectopablomartinez.R
import com.pablo.proyectopablomartinez.databinding.FragmentModifiBinding
import com.pablo.proyectopablomartinez.ui.detail.PokemonProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Modify : Fragment(R.layout.fragment_modifi) {

    private lateinit var binding: FragmentModifiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentModifiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt(POS) ?: -1
        var pokemon = PokemonProvider.pokemons[position]

        binding.nombreDetalle.setText(pokemon.name) // Mostrar el nombre del Pokemon en el EditText
        val url ="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pokemon.id+".png"
        Glide.with(binding.root)
            .load(url)
            .into(binding.imagenDetalle)
        binding.btnSave.setOnClickListener {
            val newName = binding.nombreDetalle.text.toString()

            if (position != -1 && !newName.isNullOrEmpty()) {
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                    pokemon?.let {
                        PokemonProvider.modifyPokemon(it.id, newName)
                        parentFragmentManager.popBackStack()
                    }
                }
            }
        }
    }

    companion object {
        const val POS: String = "pos"
    }
}
