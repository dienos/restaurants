package jth.kr.co.tabling.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jth.kr.co.tabling.data.repository.SampleRepository
import jth.kr.co.tabling.data.repository.SampleRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindsSampleRepository(
        localSource: SampleRepositoryImpl
    ): SampleRepository
}