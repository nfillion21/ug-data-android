package pgm.poolp.ugdata.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import pgm.poolp.ugdata.R
import pgm.poolp.ugdata.adapters.ChampionDetailHeaderAdapter
import pgm.poolp.ugdata.adapters.ChampionDetailListAdapter
import pgm.poolp.ugdata.adapters.SkillListAdapter
import pgm.poolp.ugdata.data.Champion
import pgm.poolp.ugdata.databinding.FragmentChampionDetailBinding
import pgm.poolp.ugdata.ui.ChampionDetailFragment.Callback
import pgm.poolp.ugdata.viewmodels.ChampionDetailViewModel

/**
 * A fragment representing a single Plant detail screen.
 */
@AndroidEntryPoint
class ChampionDetailFragment : Fragment() {

    private val championDetailViewModel: ChampionDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentChampionDetailBinding>(
            inflater,
            R.layout.fragment_champion_detail,
            container,
            false
        ).apply {
            viewModel = championDetailViewModel
            lifecycleOwner = viewLifecycleOwner
            callback = Callback { champion ->
                champion?.let {
                    //hideAppBarFab(fab)
                    //championDetailViewModel.addPlantToGarden()
                    //Snackbar.make(root, R.string.added_plant_to_garden, Snackbar.LENGTH_LONG)
                    //.show()
                }
            }

            val headerAdapter = ChampionDetailHeaderAdapter()
            val adapter = ChampionDetailListAdapter()
            championDetailList.adapter = ConcatAdapter(headerAdapter, adapter)
            championDetailViewModel.championWithSkills.observe(viewLifecycleOwner) { championWithSkills ->
                headerAdapter.submitList(listOf(championWithSkills.champion))
                adapter.submitList(championWithSkills.skills)
            }

            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    fun interface Callback {
        fun add(champion: Champion?)
    }
}
