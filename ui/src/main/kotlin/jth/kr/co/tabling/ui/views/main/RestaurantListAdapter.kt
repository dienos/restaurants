package jth.kr.co.tabling.ui.views.main

import android.animation.ValueAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import jth.kr.co.tabling.domain.model.Restaurant
import jth.kr.co.tabling.ui.databinding.RestaurantItemBinding

class RestaurantListAdapter(private val context: Context) :
    ListAdapter<Restaurant, RestaurantListAdapter.RestaurantViewHolder>(DiffCallback) {

    inner class RestaurantViewHolder(itemView: View, _bind: RestaurantItemBinding) :
        RecyclerView.ViewHolder(itemView) {
        private val bind: RestaurantItemBinding = _bind

        fun bind(item: Restaurant) {
            bind.item = item
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val bind = RestaurantItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        bind.adapter = this
        return RestaurantViewHolder(bind.root, bind)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun onRestaurantItemClick(item: Restaurant) {

    }

    fun onFavoriteClick(view : View, isFavorite: Boolean) {
        if (!isFavorite) {
            val animator = ValueAnimator.ofFloat(0f, 1f).setDuration(500)
            animator.addUpdateListener { animation: ValueAnimator ->
                (view as LottieAnimationView).progress = animation.animatedValue as Float
            }
            animator.start()
        } else {
            val animator = ValueAnimator.ofFloat(1f, 0f).setDuration(500)
            animator.addUpdateListener { animation: ValueAnimator ->
                (view as LottieAnimationView).progress = animation.animatedValue as Float
            }
            animator.start()
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Restaurant>() {

        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.restaurantIdx == newItem.restaurantIdx
        }
    }
}