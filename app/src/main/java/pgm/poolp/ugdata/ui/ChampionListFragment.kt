package pgm.poolp.ugdata.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import pgm.poolp.ugdata.adapters.ChampionListAdapter
import pgm.poolp.ugdata.databinding.FragmentChampionsBinding

class ChampionListFragment : Fragment() {

    //private val viewModel: PlantListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentChampionsBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = ChampionListAdapter()
        binding.championsRecyclerview.adapter = adapter
        //subscribeUi(adapter)

        //setHasOptionsMenu(true)
        return binding.root
    }

    /*
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_plant_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_zone -> {
                updateData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi(adapter: PlantAdapter) {
        viewModel.plants.observe(viewLifecycleOwner) { plants ->
            adapter.submitList(plants)
        }
    }

    private fun updateData() {
        with(viewModel) {
            if (isFiltered()) {
                clearGrowZoneNumber()
            } else {
                setGrowZoneNumber(9)
            }
        }
    }
    */
}
