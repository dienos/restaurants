package jth.kr.co.tabling.ui.views.detail

import android.animation.ValueAnimator
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import jth.kr.co.tabling.domain.model.Restaurant
import jth.kr.co.tabling.ui.R
import jth.kr.co.tabling.ui.databinding.DetailActivityBinding
import jth.kr.co.tabling.ui.viewmodels.BaseViewModel
import jth.kr.co.tabling.ui.viewmodels.detail.DetailViewModel
import jth.kr.co.tabling.ui.views.base.BaseActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : BaseActivity<DetailActivityBinding>() {
    private val _viewModel: DetailViewModel by viewModels()
    private val viewModel: DetailViewModel
        get() = _viewModel

    override fun getLayoutResId(): Int = R.layout.detail_activity

    override fun initializeViewModel() {
        binding?.viewModel = viewModel

        binding?.viewModel?._restaurantLiveData?.value =
            intent.getSerializableExtra(PUT_EXTRA_RESTAURANT) as Restaurant
    }

    override fun initializeUiEvent() {
        binding?.apply {
            val data = binding?.viewModel?._restaurantLiveData?.value

            setSupportActionBar(detailToolbar)
            supportActionBar?.title = data?.restaurantName
            supportActionBar?.subtitle = data?.classification

            data?.isFavorite?.let {
                viewModel?.isFavorite = it
                setLottie()
            }

            lifecycleOwner?.lifecycleScope?.launch {
                viewModel?.uiEvent?.collect { msg ->
                    when (msg) {
                        BaseViewModel.UiEvent.SET_FAVORITE.ui -> {
                            setLottie()
                        }
                    }

                    viewModel?.setDefaultUi()
                }
            }
        }
    }

    private fun setLottie() {
        if (viewModel.isFavorite) {
            val animator = ValueAnimator.ofFloat(0f, 1f).setDuration(500)
            animator.addUpdateListener { animation: ValueAnimator ->
                binding?.favoriteBtn?.progress = animation.animatedValue as Float
            }
            animator.start()

        } else {
            val animator = ValueAnimator.ofFloat(1f, 0f).setDuration(500)

            animator.addUpdateListener { animation: ValueAnimator ->
                binding?.favoriteBtn?.progress = animation.animatedValue as Float
            }
            animator.start()
        }
    }

    override fun onBackPressed() {
        intent.putExtra(PUT_EXTRA_IS_FAVORITE, viewModel.isFavorite)
        intent.putExtra(PUT_EXTRA_RESTAURANT, binding?.viewModel?._restaurantLiveData?.value)
        setResult(RESULT_OK, intent)
        super.onBackPressed()
    }
}