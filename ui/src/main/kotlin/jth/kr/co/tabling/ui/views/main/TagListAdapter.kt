package jth.kr.co.tabling.ui.views.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import jth.kr.co.tabling.ui.databinding.TagItemBinding

class TagListAdapter :
    ListAdapter<String, TagListAdapter.TagViewHolder>(
        DiffCallback
    ) {

    init {
        setHasStableIds(true)
    }

    inner class TagViewHolder(itemView: View, _bind: TagItemBinding) :
        RecyclerView.ViewHolder(itemView) {
        private val bind = _bind

        fun bind(tag: String) {
            bind.tag = tag
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val bind = TagItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TagViewHolder(bind.root, bind)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
