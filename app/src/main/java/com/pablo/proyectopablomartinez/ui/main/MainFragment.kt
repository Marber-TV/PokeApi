import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.pablo.proyectopablomartinez.R
import com.pablo.proyectopablomartinez.databinding.FragmentMainBinding
import com.pablo.proyectopablomartinez.ui.detail.DetailFragment
import com.pablo.proyectopablomartinez.ui.detail.PokemonProvider
import com.pablo.proyectopablomartinez.ui.main.PokemonAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment(R.layout.fragment_main) {

    companion object {
        const val USER = "username"
    }

    private lateinit var binding: FragmentMainBinding
    private var adapter = PokemonAdapter(emptyList(), this) { position ->
        findNavController().navigate(
            R.id.action_mainFragment_to_detailFragment
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view).apply {

            (requireActivity() as AppCompatActivity)
                .supportActionBar?.title = "Bienvenido/a, " + arguments?.getString(USER)

            recycler.adapter = adapter
            loadProducts()

            floating.setOnClickListener {
                binding.recycler.visibility = View.GONE
                binding.progress.visibility = View.VISIBLE
                findNavController().navigate(
                    R.id.action_mainFragment_to_buscar
                )
                adapter.notifyItemInserted(adapter.itemCount)
            }
        }
    }

    private fun loadProducts() {

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            binding.progress.visibility = View.VISIBLE
            val pokemons = withContext(Dispatchers.IO) { PokemonProvider.getPokemons() }
            adapter.pokemons = pokemons
            adapter.notifyDataSetChanged()
            binding.progress.visibility = View.GONE
        }

    }

    fun onDelete(position: Int) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            binding.recycler.visibility = View.GONE
            binding.progress.visibility = View.VISIBLE
            withContext(Dispatchers.IO) { PokemonProvider.deletePokemon(position) }
            adapter.notifyItemRemoved(position)
            binding.progress.visibility = View.GONE
            binding.recycler.visibility = View.VISIBLE
            adapter.notifyDataSetChanged()
            Toast.makeText(context, "Producto borrado", Toast.LENGTH_SHORT).show()
        }

    }

    fun onModify(position: Int) {
        findNavController().navigate(
            R.id.action_mainFragment_to_modify,
            bundleOf(Modify.POS to position)
        )
    }

    fun onDetail(position: Int) {
        findNavController().navigate(
            R.id.action_mainFragment_to_detailFragment,
            bundleOf(DetailFragment.POKEMON_KEY to position)
        )
    }

}