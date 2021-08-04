package pgm.poolp.ugdata.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pgm.poolp.ugdata.R
import pgm.poolp.ugdata.databinding.FragmentDialogSkillDetailBinding
import pgm.poolp.ugdata.databinding.FragmentSkillDetailBinding
import pgm.poolp.ugdata.viewmodels.SkillDetailViewModel

@AndroidEntryPoint
class SkillDialogFragment : DialogFragment() {

    private val skillDetailViewModel: SkillDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentDialogSkillDetailBinding>(
            inflater,
            R.layout.fragment_dialog_skill_detail,
            container,
            false
        ).apply {
            viewModel = skillDetailViewModel
            lifecycleOwner = this@SkillDialogFragment

            //executePendingBindings()
            /*
            skillDetailViewModel.skill.observe(this@SkillDialogFragment) { skill ->
                adapter.submitList(skillWithChampions.champions)

            }
            */
        }

        return binding.root




/*
        val binding = FragmentSkillDetailBinding.inflate(inflater, container, false)
        context ?: return binding.root
        return binding.root

             */
    }
}