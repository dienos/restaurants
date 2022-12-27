package jth.kr.co.tabling.ui.views.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jth.kr.co.tabling.domain.model.Restaurant
import jth.kr.co.tabling.ui.databinding.RestaurantPagerItemBinding

class PagerRecyclerAdapter(private val tabs: List<List<Restaurant>>) : RecyclerView.Adapter<PagerRecyclerAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(itemView: View, _bind: RestaurantPagerItemBinding) : RecyclerView.ViewHolder(itemView) {
        private val bind = _bind

        fun bind(item: List<Restaurant>) {
            bind.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val bind = RestaurantPagerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PagerViewHolder(bind.root, bind)
    }
    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(tabs[position])
    }

    override fun getItemCount(): Int = tabs.size
}
