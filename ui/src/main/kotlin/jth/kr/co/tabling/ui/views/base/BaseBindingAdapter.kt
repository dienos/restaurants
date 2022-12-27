package jth.kr.co.tabling.ui.views.base

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@BindingAdapter(value = ["restaurant_image"])
fun setImage(view: ImageView, url: String?) {
    url?.let {
        Glide.with(view.context).load(it).into(view)
    }
}

@BindingAdapter(value = ["restaurant_image"])
fun setImage(view: RelativeLayout, url: String?) {
    url?.let {
        Glide
            .with(view.context).load(url)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    view.background = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }
}