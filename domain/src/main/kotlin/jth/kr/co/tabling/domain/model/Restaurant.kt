package jth.kr.co.tabling.domain.model

data class Restaurant(
    val restaurantIdx : Int? = null,
    val thumbnail : String? = null,
    val classification : String? = null,
    val restaurantName : String? = null,
    val rating : Float? = null,
    val reviewCount : Int? = null,
    val summaryAddress : String? = null,
    val isQuickBooking : Boolean? = null,
    val isRemoteWaiting : Boolean? = null,
    val useWaiting : Boolean? = null,
    val useBooking : Boolean? = null,
    val isNew : Boolean? = null,
    val waitingCount : Int? = null
)