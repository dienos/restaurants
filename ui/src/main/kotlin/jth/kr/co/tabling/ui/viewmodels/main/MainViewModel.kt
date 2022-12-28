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

    fun getRestaurants(isRefresh: Boolean) {
        getRestaurantsUseCase(
            isRefresh = isRefresh,
            localRestaurants = restaurantLiveData.value,
            scope = viewModelScope,
            { result ->
                _restaurantLiveData.value = result
            },
            {
                //todo 에러 처리
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
                //todo 에러 처리
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
                //todo 에러 처리
            })
    }

    fun insertFavoriteRestaurant(item: Restaurant) {
        insertFavoriteRestaurantUseCase(scope = viewModelScope, item, {
            getDataByPage()
        }, {
            //todo 에러 처리
        })
    }

    fun deleteFavoriteRestaurant(item: Restaurant) {
        item.restaurantIdx?.let {
            deleteFavoriteRestaurantUseCase(it, scope = viewModelScope,  {
                getDataByPage()
            }, {
                //todo 에러 처리
            })
        }
    }

    private fun getDataByPage() {
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
}