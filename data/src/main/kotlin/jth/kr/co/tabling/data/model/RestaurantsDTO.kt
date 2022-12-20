package jth.kr.co.tabling.data.model

import com.google.gson.annotations.SerializedName

data class RestaurantsDTO(
    @SerializedName("list")
    val list: List<RestaurantDTO>
)