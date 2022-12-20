package jth.kr.co.tabling.data.api

import jth.kr.co.tabling.data.model.RestaurantsDTO
import retrofit2.http.GET

interface TablingService {
    @GET("/tabling/tabling_mobile_test/save")
    suspend fun getRestaurants() : RestaurantsDTO

    @GET("tabling/tabling_mobile_test/recent")
    suspend fun getRecentRestaurants() : RestaurantsDTO
}