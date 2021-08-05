package pgm.poolp.ugdata.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import dagger.hilt.android.AndroidEntryPoint
import pgm.poolp.ugdata.R
import pgm.poolp.ugdata.adapters.SkillChampionsHeaderAdapter
import pgm.poolp.ugdata.adapters.SkillChampionsListAdapter
import pgm.poolp.ugdata.databinding.FragmentDialogSkillChampionsDetailBinding
import pgm.poolp.ugdata.viewmodels.SkillDetailViewModel

@AndroidEntryPoint
class SkillDetailDialogFragment : DialogFragment() {

    private val skillDetailViewModel: SkillDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentDialogSkillChampionsDetailBinding>(
            inflater,
            R.layout.fragment_dialog_skill_champions_detail,
            container,
            false
        ).apply {
            viewModel = skillDetailViewModel
            lifecycleOwner = this@SkillDetailDialogFragment

            val headerAdapter = SkillChampionsHeaderAdapter()
            val championsAdapter = SkillChampionsListAdapter()
            skillChampionsDetailList.adapter = ConcatAdapter(headerAdapter, championsAdapter)
            skillDetailViewModel.skillWithChampions.observe(viewLifecycleOwner) { skillWithChampions ->
                headerAdapter.submitList(listOf(skillWithChampions.skill))
                championsAdapter.submitList(skillWithChampions.champions)
            }
        }
        return binding.root
    }
}