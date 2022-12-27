package jth.kr.co.tabling.domain.model

enum class ViewType {
    SAVE,
    RECENT,
    FAVORITE
}

data class Restaurant(
    val viewType : ViewType = ViewType.SAVE,
    val restaurantIdx: Int? = null,
    val thumbnail: String? = null,
    val classification: String? = null,
    val restaurantName: String? = null,
    val rating: String? = null,
    val reviewCount: String? = null,
    val summaryAddress: String? = null,
    val tags : List<String> ?= null,
    val isFavorite: Boolean ?= null,
    val waitingCount: String? = null
)