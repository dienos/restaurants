package jth.kr.co.tabling.domain.model

data class Restaurant(
    val restaurantIdx: Int? = null,
    val thumbnail: String? = null,
    val classification: String? = null,
    val restaurantName: String? = null,
    val rating: String? = null,
    val reviewCount: String? = null,
    val summaryAddress: String? = null,
    val tags : List<String> ?= null,
    val isFavorite: Boolean = false,
    val waitingCount: String? = null
)