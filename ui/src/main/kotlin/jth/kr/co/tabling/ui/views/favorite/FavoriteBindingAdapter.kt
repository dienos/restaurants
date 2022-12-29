package jth.kr.co.tabling.ui.views.favorite

import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView

@BindingAdapter(value = ["favorite_empty"])
fun setFavoriteEmptyLottie(
    view: LottieAnimationView,
    isPlay: Boolean?,
) {
    isPlay?.let {
        if(it) {
            view.playAnimation()
        }
    }
}
