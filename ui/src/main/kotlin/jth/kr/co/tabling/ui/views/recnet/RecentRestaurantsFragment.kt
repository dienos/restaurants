package jth.kr.co.tabling.ui.views.recnet

import androidx.fragment.app.viewModels
import jth.kr.co.tabling.ui.R
import jth.kr.co.tabling.ui.databinding.RecentRestaurantPagerItemBinding
import jth.kr.co.tabling.ui.viewmodels.recent.RecentRestaurantsViewModel
import jth.kr.co.tabling.ui.views.base.BaseFragment

class RecentRestaurantsFragment : BaseFragment<RecentRestaurantPagerItemBinding>() {
    private val _viewModel: RecentRestaurantsViewModel by viewModels()
    private val viewModel: RecentRestaurantsViewModel
        get() = _viewModel

    override fun getLayoutResId(): Int = R.layout.recent_restaurant_pager_item

    override fun initializeViewModel() {
        binding?.viewModel = viewModel
    }

    override fun initializeUi() {

    }
}