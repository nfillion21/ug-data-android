package pgm.poolp.ugdata.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pgm.poolp.ugdata.data.Champion
import pgm.poolp.ugdata.data.Skill
import pgm.poolp.ugdata.databinding.ListItemChampionDetailBinding
import pgm.poolp.ugdata.databinding.ListItemSkillChampionsBinding
import pgm.poolp.ugdata.ui.ChampionDetailFragmentDirections

class SkillChampionsListAdapter : ListAdapter<Champion, RecyclerView.ViewHolder>(SkillDetailDiffCallback2()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SkillChampionsViewHolder(
            ListItemSkillChampionsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val champion = getItem(position)
        (holder as SkillChampionsViewHolder).bind(champion)
    }
    class SkillChampionsViewHolder(
        private val binding: ListItemSkillChampionsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Champion) {
            binding.apply {
                champion = item
                executePendingBindings()
            }
        }
    }
}
private class SkillDetailDiffCallback2 : DiffUtil.ItemCallback<Champion>() {

    override fun areItemsTheSame(oldItem: Champion, newItem: Champion): Boolean {
        return oldItem.championId == newItem.championId
    }

    override fun areContentsTheSame(oldItem: Champion, newItem: Champion): Boolean {
        return oldItem == newItem
    }
}
