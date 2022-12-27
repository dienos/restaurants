package jth.kr.co.tabling.ui.viewmodels.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import jth.kr.co.tabling.domain.model.LocalSample
import jth.kr.co.tabling.domain.model.Restaurant
import jth.kr.co.tabling.domain.usecase.GetLocalSampleUseCase
import jth.kr.co.tabling.domain.usecase.GetRestaurantsUseCase
import jth.kr.co.tabling.ui.viewmodels.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLocalSampleUseCase: GetLocalSampleUseCase,
    private val getRestaurantsUseCase: GetRestaurantsUseCase
) : BaseViewModel() {

    private var _sampleLocalData = MutableLiveData<List<LocalSample>>()
    val sampleLocalRepository: LiveData<List<LocalSample>> = _sampleLocalData

    private var _restaurantLiveData = MutableLiveData<List<Restaurant>>()
    val restaurantLiveData: LiveData<List<Restaurant>> = _restaurantLiveData

    fun getRestaurants() {
        viewModelScope.launch {
            getRestaurantsUseCase(scope = viewModelScope, { result ->
                _restaurantLiveData.value = result
            }, {
                //todo 에러 처리
            })
        }
    }

    fun getLocalSimpleData() {
        viewModelScope.launch {
            _sampleLocalData.value = getLocalSampleUseCase.invoke()
        }
    }
}