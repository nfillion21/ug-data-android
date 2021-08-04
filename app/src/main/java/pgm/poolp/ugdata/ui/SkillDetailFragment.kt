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
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import pgm.poolp.ugdata.R
import pgm.poolp.ugdata.adapters.ChampionDetailListAdapter
import pgm.poolp.ugdata.adapters.SkillDetailListAdapter
import pgm.poolp.ugdata.adapters.SkillListAdapter
import pgm.poolp.ugdata.data.Champion
import pgm.poolp.ugdata.databinding.FragmentChampionDetailBinding
import pgm.poolp.ugdata.databinding.FragmentSkillDetailBinding
import pgm.poolp.ugdata.ui.ChampionDetailFragment.Callback
import pgm.poolp.ugdata.viewmodels.ChampionDetailViewModel
import pgm.poolp.ugdata.viewmodels.SkillDetailViewModel

/**
 * A fragment representing a single Plant detail screen.
 */
@AndroidEntryPoint
class SkillDetailFragment : Fragment() {

    private val skillDetailViewModel: SkillDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentSkillDetailBinding>(
            inflater,
            R.layout.fragment_dialog_skill_detail,
            container,
            false
        ).apply {
            viewModel = skillDetailViewModel
            lifecycleOwner = viewLifecycleOwner

            val adapter = SkillDetailListAdapter()
            skillDetailList.adapter = adapter
            skillDetailViewModel.championWithSkills.observe(viewLifecycleOwner) { skillWithChampions ->
                adapter.submitList(skillWithChampions.champions)
            }

            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }
        }
        setHasOptionsMenu(true)

        return binding.root
    }
}
