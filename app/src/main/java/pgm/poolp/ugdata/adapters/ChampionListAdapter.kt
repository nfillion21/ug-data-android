package pgm.poolp.ugdata.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pgm.poolp.ugdata.data.Champion
import pgm.poolp.ugdata.databinding.ListItemChampionBinding

class ChampionListAdapter : ListAdapter<Champion, RecyclerView.ViewHolder>(ChampionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ChampionViewHolder(
            ListItemChampionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val champion = getItem(position)
        (holder as ChampionViewHolder).bind(champion)
    }
    class ChampionViewHolder(
        private val binding: ListItemChampionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.champion?.let { champion ->
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
private class ChampionDiffCallback : DiffUtil.ItemCallback<Champion>() {

    override fun areItemsTheSame(oldItem: Champion, newItem: Champion): Boolean {
        return oldItem.championId == newItem.championId
    }

    override fun areContentsTheSame(oldItem: Champion, newItem: Champion): Boolean {
        return oldItem == newItem
    }
}
