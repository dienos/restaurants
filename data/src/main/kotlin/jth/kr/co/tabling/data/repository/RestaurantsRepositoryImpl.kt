package jth.kr.co.tabling.data.repository

import jth.kr.co.tabling.data.datasource.local.RestaurantsLocalSource
import jth.kr.co.tabling.data.datasource.remote.RestaurantRemoteSource
import jth.kr.co.tabling.data.model.FavoriteRestaurantEntity
import jth.kr.co.tabling.data.model.RestaurantsDTO
import javax.inject.Inject

interface RestaurantsRepository {
    suspend fun getFavoriteRestaurants(): List<FavoriteRestaurantEntity>
    suspend fun insertFavoriteRestaurant(data: FavoriteRestaurantEntity)
    suspend fun deleteFavoriteRestaurant(restaurantIdx: Int)
    suspend fun getRestaurants(): RestaurantsDTO
    suspend fun getRecentRestaurants(): RestaurantsDTO
}

class RestaurantsRepositoryImpl @Inject constructor(
    private val localSource: RestaurantsLocalSource,
    private val remoteSource: RestaurantRemoteSource
) : RestaurantsRepository {
    override suspend fun getFavoriteRestaurants(): List<FavoriteRestaurantEntity> =
        localSource.getFavoriteRestaurants()

    override suspend fun insertFavoriteRestaurant(data: FavoriteRestaurantEntity) =
        localSource.insertFavoriteRestaurant(data)

    override suspend fun deleteFavoriteRestaurant(restaurantIdx: Int) =
        localSource.deleteFavoriteRestaurant(restaurantIdx)

    override suspend fun getRestaurants(): RestaurantsDTO = remoteSource.getRestaurants()
    override suspend fun getRecentRestaurants(): RestaurantsDTO =
        remoteSource.getRecentRestaurants()
}