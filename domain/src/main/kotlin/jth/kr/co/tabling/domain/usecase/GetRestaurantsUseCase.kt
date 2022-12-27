package jth.kr.co.tabling.domain.usecase

import jth.kr.co.tabling.data.model.RestaurantsDTO
import jth.kr.co.tabling.data.repository.RestaurantsRepository
import jth.kr.co.tabling.domain.mapper.RestaurantMapper
import jth.kr.co.tabling.domain.model.Restaurant
import kotlinx.coroutines.*
import java.lang.Exception

class GetRestaurantsUseCase(
    private val repository: RestaurantsRepository,
) {
    operator fun invoke(
        scope: CoroutineScope,
        onResult: (List<Restaurant>) -> Unit = {},
        onFail: (String) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            try {
                val result : MutableList<Restaurant> = mutableListOf()
                val deferredList: ArrayList<Deferred<RestaurantsDTO>> = arrayListOf()

                deferredList.add(async {
                    repository.getRestaurants()
                })

                deferredList.add(async {
                    repository.getRecentRestaurants()
                })

                deferredList.forEach {
                    it.await().list.forEach {
                        result.add(RestaurantMapper.convertRestaurant(it))
                    }
                }

                onResult(result)
            } catch (e: Exception) {
                e.message?.let {
                    onFail(it)
                } ?: onFail("알수 없는 에러 입니다.")
            }
        }
    }
}