package jth.kr.co.tabling.ui.views.favorite

import androidx.fragment.app.activityViewModels
import jth.kr.co.tabling.ui.R
import jth.kr.co.tabling.ui.databinding.FavoriteRestaurantPagerItemBinding
import jth.kr.co.tabling.ui.viewmodels.main.MainViewModel
import jth.kr.co.tabling.ui.views.base.BaseFragment

class FavoriteRestaurantsFragment : BaseFragment<FavoriteRestaurantPagerItemBinding>() {
    private val _viewModel: MainViewModel by activityViewModels()
    private val viewModel: MainViewModel
        get() = _viewModel

    override fun getLayoutResId(): Int = R.layout.favorite_restaurant_pager_item

    override fun initializeViewModel() {
        binding?.viewModel = viewModel
        binding?.viewModel?.getFavoriteRestaurants(true)
    }

    override fun initializeUi() {

    }
}