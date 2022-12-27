package jth.kr.co.tabling.ui.views.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jth.kr.co.tabling.ui.databinding.TagItemBinding

class TagListAdapter(private val tags : List<String>) : RecyclerView.Adapter<TagListAdapter.TagViewHolder>() {

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
        holder.bind(tags[position])
    }

    override fun getItemCount(): Int = tags.size
}
