package pgm.poolp.ugdata.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pgm.poolp.ugdata.R
import pgm.poolp.ugdata.data.Champion

class ChampionListAdapter : ListAdapter<Champion, ChampionListAdapter.ChampionViewHolder>(CHAMPION_COMPARATOR)
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionViewHolder
    {
        return ChampionViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int)
    {
        val current = getItem(position)
        holder.bind(current.word)
    }

    class ChampionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?)
        {
            wordItemView.text = text
        }

        companion object
        {
            fun create(parent: ViewGroup): ChampionViewHolder
            {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return ChampionViewHolder(view)
            }
        }
    }

    companion object
    {
        private val CHAMPION_COMPARATOR = object : DiffUtil.ItemCallback<Champion>()
        {
            override fun areItemsTheSame(oldItem: Champion, newItem: Champion): Boolean
            {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Champion, newItem: Champion): Boolean
            {
                return oldItem.word == newItem.word
            }
        }
    }
}
