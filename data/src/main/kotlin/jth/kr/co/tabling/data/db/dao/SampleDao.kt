package jth.kr.co.tabling.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jth.kr.co.tabling.data.model.SampleEntity

@Dao
interface SampleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: SampleEntity)

    @Query("SELECT * FROM sample")
    suspend fun getSamples(): List<SampleEntity>
}