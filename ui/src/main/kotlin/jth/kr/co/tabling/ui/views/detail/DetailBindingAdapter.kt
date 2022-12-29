package jth.kr.co.tabling.ui.views.detail

import android.animation.ValueAnimator
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView

@BindingAdapter(value = ["rating"])
fun setStartsLottie(
    view: LottieAnimationView,
    rate: String?,
) {
    rate?.let {
        val endValue : Float = it.toFloat().div(10).times(2)
        val animator = ValueAnimator.ofFloat(0f, endValue).setDuration(1000)

        animator.addUpdateListener { animation: ValueAnimator ->
            view.progress = animation.animatedValue as Float
        }
        animator.start()
    }
}
