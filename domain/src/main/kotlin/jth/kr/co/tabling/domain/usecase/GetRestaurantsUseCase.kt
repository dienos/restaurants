package jth.kr.co.tabling.domain.usecase

import jth.kr.co.tabling.data.repository.RestaurantsRepository
import jth.kr.co.tabling.domain.mapper.RestaurantMapper
import jth.kr.co.tabling.domain.model.Restaurant
import jth.kr.co.tabling.domain.model.ViewType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
                val favoriteList =repository.getFavoriteRestaurants().map { it.restaurantIdx }

                if (isRefresh) {
                    val response = repository.getRestaurants()

                    response.list.forEach { original ->
                        if (favoriteList.contains(original.restaurantIdx)) {
                            result.add(
                                RestaurantMapper.convertRestaurant(
                                    ViewType.SAVE,
                                    true,
                                    original
                                )
                            )
                        } else {
                            result.add(
                                RestaurantMapper.convertRestaurant(
                                    ViewType.SAVE,
                                    false,
                                    original
                                )
                            )
                        }
                    }
                } else {
                    localRestaurants?.forEach { original ->
                        if (favoriteList.contains(original.restaurantIdx)) {
                            result.add(
                                RestaurantMapper.changeFavorite(true, original)
                            )
                        } else {
                            result.add(
                                RestaurantMapper.changeFavorite(false, original)
                            )
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