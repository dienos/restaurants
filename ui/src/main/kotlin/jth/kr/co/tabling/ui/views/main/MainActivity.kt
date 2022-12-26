package jth.kr.co.tabling.ui.views.main

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jth.kr.co.tabling.ui.R
import jth.kr.co.tabling.ui.databinding.MainActivityBinding
import jth.kr.co.tabling.ui.viewmodels.main.MainViewModel
import jth.kr.co.tabling.ui.views.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>() {
    private val _viewModel: MainViewModel by viewModels()
    private val viewModel: MainViewModel
        get() = _viewModel

    override fun getLayoutResId(): Int = R.layout.main_activity

    override fun initializeViewModel() {
        binding?.viewModel = viewModel
        binding?.viewModel?.getRestaurants()
    }

    override fun initializeUiEvent() {
        binding?.apply {
            setSupportActionBar(toolbar)
        }
    }
}