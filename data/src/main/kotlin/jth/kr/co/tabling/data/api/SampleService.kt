package jth.kr.co.tabling.data.api

import jth.kr.co.tabling.data.model.SampleDTO
import retrofit2.http.GET

interface SampleService {
    @GET("sample")
    suspend fun getSample() : List<SampleDTO>
}