package pgm.poolp.ugdata.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pgm.poolp.ugdata.data.Champion
import pgm.poolp.ugdata.data.Skill
import pgm.poolp.ugdata.databinding.ListItemSkillBinding

class SkillListAdapter : ListAdapter<Skill, RecyclerView.ViewHolder>(SkillDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SkillViewHolder(
            ListItemSkillBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val skill = getItem(position)
        (holder as SkillViewHolder).bind(skill)
    }
    class SkillViewHolder(
        private val binding: ListItemSkillBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.skill?.let { _ ->
                    //navigateToPlant(plant, it)
                }
            }
        }

        /*
        private fun navigateToPlant(
            champion: Champion,
            view: View
        ) {
            val direction =
                HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(
                    plant.plantId
                )
            view.findNavController().navigate(direction)
        }
        */

        fun bind(item: Skill) {
            binding.apply {
                skill = item
                executePendingBindings()
            }
        }
    }
}
private class SkillDiffCallback : DiffUtil.ItemCallback<Skill>() {

    override fun areItemsTheSame(oldItem: Skill, newItem: Skill): Boolean {
        return oldItem.skillId == newItem.skillId
    }

    override fun areContentsTheSame(oldItem: Skill, newItem: Skill): Boolean {
        return oldItem == newItem
    }
}
