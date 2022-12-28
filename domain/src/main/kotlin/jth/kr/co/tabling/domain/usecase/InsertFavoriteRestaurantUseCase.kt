package jth.kr.co.tabling.domain.usecase

import jth.kr.co.tabling.data.repository.RestaurantsRepository
import jth.kr.co.tabling.domain.mapper.asFavoriteRestaurantEntity
import jth.kr.co.tabling.domain.model.Restaurant
import kotlinx.coroutines.*
import java.lang.Exception

class InsertFavoriteRestaurantUseCase(
    private val repository: RestaurantsRepository,
) {
    operator fun invoke(
        scope: CoroutineScope,
        data: Restaurant,
        onSuccess: () -> Unit = {},
        onFail: (String) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            try {
                repository.insertFavoriteRestaurant(
                    data.asFavoriteRestaurantEntity()
                )

                onSuccess()
            } catch (e: Exception) {
                e.message?.let {
                    onFail(it)
                } ?: onFail("알수 없는 에러 입니다.")
            }
        }
    }
}