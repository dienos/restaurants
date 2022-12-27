package jth.kr.co.tabling.ui.viewmodels.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import jth.kr.co.tabling.domain.model.LocalSample
import jth.kr.co.tabling.domain.model.Restaurant
import jth.kr.co.tabling.domain.usecase.GetLocalSampleUseCase
import jth.kr.co.tabling.domain.usecase.GetRecentRestaurantsUseCae
import jth.kr.co.tabling.domain.usecase.GetRestaurantsUseCase
import jth.kr.co.tabling.ui.viewmodels.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLocalSampleUseCase: GetLocalSampleUseCase,
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val getRecentRestaurantsUseCae: GetRecentRestaurantsUseCae,
) : BaseViewModel() {

    private var _sampleLocalData = MutableLiveData<List<LocalSample>>()
    val sampleLocalRepository: LiveData<List<LocalSample>> = _sampleLocalData

    private var _restaurantLiveData = MutableLiveData<List<Restaurant>>()
    val restaurantLiveData: LiveData<List<Restaurant>> = _restaurantLiveData

    private var _recentRestaurantLiveData = MutableLiveData<List<Restaurant>>()
    val recentRestaurantLiveData: LiveData<List<Restaurant>> = _recentRestaurantLiveData

    fun getRestaurants() {
        viewModelScope.launch {
            getRestaurantsUseCase(scope = viewModelScope, { result ->
                _restaurantLiveData.value = result
            }, {
                //todo 에러 처리
            })
        }
    }

    fun getRecentRestaurants() {
        viewModelScope.launch {
            getRecentRestaurantsUseCae(scope = viewModelScope, { result ->
                _recentRestaurantLiveData.value = result
            }, {
                //todo 에러 처리
            })
        }
    }
}