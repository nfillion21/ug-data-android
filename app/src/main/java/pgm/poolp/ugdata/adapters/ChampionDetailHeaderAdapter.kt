package pgm.poolp.ugdata.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pgm.poolp.ugdata.data.Champion
import pgm.poolp.ugdata.databinding.ListItemChampionDetailHeaderBinding

class ChampionDetailHeaderAdapter : ListAdapter<Champion, RecyclerView.ViewHolder>(ChampionDetailDiffCallback2()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ChampionDetailHeaderViewHolder(
            ListItemChampionDetailHeaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val skill = getItem(position)
        (holder as ChampionDetailHeaderViewHolder).bind(skill)
    }

    class ChampionDetailHeaderViewHolder(
        private val binding: ListItemChampionDetailHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Champion) {
            binding.apply {
                champion = item
                executePendingBindings()
            }
        }
    }
}

private class ChampionDetailDiffCallback2 : DiffUtil.ItemCallback<Champion>() {

    override fun areItemsTheSame(oldItem: Champion, newItem: Champion): Boolean {
        return oldItem.championId == newItem.championId
    }

    override fun areContentsTheSame(oldItem: Champion, newItem: Champion): Boolean {
        return oldItem == newItem
    }
}
