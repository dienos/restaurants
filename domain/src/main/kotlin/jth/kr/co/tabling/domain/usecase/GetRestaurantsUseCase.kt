package jth.kr.co.tabling.domain.usecase

import jth.kr.co.tabling.data.repository.RestaurantsRepository
import jth.kr.co.tabling.domain.mapper.asRestaurant
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

                val response = async {
                    repository.getRestaurants()
                }

                response.await().list.forEach {
                    result.add(it.asRestaurant())
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