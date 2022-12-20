package jth.kr.co.tabling.data.datasource.local

import jth.kr.co.tabling.data.db.dao.SampleDao
import jth.kr.co.tabling.data.model.SampleEntity
import javax.inject.Inject

interface SampleLocalSource {
    suspend fun getLocalSimple(): List<SampleEntity>
}

class SampleLocalSourceImpl @Inject constructor(
    private val dao: SampleDao
) : SampleLocalSource {
    override suspend fun getLocalSimple(): List<SampleEntity> = dao.getSamples()
}