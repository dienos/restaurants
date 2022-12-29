package jth.kr.co.tabling.domain.mapper

import jth.kr.co.tabling.data.model.FavoriteRestaurantEntity
import jth.kr.co.tabling.data.model.RestaurantDTO
import jth.kr.co.tabling.domain.model.Restaurant

fun Restaurant.asFavoriteRestaurantEntity(): FavoriteRestaurantEntity {
    return FavoriteRestaurantEntity(
        restaurantIdx = restaurantIdx ?: -1,
        createTime = System.currentTimeMillis()
    )
}

fun Restaurant.changeFavorite(isFavorite: Boolean): Restaurant {
    return Restaurant(
        restaurantIdx = restaurantIdx,
        thumbnail = thumbnail,
        classification = classification,
        restaurantName = restaurantName,
        rating = rating,
        reviewCount = reviewCount,
        summaryAddress = summaryAddress,
        tags = tags,
        isFavorite = isFavorite,
        waitingCount = waitingCount
    )
}

fun RestaurantDTO.asRestaurant(isFavorite: Boolean): Restaurant {
    val tags: MutableList<String> = arrayListOf()

    isQuickBooking?.let {
        if (it) {
            tags.add("즉시 예약")
        }
    }

    isRemoteWaiting?.let {
        if (it) {
            tags.add("원격 줄서기")
        }
    }

    isNew?.let {
        if (it) {
            tags.add("새로운 매장")
        }
    }

    return Restaurant(
        restaurantIdx = restaurantIdx,
        thumbnail = thumbnail,
        classification = classification,
        restaurantName = restaurantName,
        rating = rating?.toString() ?: "",
        reviewCount = reviewCount?.let {
            "(" + reviewCount?.toString() + ")"
        } ?: "",
        summaryAddress = summaryAddress,
        tags = tags,
        waitingCount = waitingCount?.let {
            if (it == 0) {
                "즉시입장"
            } else {
                "대기 : $it"
            }
        } ?: "즉시입장",
        isFavorite = isFavorite,
    )
}

