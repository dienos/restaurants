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
import jth.kr.co.tabling.ui.viewmodels.main.MainViewModel


class RestaurantListAdapter(private val context: Context, private val viewModel: MainViewModel) :
    ListAdapter<Restaurant, RestaurantListAdapter.RestaurantViewHolder>(DiffCallback) {

    init {
        setHasStableIds(true)
    }

    inner class RestaurantViewHolder(_itemView: View, _bind: RestaurantItemBinding) :
        RecyclerView.ViewHolder(_itemView) {
        val bind: RestaurantItemBinding = _bind

        fun bind(item: Restaurant) {
            bind.item = item
            setLottie(bind.favoriteBtn, item.isFavorite)
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onViewRecycled(holder: RestaurantViewHolder) {
        holder.bind.favoriteBtn.cancelAnimation()
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

    private fun setLottie(view: View, isFavorite: Boolean?) {
        isFavorite?.let {
            val process = (view as LottieAnimationView).progress

            if (it) {
                if (process == 0f) {
                    val animator = ValueAnimator.ofFloat(0f, 1f).setDuration(500)
                    animator.addUpdateListener { animation: ValueAnimator ->
                        view.progress = animation.animatedValue as Float
                    }
                    animator.start()
                }

            } else {
                if (process == 1f) {
                    val animator = ValueAnimator.ofFloat(1f, 0f).setDuration(500)

                    animator.addUpdateListener { animation: ValueAnimator ->
                        view.progress = animation.animatedValue as Float
                    }
                    animator.start()
                }
            }
        }
    }

    fun onFavoriteClick(view: View, item: Restaurant) {
        setLottie(view, item.isFavorite?.not())

        if (item.isFavorite?.not()!!) {
            viewModel.insertFavoriteRestaurant(item)
        } else {
            viewModel.deleteFavoriteRestaurant(item)
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