package pgm.poolp.ugdata.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pgm.poolp.ugdata.adapters.ChampionListAdapter
import pgm.poolp.ugdata.databinding.FragmentChampionsBinding
import pgm.poolp.ugdata.viewmodels.ChampionViewModel

@AndroidEntryPoint
class ChampionListFragment : Fragment() {

    private val championViewModel: ChampionViewModel by viewModels()
    /*
    private val championViewModel: ChampionViewModel by viewModels {
        ChampionViewModelFactory((activity?.application as MainApplication).repository)
    }
    */

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentChampionsBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = ChampionListAdapter()
        binding.championsRecyclerview.adapter = adapter
        subscribeUi(adapter)

        //setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter: ChampionListAdapter) {
        championViewModel.allChampions.observe(viewLifecycleOwner) { plants ->
            adapter.submitList(plants)
        }
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
