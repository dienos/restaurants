package jth.kr.co.tabling.ui.views.main

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import jth.kr.co.tabling.ui.R
import jth.kr.co.tabling.ui.databinding.MainActivityBinding
import jth.kr.co.tabling.ui.viewmodels.main.MainViewModel
import jth.kr.co.tabling.ui.views.base.BaseActivity
import jth.kr.co.tabling.ui.views.recnet.RecentRestaurantsFragment

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>() {
    private val _viewModel: MainViewModel by viewModels()
    private val viewModel: MainViewModel
        get() = _viewModel

    override fun getLayoutResId(): Int = R.layout.main_activity

    override fun initializeViewModel() {
        binding?.viewModel = viewModel
    }

    override fun initializeUiEvent() {
        binding?.apply {
            setSupportActionBar(toolbar)
            setPager(this)
        }
    }

    private fun setPager(binding : MainActivityBinding) {
        val fragments : MutableList<Fragment> = arrayListOf()
        fragments.add(RestaurantsFragment())
        fragments.add(RecentRestaurantsFragment())

        val pagerAdapter = PagerFragmentStateAdapter(fragments, this@MainActivity)
        binding.restaurantPager.adapter = pagerAdapter

        binding.restaurantPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })

        TabLayoutMediator(binding.tab, binding.restaurantPager) { tab, position ->
            tab.text = "Tab ${position+1}"
        }.attach()
    }
}