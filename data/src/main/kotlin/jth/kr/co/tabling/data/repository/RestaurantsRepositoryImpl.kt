package jth.kr.co.tabling.data.repository

import jth.kr.co.tabling.data.datasource.local.SampleLocalSource
import jth.kr.co.tabling.data.datasource.remote.RestaurantRemoteSource
import jth.kr.co.tabling.data.model.SampleEntity
import jth.kr.co.tabling.data.model.RestaurantDTO
import jth.kr.co.tabling.data.model.RestaurantsDTO
import javax.inject.Inject

interface RestaurantsRepository {
    suspend fun getLocalSample(): List<SampleEntity>
    suspend fun getRestaurants(): RestaurantsDTO
    suspend fun getRecentRestaurants(): RestaurantsDTO
}

class RestaurantsRepositoryImpl @Inject constructor(
    private val localSource: SampleLocalSource,
    private val remoteSource: RestaurantRemoteSource
) : RestaurantsRepository {
    override suspend fun getLocalSample(): List<SampleEntity> = localSource.getLocalSimple()
    override suspend fun getRestaurants():RestaurantsDTO = remoteSource.getRestaurants()
    override suspend fun getRecentRestaurants(): RestaurantsDTO = remoteSource.getRecentRestaurants()
}