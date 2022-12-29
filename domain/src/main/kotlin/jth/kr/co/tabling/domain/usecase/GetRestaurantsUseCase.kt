package jth.kr.co.tabling.domain.usecase

import jth.kr.co.tabling.data.repository.RestaurantsRepository
import jth.kr.co.tabling.domain.mapper.asRestaurant
import jth.kr.co.tabling.domain.mapper.changeFavorite
import jth.kr.co.tabling.domain.model.Restaurant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetRestaurantsUseCase(
    private val repository: RestaurantsRepository,
) {
    operator fun invoke(
        isRefresh: Boolean,
        localRestaurants: List<Restaurant>?,
        scope: CoroutineScope,
        onResult: (List<Restaurant>) -> Unit = {},
        onFail: (String) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            try {
                val result: MutableList<Restaurant> = mutableListOf()
                val favoriteList = repository.getFavoriteRestaurants().map { it.restaurantIdx }

                if (isRefresh) {
                    val response = repository.getRestaurants()

                    response.list.forEach { original ->
                        if (favoriteList.contains(original.restaurantIdx)) {
                            result.add(original.asRestaurant(true))
                        } else {
                            result.add(original.asRestaurant(false))
                        }
                    }
                } else {
                    localRestaurants?.forEach { original ->
                        if (favoriteList.contains(original.restaurantIdx)) {
                            result.add(original.changeFavorite(true))
                        } else {
                            result.add(original.changeFavorite(false))
                        }
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