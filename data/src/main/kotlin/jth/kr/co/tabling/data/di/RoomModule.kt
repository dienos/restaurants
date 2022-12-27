package jth.kr.co.tabling.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jth.kr.co.tabling.data.db.FavoriteRestaurantsDataBase
import jth.kr.co.tabling.data.db.dao.FavoriteRestaurantsDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    private const val name = "FavoriteRestaurants.db"

    @Provides
    @Singleton
    fun provideFavoriteRestaurantsDao(dataBase: FavoriteRestaurantsDataBase): FavoriteRestaurantsDao =
        dataBase.favoriteRestaurantsDao()

    @Provides
    @Singleton
    fun provideFavoriteRestaurantsDataBase(
        @ApplicationContext context: Context
    ): FavoriteRestaurantsDataBase = Room
        .databaseBuilder(context, FavoriteRestaurantsDataBase::class.java, name)
        .build()
}