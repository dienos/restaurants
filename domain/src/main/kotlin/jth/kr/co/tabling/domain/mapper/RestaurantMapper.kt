package jth.kr.co.tabling.domain.mapper

import jth.kr.co.tabling.data.model.FavoriteRestaurantEntity
import jth.kr.co.tabling.data.model.RestaurantDTO
import jth.kr.co.tabling.domain.model.Restaurant
import jth.kr.co.tabling.domain.model.ViewType

object RestaurantMapper {
    fun convertFavoriteRestaurantEntity(data: Restaurant): FavoriteRestaurantEntity {
        return FavoriteRestaurantEntity(
            restaurantIdx = data.restaurantIdx ?: -1,
            createTime = System.currentTimeMillis()
        )
    }

    fun changeFavorite(isFavorite : Boolean, original: Restaurant): Restaurant {
        return Restaurant(
            viewType = original.viewType,
            restaurantIdx = original.restaurantIdx,
            thumbnail = original.thumbnail,
            classification = original.classification,
            restaurantName = original.restaurantName,
            rating = original.rating,
            reviewCount = original.reviewCount,
            summaryAddress = original.summaryAddress,
            tags = original.tags,
            isFavorite = isFavorite,
            waitingCount = original.waitingCount
        )
    }

    fun convertRestaurant(viewType: ViewType, isFavorite: Boolean, dto: RestaurantDTO): Restaurant {
        val tags: MutableList<String> = arrayListOf()

        if (dto.isQuickBooking != null && dto.isQuickBooking!!) {
            tags.add("즉시 예약")
        }

        if (dto.isRemoteWaiting != null && dto.isRemoteWaiting!!) {
            tags.add("원격 줄서기")
        }

        if (dto.isNew != null && dto.isNew!!) {
            tags.add("새로운 매장")
        }

        return Restaurant(
            restaurantIdx = dto.restaurantIdx,
            thumbnail = dto.thumbnail,
            classification = dto.classification,
            restaurantName = dto.restaurantName,
            rating = dto.rating?.toString() ?: "",
            reviewCount = dto.reviewCount?.let {
                "(" + dto.reviewCount?.toString() + ")"
            } ?: "",
            summaryAddress = dto.summaryAddress,
            tags = tags,
            waitingCount = dto.waitingCount?.let {
                if (it == 0) {
                    "즉시입장"
                } else {
                    "대기 : $it"
                }
            } ?: "즉시입장",
            isFavorite = isFavorite,
            viewType = viewType
        )
    }
}

