package jth.kr.co.tabling.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import jth.kr.co.tabling.data.db.dao.FavoriteRestaurantsDao
import jth.kr.co.tabling.data.model.FavoriteRestaurantEntity

@Database(
    entities = [FavoriteRestaurantEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteRestaurantsDataBase : RoomDatabase() {
    abstract fun favoriteRestaurantsDao(): FavoriteRestaurantsDao
}