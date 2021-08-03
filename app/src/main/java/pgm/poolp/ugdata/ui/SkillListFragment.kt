package pgm.poolp.ugdata.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pgm.poolp.ugdata.adapters.SkillListAdapter
import pgm.poolp.ugdata.databinding.FragmentSkillsBinding
import pgm.poolp.ugdata.viewmodels.SkillViewModel

@AndroidEntryPoint
class SkillListFragment : Fragment() {

    private val skillViewModel: SkillViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSkillsBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = SkillListAdapter()
        binding.skillsList.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: SkillListAdapter) {
        skillViewModel.allSkills.observe(viewLifecycleOwner) { skills ->
            adapter.submitList(skills)
        }
    }
}
