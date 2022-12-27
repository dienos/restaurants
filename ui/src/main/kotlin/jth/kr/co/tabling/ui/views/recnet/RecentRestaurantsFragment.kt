package jth.kr.co.tabling.ui.views.recnet

import androidx.fragment.app.activityViewModels
import jth.kr.co.tabling.ui.R
import jth.kr.co.tabling.ui.databinding.RecentRestaurantPagerItemBinding
import jth.kr.co.tabling.ui.viewmodels.main.MainViewModel
import jth.kr.co.tabling.ui.views.base.BaseFragment

class RecentRestaurantsFragment : BaseFragment<RecentRestaurantPagerItemBinding>() {
    private val _viewModel: MainViewModel by activityViewModels()
    private val viewModel: MainViewModel
        get() = _viewModel

    override fun getLayoutResId(): Int = R.layout.recent_restaurant_pager_item

    override fun initializeViewModel() {
        binding?.viewModel = viewModel
    }

    override fun initializeUi() {

    }
}