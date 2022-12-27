package jth.kr.co.tabling.ui.views.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jth.kr.co.tabling.domain.model.Restaurant
import jth.kr.co.tabling.ui.viewmodels.main.MainViewModel

@BindingAdapter(value = ["viewModel", "restaurants"])
fun setRestaurants(
    view: RecyclerView,
    viewModel: MainViewModel,
    currentList: List<Restaurant>?
) {
    currentList?.let {
        view.adapter?.apply {
            val adapter = view.adapter as RestaurantListAdapter
            adapter.submitList(it)
        } ?: run {
            val layoutManager =
                LinearLayoutManager(
                    view.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            view.layoutManager = layoutManager
            view.adapter =
                RestaurantListAdapter(
                    view.context,
                    viewModel
                )

            (view.adapter as RestaurantListAdapter).submitList(currentList)
        }
    }
}

@BindingAdapter(value = ["tags"])
fun setTags(
    view: RecyclerView,
    currentList: List<String>?
) {
    currentList?.let {
        val layoutManager = LinearLayoutManager(
            view.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        view.layoutManager = layoutManager
        view.adapter = TagListAdapter(currentList)
    }
}