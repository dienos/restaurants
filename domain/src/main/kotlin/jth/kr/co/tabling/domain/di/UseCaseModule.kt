package jth.kr.co.tabling.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import jth.kr.co.tabling.data.repository.RestaurantsRepositoryImpl
import jth.kr.co.tabling.domain.usecase.GetLocalSampleUseCase
import jth.kr.co.tabling.domain.usecase.GetRecentRestaurantsUseCae
import jth.kr.co.tabling.domain.usecase.GetRestaurantsUseCase

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
    fun providesGetLocalSampleUseCase(repository: RestaurantsRepositoryImpl): GetLocalSampleUseCase {
        return GetLocalSampleUseCase(repository)
    }
}