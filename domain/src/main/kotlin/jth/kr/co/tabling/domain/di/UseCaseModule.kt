package jth.kr.co.tabling.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import jth.kr.co.tabling.data.repository.SampleRepositoryImpl
import jth.kr.co.tabling.domain.usecase.GetLocalSampleUseCase
import jth.kr.co.tabling.domain.usecase.GetSampleUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun providesGetSampleUseCase(repository: SampleRepositoryImpl): GetSampleUseCase {
        return GetSampleUseCase(repository)
    }

    @Provides
    fun providesGetLocalSampleUseCase(repository: SampleRepositoryImpl): GetLocalSampleUseCase {
        return GetLocalSampleUseCase(repository)
    }
}