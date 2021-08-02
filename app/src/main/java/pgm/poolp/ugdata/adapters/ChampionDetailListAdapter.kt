package pgm.poolp.ugdata.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pgm.poolp.ugdata.data.Skill
import pgm.poolp.ugdata.databinding.ListItemChampionBinding
import pgm.poolp.ugdata.databinding.ListItemChampionDetailBinding
import pgm.poolp.ugdata.databinding.ListItemSkillBinding

class ChampionDetailListAdapter : ListAdapter<Skill, RecyclerView.ViewHolder>(SkillDiffCallback2()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ChampionDetailViewHolder(
            ListItemChampionDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val skill = getItem(position)
        (holder as ChampionDetailViewHolder).bind(skill)
    }
    class ChampionDetailViewHolder(
        private val binding: ListItemChampionDetailBinding
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
private class SkillDiffCallback2 : DiffUtil.ItemCallback<Skill>() {

    override fun areItemsTheSame(oldItem: Skill, newItem: Skill): Boolean {
        return oldItem.skillId == newItem.skillId
    }

    override fun areContentsTheSame(oldItem: Skill, newItem: Skill): Boolean {
        return oldItem == newItem
    }
}