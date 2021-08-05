package pgm.poolp.ugdata.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pgm.poolp.ugdata.data.Champion
import pgm.poolp.ugdata.data.Skill
import pgm.poolp.ugdata.databinding.ListItemChampionDetailHeaderBinding
import pgm.poolp.ugdata.databinding.ListItemSkillChampionsHeaderBinding

class SkillChampionsHeaderAdapter : ListAdapter<Skill, RecyclerView.ViewHolder>(SkillDetailDiffCallback3()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ChampionDetailHeaderViewHolder(
            ListItemSkillChampionsHeaderBinding.inflate(
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
        private val binding: ListItemSkillChampionsHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Skill) {
            binding.apply {
                skill = item
                executePendingBindings()
            }
        }
    }
}

private class SkillDetailDiffCallback3 : DiffUtil.ItemCallback<Skill>() {

    override fun areItemsTheSame(oldItem: Skill, newItem: Skill): Boolean {
        return oldItem.skillId == newItem.skillId
    }

    override fun areContentsTheSame(oldItem: Skill, newItem: Skill): Boolean {
        return oldItem == newItem
    }
}
