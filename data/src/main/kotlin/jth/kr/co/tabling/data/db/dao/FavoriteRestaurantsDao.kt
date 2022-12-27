package jth.kr.co.tabling.data.db.dao

import androidx.room.*
import jth.kr.co.tabling.data.model.FavoriteRestaurantEntity

@Dao
interface FavoriteRestaurantsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: FavoriteRestaurantEntity)

    @Query("SELECT * FROM favorite_restaurants ORDER BY createTime ASC")
    suspend fun getAll(): List<FavoriteRestaurantEntity>

    @Query("DELETE FROM favorite_restaurants WHERE restaurantIdx = :restaurantIdx")
    suspend fun deleteUserById(restaurantIdx: Int)
}