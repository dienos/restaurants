package jth.kr.co.tabling.domain.usecase

import jth.kr.co.tabling.data.repository.RestaurantsRepository
import jth.kr.co.tabling.domain.mapper.RestaurantMapper
import jth.kr.co.tabling.domain.model.Restaurant
import jth.kr.co.tabling.domain.model.ViewType
import kotlinx.coroutines.*
import java.lang.Exception

class GetFavoriteRestaurantsUseCase(
    private val repository: RestaurantsRepository,
) {
    operator fun invoke(
        isRefresh: Boolean,
        localRestaurants: List<Restaurant>?,
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
                    val restaurants = repository.getRestaurants()
                    val recentRestaurants = repository.getRecentRestaurants()

                    restaurants.list.union(recentRestaurants.list).filter { union ->
                        favoriteList.contains(union.restaurantIdx)
                    }.forEach {
                        result.add(RestaurantMapper.convertRestaurant(ViewType.FAVORITE, true, it))
                    }
                } else {
                    localRestaurants?.let {
                        localRecentRestaurants?.union(it)?.filter { union ->
                            favoriteList.contains(union.restaurantIdx)
                        }?.forEach {
                            result.add(
                                RestaurantMapper.changeFavorite(true, it)
                            )
                        }
                    }
                }

                onResult(result.distinct())
            } catch (e: Exception) {
                e.message?.let {
                    onFail(it)
                } ?: onFail("알수 없는 에러 입니다.")
            }
        }

    }
}