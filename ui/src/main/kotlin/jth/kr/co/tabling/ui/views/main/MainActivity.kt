package jth.kr.co.tabling.ui.views.main

import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import jth.kr.co.tabling.ui.R
import jth.kr.co.tabling.ui.databinding.MainActivityBinding
import jth.kr.co.tabling.ui.viewmodels.main.MainViewModel
import jth.kr.co.tabling.ui.views.base.BaseActivity
import jth.kr.co.tabling.ui.views.favorite.FavoriteRestaurantsFragment
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

    private fun setPager(binding: MainActivityBinding) {
        val fragments: MutableList<Fragment> = arrayListOf()
        fragments.add(RestaurantsFragment())
        fragments.add(RecentRestaurantsFragment())
        fragments.add(FavoriteRestaurantsFragment())

        val pagerAdapter = PagerFragmentStateAdapter(fragments, this@MainActivity)
        binding.restaurantPager.offscreenPageLimit = fragments.size
        binding.restaurantPager.adapter = pagerAdapter

        binding.restaurantPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> viewModel.getRestaurants(true)
                    1 -> viewModel.getRecentRestaurants(true)
                    2 -> viewModel.getFavoriteRestaurants(false)
                }
            }
        })

        TabLayoutMediator(binding.tab, binding.restaurantPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.save)
                1 -> tab.text = getString(R.string.recent_look)
                2 -> tab.text = getString(R.string.favorite)
            }

        }.attach()
    }
}