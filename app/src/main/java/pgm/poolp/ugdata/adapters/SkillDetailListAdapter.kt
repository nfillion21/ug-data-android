package pgm.poolp.ugdata.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pgm.poolp.ugdata.data.Champion
import pgm.poolp.ugdata.databinding.ListItemSkillDetailBinding

class SkillDetailListAdapter : ListAdapter<Champion, RecyclerView.ViewHolder>(ChampionDiffCallback2()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SkillDetailViewHolder(
            ListItemSkillDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val champion = getItem(position)
        (holder as SkillDetailViewHolder).bind(champion)
    }
    class SkillDetailViewHolder(
        private val binding: ListItemSkillDetailBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.champion?.let { _ ->
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

        fun bind(item: Champion) {
            binding.apply {
                champion = item
                executePendingBindings()
            }
        }
    }
}
private class ChampionDiffCallback2 : DiffUtil.ItemCallback<Champion>() {

    override fun areItemsTheSame(oldItem: Champion, newItem: Champion): Boolean {
        return oldItem.championId == newItem.championId
    }

    override fun areContentsTheSame(oldItem: Champion, newItem: Champion): Boolean {
        return oldItem == newItem
    }
}
