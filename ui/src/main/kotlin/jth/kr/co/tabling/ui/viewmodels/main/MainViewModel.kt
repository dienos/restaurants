package jth.kr.co.tabling.ui.viewmodels.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jth.kr.co.tabling.domain.model.Page
import jth.kr.co.tabling.domain.model.Restaurant
import jth.kr.co.tabling.domain.usecase.*
import jth.kr.co.tabling.ui.viewmodels.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFavoriteRestaurantsUseCase: GetFavoriteRestaurantsUseCase,
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val getRecentRestaurantsUseCae: GetRecentRestaurantsUseCae,
    private val insertFavoriteRestaurantUseCase: InsertFavoriteRestaurantUseCase,
    private val deleteFavoriteRestaurantUseCase: DeleteFavoriteRestaurantUseCase,
) : BaseViewModel() {
    var currentPageNumber = 0

    private var _favoriteRestaurantLiveData = MutableLiveData<List<Restaurant>>()
    val favoriteRestaurantLiveData: LiveData<List<Restaurant>> = _favoriteRestaurantLiveData

    private var _restaurantLiveData = MutableLiveData<List<Restaurant>>()
    val restaurantLiveData: LiveData<List<Restaurant>> = _restaurantLiveData

    private var _recentRestaurantLiveData = MutableLiveData<List<Restaurant>>()
    val recentRestaurantLiveData: LiveData<List<Restaurant>> = _recentRestaurantLiveData
    var selectRestaurant = Restaurant()

    fun getRestaurants(isRefresh: Boolean) {
        getRestaurantsUseCase(
            isRefresh = isRefresh,
            localRestaurants = restaurantLiveData.value,
            scope = viewModelScope,
            { result ->
                _restaurantLiveData.value = result
            },
            {
                updateToast(it)
            })
    }

    fun getRecentRestaurants(isRefresh: Boolean) {
        getRecentRestaurantsUseCae(isRefresh = isRefresh,
            localRecentRestaurants = recentRestaurantLiveData.value,
            scope = viewModelScope,
            { result ->
                _recentRestaurantLiveData.value = result
            },
            {
                updateToast(it)
            })
    }

    fun getFavoriteRestaurants(isRefresh: Boolean) {
        getFavoriteRestaurantsUseCase(isRefresh = isRefresh,
            localRestaurants = restaurantLiveData.value,
            localRecentRestaurants = recentRestaurantLiveData.value,
            scope = viewModelScope,
            { result ->
                _favoriteRestaurantLiveData.value = result
            },
            {
                updateToast(it)
            })
    }

    fun insertFavoriteRestaurant(item: Restaurant) {
        insertFavoriteRestaurantUseCase(scope = viewModelScope, item, {
            getDataByPage()
        }, {
            updateToast(it)
        })
    }

    fun deleteFavoriteRestaurant(item: Restaurant) {
        item.restaurantIdx?.let {
            deleteFavoriteRestaurantUseCase(it, scope = viewModelScope, {
                getDataByPage()
            }, {
                updateToast(it)
            })
        }
    }

    fun getDataByPage() {
        when (currentPageNumber) {
            Page.SAVE.number -> {
                getRestaurants(false)
            }

            Page.RECENT.number -> {
                getRecentRestaurants(false)
            }

            Page.FAVORITE.number -> {
                getFavoriteRestaurants(false)
            }
        }
    }

    fun onRestaurantItemClick(item: Restaurant) {
        selectRestaurant = item
        updateUi(UiEvent.START_DETAIL.ui)
    }
}