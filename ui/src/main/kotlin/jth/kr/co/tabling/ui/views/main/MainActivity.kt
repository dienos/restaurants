package jth.kr.co.tabling.ui.views.main

import android.content.Intent
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import jth.kr.co.tabling.domain.model.Page
import jth.kr.co.tabling.domain.model.Restaurant
import jth.kr.co.tabling.ui.R
import jth.kr.co.tabling.ui.databinding.MainActivityBinding
import jth.kr.co.tabling.ui.viewmodels.BaseViewModel
import jth.kr.co.tabling.ui.viewmodels.main.MainViewModel
import jth.kr.co.tabling.ui.views.base.BaseActivity
import jth.kr.co.tabling.ui.views.Const.PUT_EXTRA_IS_FAVORITE
import jth.kr.co.tabling.ui.views.Const.PUT_EXTRA_RESTAURANT
import jth.kr.co.tabling.ui.views.base.ProgressDialog
import jth.kr.co.tabling.ui.views.detail.DetailActivity
import jth.kr.co.tabling.ui.views.favorite.FavoriteRestaurantsFragment
import jth.kr.co.tabling.ui.views.recnet.RecentRestaurantsFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>() {
    private val fragments: MutableList<Fragment> = arrayListOf()

    private val activityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                RESULT_OK -> {
                    val data = result.data?.getSerializableExtra(PUT_EXTRA_RESTAURANT)
                    val isFavorite = result.data?.getBooleanExtra(PUT_EXTRA_IS_FAVORITE, false)

                    data?.let {
                        isFavorite?.let {
                            if (it) {
                                viewModel.insertFavoriteRestaurant(data as Restaurant)
                            } else {
                                viewModel.deleteFavoriteRestaurant(data as Restaurant)
                            }
                        }
                    }
                }
            }
        }

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

            progressDialog = ProgressDialog()

            lifecycleOwner?.lifecycleScope?.launch {
                viewModel?.toastFlow?.collect { msg ->
                    if (msg.isNotEmpty()) {
                        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            lifecycleOwner?.lifecycleScope?.launch {
                viewModel?.uiEventFlow?.collect { msg ->
                    when (msg) {
                        BaseViewModel.UiEvent.START_DETAIL.ui -> {
                            startDetail()
                        }
                    }

                    viewModel?.setDefaultUi()
                }
            }

            binding?.lifecycleOwner?.lifecycleScope?.launch {
                viewModel?.progressFlow?.collect { isShowing ->
                    try {
                        if (isShowing) {
                            progressDialog?.show(supportFragmentManager, "progress")
                        } else {
                            progressDialog?.dismiss()
                        }
                    } catch (e: Exception) {}
                }
            }
        }
    }

    private fun setPager(binding: MainActivityBinding) {
        fragments.add(RestaurantsFragment())
        fragments.add(RecentRestaurantsFragment())
        fragments.add(FavoriteRestaurantsFragment())


        val pagerAdapter = PagerFragmentStateAdapter(fragments, this@MainActivity)
        binding.restaurantPager.offscreenPageLimit = fragments.size
        binding.restaurantPager.adapter = pagerAdapter

        binding.restaurantPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                viewModel.currentPageNumber = position

                when (position) {
                    Page.SAVE.number -> viewModel.getRestaurants(true)
                    Page.RECENT.number -> viewModel.getRecentRestaurants(true)
                    Page.FAVORITE.number -> viewModel.getFavoriteRestaurants(false)
                }
            }
        })

        TabLayoutMediator(binding.tab, binding.restaurantPager) { tab, position ->
            when (position) {
                Page.SAVE.number -> tab.text = getString(R.string.save)
                Page.RECENT.number -> tab.text = getString(R.string.recent_look)
                Page.FAVORITE.number -> tab.text = getString(R.string.favorite)
            }
        }.attach()
    }

    private fun startDetail() {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(PUT_EXTRA_RESTAURANT, viewModel.selectRestaurant)
        activityLauncher.launch(intent)
    }
}