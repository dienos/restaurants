package jth.kr.co.tabling.domain.usecase

import jth.kr.co.tabling.data.repository.RestaurantsRepository
import jth.kr.co.tabling.domain.mapper.asRestaurant
import jth.kr.co.tabling.domain.mapper.changeFavorite
import jth.kr.co.tabling.domain.model.Restaurant
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

                    (recentRestaurants.list as ArrayList).addAll(restaurants.list)
                    val distinctList =
                        (recentRestaurants.list as ArrayList).distinctBy { it.restaurantIdx }

                    distinctList.filter { item ->
                        favoriteList.contains(item.restaurantIdx)
                    }.forEach {
                        result.add(it.asRestaurant(true))
                    }
                } else {
                    localRestaurants?.let { restaurants ->
                        localRecentRestaurants?.let { recentRestaurants ->
                            (recentRestaurants as ArrayList).addAll(restaurants)
                            val distinctList = recentRestaurants.distinctBy { it.restaurantIdx }

                            distinctList.filter { item ->
                                favoriteList.contains(item.restaurantIdx)
                            }.forEach {
                                result.add(it.changeFavorite(true))
                            }
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