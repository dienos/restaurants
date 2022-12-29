package jth.kr.co.tabling.domain.model

import java.io.Serializable

data class Restaurant(
    val restaurantIdx: Int? = null,
    val thumbnail: String? = null,
    val classification: String? = null,
    val restaurantName: String? = null,
    val rating: String? = null,
    val reviewCount: String? = null,
    val summaryAddress: String? = null,
    val tags: List<String>? = null,
    val isFavorite: Boolean? = null,
    val waitingCount: String? = null
) : Serializable