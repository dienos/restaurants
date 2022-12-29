package jth.kr.co.tabling.ui.viewmodels.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import jth.kr.co.tabling.domain.model.Restaurant
import jth.kr.co.tabling.ui.viewmodels.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : BaseViewModel() {
    val restaurantData = MutableLiveData<Restaurant>()
    val restaurantLiveData: LiveData<Restaurant>
        get() = restaurantData

    var isFavorite = false

    fun onFavoriteClick(item: Restaurant) {
        updateProgress(true)

        item.isFavorite?.let {
            isFavorite = it.not()
            updateUi(UiEvent.SET_FAVORITE.ui)
        }

        updateProgress(false)
    }
}