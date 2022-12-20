package jth.kr.co.tabling.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import jth.kr.co.tabling.data.db.dao.SampleDao
import jth.kr.co.tabling.data.model.SampleEntity

@Database(
    entities = [SampleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SampleDataBase : RoomDatabase() {
   abstract fun SampleDao() : SampleDao
}