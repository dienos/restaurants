package jth.kr.co.tabling.data.datasource.remote

import jth.kr.co.tabling.data.api.SampleService
import jth.kr.co.tabling.data.model.SampleDTO
import javax.inject.Inject

interface SampleRemoteSource {
    suspend fun getSimple(): List<SampleDTO>
}

class SampleRemoteSourceImpl @Inject constructor(
    private val sampleService: SampleService
) : SampleRemoteSource {
    override suspend fun getSimple(): List<SampleDTO> = sampleService.getSample()
}