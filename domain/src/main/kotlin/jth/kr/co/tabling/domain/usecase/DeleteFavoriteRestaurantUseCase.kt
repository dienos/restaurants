package jth.kr.co.tabling.domain.usecase

import jth.kr.co.tabling.data.repository.RestaurantsRepository
import kotlinx.coroutines.*
import java.lang.Exception

class DeleteFavoriteRestaurantUseCase(
    private val repository: RestaurantsRepository,
) {
    operator fun invoke(
        restaurantIdx: Int,
        scope: CoroutineScope,
        onSuccess: () -> Unit = {},
        onFail: (String) -> Unit = {}
    ) {
        scope.launch {
            withContext(Dispatchers.IO) {
                try {
                    repository.deleteFavoriteRestaurant(restaurantIdx)
                    onSuccess()
                } catch (e: Exception) {
                    e.message?.let {
                        onFail(it)
                    } ?: onFail("알수 없는 에러 입니다.")
                }
            }
        }
    }
}