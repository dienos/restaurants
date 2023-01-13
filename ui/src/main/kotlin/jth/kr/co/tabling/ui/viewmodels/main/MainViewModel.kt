package jth.kr.co.tabling.ui.viewmodels.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jth.kr.co.tabling.ui.Page
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

    private val favoriteRestaurantData = MutableLiveData<List<Restaurant>>()
    val favoriteRestaurantLiveData: LiveData<List<Restaurant>>
        get() = favoriteRestaurantData

    private var restaurantData = MutableLiveData<List<Restaurant>>()
    val restaurantLiveData: LiveData<List<Restaurant>>
        get() = restaurantData

    private var recentRestaurantData = MutableLiveData<List<Restaurant>>()
    val recentRestaurantLiveData: LiveData<List<Restaurant>>
        get() = recentRestaurantData

    private var isEmptyFavorite = MutableLiveData<Boolean>()
    val isEmptyFavoriteLiveData: LiveData<Boolean>
        get() = isEmptyFavorite

    var selectRestaurant = Restaurant()

     fun getRestaurants(isRefresh: Boolean) {
        updateProgress(true)

        getRestaurantsUseCase(
            isRefresh = isRefresh,
            localRestaurants = restaurantLiveData.value,
            scope = viewModelScope,
            { result ->
                updateProgress(false)
                restaurantData.postValue(result)
            },
            { msg ->
                updateProgress(false)
                updateToast(msg)
            })
    }

    fun getRecentRestaurants(isRefresh: Boolean) {
        updateProgress(true)

        getRecentRestaurantsUseCae(isRefresh = isRefresh,
            localRecentRestaurants = recentRestaurantLiveData.value,
            scope = viewModelScope,
            { result ->
                updateProgress(false)
                recentRestaurantData.postValue(result)
            },
            { msg ->
                updateProgress(false)
                updateToast(msg)
            })
    }

    fun getFavoriteRestaurants(isRefresh: Boolean) {
        updateProgress(true)

        getFavoriteRestaurantsUseCase(isRefresh = isRefresh,
            localRestaurants = restaurantLiveData.value,
            localRecentRestaurants = recentRestaurantLiveData.value,
            scope = viewModelScope,
            { result ->
                updateProgress(false)
                favoriteRestaurantData.postValue(result)
                isEmptyFavorite.postValue(favoriteRestaurantData.value?.isEmpty())
            },
            { msg ->
                updateProgress(false)
                updateToast(msg)
            })
    }

    fun insertFavoriteRestaurant(item: Restaurant) {
        updateProgress(true)

        insertFavoriteRestaurantUseCase(scope = viewModelScope, item, {
            updateProgress(false)
            getDataByPage()
        }, { msg ->
            updateProgress(false)
            updateToast(msg)
        })
    }

    fun deleteFavoriteRestaurant(item: Restaurant) {
        updateProgress(true)

        item.restaurantIdx?.let {
            deleteFavoriteRestaurantUseCase(it, scope = viewModelScope, {
                updateProgress(false)
                getDataByPage()
            }, { msg ->
                updateProgress(false)
                updateToast(msg)
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

    fun onRestaurantItemClick(item: Restaurant) {
        selectRestaurant = item
        updateUi(UiEvent.START_DETAIL.ui)
    }
}