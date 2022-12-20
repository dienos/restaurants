package jth.kr.co.tabling.data.repository

import jth.kr.co.tabling.data.datasource.local.SampleLocalSource
import jth.kr.co.tabling.data.datasource.remote.SampleRemoteSource
import jth.kr.co.tabling.data.model.SampleEntity
import jth.kr.co.tabling.data.model.SampleDTO
import javax.inject.Inject

interface SampleRepository {
    suspend fun getLocalSample(): List<SampleEntity>
    suspend fun getSample(): List<SampleDTO>
}

class SampleRepositoryImpl @Inject constructor(
    private val localSource: SampleLocalSource,
    private val remoteSource: SampleRemoteSource
) : SampleRepository {
    override suspend fun getLocalSample(): List<SampleEntity> = localSource.getLocalSimple()
    override suspend fun getSample(): List<SampleDTO> = remoteSource.getSimple()
}