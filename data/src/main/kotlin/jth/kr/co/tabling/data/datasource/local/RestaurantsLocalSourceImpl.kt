package jth.kr.co.tabling.data.datasource.local

import jth.kr.co.tabling.data.db.dao.FavoriteRestaurantsDao
import jth.kr.co.tabling.data.model.FavoriteRestaurantEntity
import javax.inject.Inject

interface RestaurantsLocalSource {
    suspend fun getFavoriteRestaurants(): List<FavoriteRestaurantEntity>
    suspend fun insertFavoriteRestaurant(data : FavoriteRestaurantEntity)
    suspend fun deleteFavoriteRestaurant(restaurantIdx: Int)
}

class RestaurantsLocalSourceImpl @Inject constructor(
    private val dao: FavoriteRestaurantsDao
) : RestaurantsLocalSource {
    override suspend fun getFavoriteRestaurants(): List<FavoriteRestaurantEntity> = dao.getAll()
    override suspend fun insertFavoriteRestaurant(data: FavoriteRestaurantEntity) = dao.insert(data)
    override suspend fun deleteFavoriteRestaurant(restaurantIdx: Int) = dao.deleteUserById(restaurantIdx)
}