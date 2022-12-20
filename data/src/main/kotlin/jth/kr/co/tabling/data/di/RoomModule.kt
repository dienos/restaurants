package jth.kr.co.tabling.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jth.kr.co.tabling.data.db.SampleDataBase
import jth.kr.co.tabling.data.db.dao.SampleDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    private const val name = "sample.db"

    @Provides
    @Singleton
    fun provideSampleDao(dataBase: SampleDataBase): SampleDao {
        return dataBase.SampleDao()
    }

    @Provides
    @Singleton
    fun provideSampleDatabase(
        @ApplicationContext context: Context
    ): SampleDataBase = Room
        .databaseBuilder(context, SampleDataBase::class.java, name)
        .build()
}