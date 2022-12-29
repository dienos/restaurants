package jth.kr.co.tabling.ui.views.main

import androidx.fragment.app.activityViewModels
import jth.kr.co.tabling.ui.R
import jth.kr.co.tabling.ui.databinding.RestaurantPagerItemBinding
import jth.kr.co.tabling.ui.viewmodels.main.MainViewModel
import jth.kr.co.tabling.ui.views.base.BaseFragment

class RestaurantsFragment : BaseFragment<RestaurantPagerItemBinding>() {
    private val _viewModel: MainViewModel by activityViewModels()
    private val viewModel: MainViewModel
        get() = _viewModel

    override fun getLayoutResId(): Int = R.layout.restaurant_pager_item

    override fun initializeViewModel() {
        binding?.viewModel = viewModel
        binding?.viewModel?.getRestaurants(true)
    }
}