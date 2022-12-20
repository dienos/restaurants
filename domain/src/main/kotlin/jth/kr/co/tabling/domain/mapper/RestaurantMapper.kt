package jth.kr.co.tabling.domain.mapper

import jth.kr.co.tabling.data.model.RestaurantDTO
import jth.kr.co.tabling.domain.model.Restaurant

fun RestaurantDTO.asRestaurant() = Restaurant(
    restaurantIdx = this.restaurantIdx,
    thumbnail = this.thumbnail,
    classification = this.thumbnail,
    restaurantName = this.thumbnail,
    rating = this.rating,
    reviewCount = this.reviewCount,
    summaryAddress = this.summaryAddress,
    isQuickBooking = this.isQuickBooking,
    isRemoteWaiting = this.isRemoteWaiting,
    useWaiting = this.useWaiting,
    useBooking = this.useBooking,
    isNew = this.isNew,
    waitingCount = this.waitingCount,
)