package jth.kr.co.tabling.data.model

import com.google.gson.annotations.SerializedName

data class RestaurantDTO(
    @SerializedName("restaurantIdx")
    val restaurantIdx : Int? = null,

    @SerializedName("thumbnail")
    val thumbnail : String? = null,

    @SerializedName("classification")
    val classification : String? = null,

    @SerializedName("restaurantName")
    val restaurantName : String? = null,

    @SerializedName("rating")
    val rating : Float? = null,

    @SerializedName("reviewCount")
    val reviewCount : Int? = null,

    @SerializedName("summaryAddress")
    val summaryAddress : String? = null,

    @SerializedName("isQuickBooking")
    val isQuickBooking : Boolean? = null,

    @SerializedName("isRemoteWaiting")
    val isRemoteWaiting : Boolean? = null,

    @SerializedName("useWaiting")
    val useWaiting : Boolean? = null,

    @SerializedName("useBooking")
    val useBooking : Boolean? = null,

    @SerializedName("isNew")
    val isNew : Boolean? = null,

    @SerializedName("waitingCount")
    val waitingCount : Int? = null)
