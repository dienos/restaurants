package jth.kr.co.tabling.domain.mapper

import jth.kr.co.tabling.data.model.RestaurantDTO
import jth.kr.co.tabling.domain.model.Restaurant

object RestaurantMapper {
    fun convertRestaurant(dto: RestaurantDTO): Restaurant {
        val tags : MutableList<String> = arrayListOf()

        if(dto.isQuickBooking != null && dto.isQuickBooking!!) {
            tags.add("즉시 예약")
        }

        if(dto.isRemoteWaiting != null && dto.isRemoteWaiting!!) {
            tags.add("원격 줄서기")
        }

        if(dto.isNew != null && dto.isNew!!) {
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
                if(it == 0) {
                    "즉시입장"
                } else {
                    "대기 : $it"
                }
            }?: "즉시입장",
        )
    }
}

