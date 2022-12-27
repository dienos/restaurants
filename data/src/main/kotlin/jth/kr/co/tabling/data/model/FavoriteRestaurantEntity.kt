package jth.kr.co.tabling.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_restaurants")
data class FavoriteRestaurantEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "restaurantIdx")
    val restaurantIdx: Int,

    @ColumnInfo(name = "createTime")
    val createTime: Long
)