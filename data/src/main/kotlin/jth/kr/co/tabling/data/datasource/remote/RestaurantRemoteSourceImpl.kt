package jth.kr.co.tabling.data.datasource.remote

import jth.kr.co.tabling.data.api.TablingService
import jth.kr.co.tabling.data.model.RestaurantsDTO
import javax.inject.Inject

interface RestaurantRemoteSource {
    suspend fun getRestaurants(): RestaurantsDTO
    suspend fun getRecentRestaurants(): RestaurantsDTO
}

class RestaurantRemoteSourceImpl @Inject constructor(
    private val service: TablingService
) : RestaurantRemoteSource {
    override suspend fun getRestaurants(): RestaurantsDTO = service.getRestaurants()
    override suspend fun getRecentRestaurants(): RestaurantsDTO = service.getRecentRestaurants()
}