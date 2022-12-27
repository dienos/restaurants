package jth.kr.co.tabling.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import jth.kr.co.tabling.data.repository.RestaurantsRepositoryImpl
import jth.kr.co.tabling.domain.usecase.*

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun providesGetRestaurantsUseCase(repository: RestaurantsRepositoryImpl): GetRestaurantsUseCase {
        return GetRestaurantsUseCase(repository)
    }

    @Provides
    fun providesGetRecentRestaurantsUseCae(repository: RestaurantsRepositoryImpl): GetRecentRestaurantsUseCae {
        return GetRecentRestaurantsUseCae(repository)
    }

    @Provides
    fun providesGetFavoriteRestaurantsUseCase(repository: RestaurantsRepositoryImpl): GetFavoriteRestaurantsUseCase {
        return GetFavoriteRestaurantsUseCase(repository)
    }

    @Provides
    fun providesInsertFavoriteRestaurantUseCase(repository: RestaurantsRepositoryImpl): InsertFavoriteRestaurantUseCase {
        return InsertFavoriteRestaurantUseCase(repository)
    }

    @Provides
    fun providesDeleteFavoriteRestaurantUseCase(repository: RestaurantsRepositoryImpl): DeleteFavoriteRestaurantUseCase {
        return DeleteFavoriteRestaurantUseCase(repository)
    }
}