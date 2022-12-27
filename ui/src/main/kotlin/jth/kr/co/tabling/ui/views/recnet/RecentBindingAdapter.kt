package jth.kr.co.tabling.ui.views.recnet

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jth.kr.co.tabling.domain.model.Restaurant
import jth.kr.co.tabling.ui.viewmodels.main.MainViewModel
import jth.kr.co.tabling.ui.views.main.RestaurantListAdapter

@BindingAdapter(value = ["viewModel","recent_restaurants"])
fun setRecentRestaurants(
    view: RecyclerView,
    viewModel : MainViewModel,
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
