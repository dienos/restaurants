package jth.kr.co.tabling.domain.usecase

import jth.kr.co.tabling.data.repository.RestaurantsRepository
import jth.kr.co.tabling.domain.mapper.RestaurantMapper
import jth.kr.co.tabling.domain.model.Restaurant
import jth.kr.co.tabling.domain.model.ViewType
import kotlinx.coroutines.*
import java.lang.Exception

class GetRecentRestaurantsUseCae(
    private val repository: RestaurantsRepository,
) {
    operator fun invoke(
        isRefresh: Boolean,
        localRecentRestaurants: List<Restaurant>?,
        scope: CoroutineScope,
        onResult: (List<Restaurant>) -> Unit = {},
        onFail: (String) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            try {
                val result: MutableList<Restaurant> = mutableListOf()

                val favoriteList = repository.getFavoriteRestaurants().map { it.restaurantIdx }

                if (isRefresh) {
                    val recentList = repository.getRecentRestaurants().list

                    recentList.forEach { original ->
                        if (favoriteList.contains(original.restaurantIdx)) {
                            result.add(
                                RestaurantMapper.convertRestaurant(
                                    ViewType.RECENT,
                                    true,
                                    original
                                )
                            )
                        } else {
                            result.add(
                                RestaurantMapper.convertRestaurant(
                                    ViewType.RECENT,
                                    false,
                                    original
                                )
                            )
                        }
                    }
                } else {
                    localRecentRestaurants?.forEach { original ->
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