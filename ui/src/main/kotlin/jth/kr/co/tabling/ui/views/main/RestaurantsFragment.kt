package jth.kr.co.tabling.ui.views.main

import androidx.fragment.app.viewModels
import jth.kr.co.tabling.ui.R
import jth.kr.co.tabling.ui.databinding.RestaurantPagerItemBinding
import jth.kr.co.tabling.ui.viewmodels.main.MainRestaurantsViewModel
import jth.kr.co.tabling.ui.views.base.BaseFragment

class RestaurantsFragment : BaseFragment<RestaurantPagerItemBinding>() {
    private val _viewModel: MainRestaurantsViewModel by viewModels()
    private val viewModel: MainRestaurantsViewModel
        get() = _viewModel

    override fun getLayoutResId(): Int = R.layout.restaurant_pager_item

    override fun initializeViewModel() {
        binding?.restaurantViewModel = viewModel
    }

    override fun initializeUi() {

    }
}