package jth.kr.co.tabling.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jth.kr.co.tabling.data.datasource.local.SampleLocalSource
import jth.kr.co.tabling.data.datasource.local.SampleLocalSourceImpl
import jth.kr.co.tabling.data.datasource.remote.SampleRemoteSource
import jth.kr.co.tabling.data.datasource.remote.SampleRemoteSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindsSimpleRemoteSource(source: SampleRemoteSourceImpl): SampleRemoteSource

    @Singleton
    @Binds
    abstract fun bindsSimpleLocalSource(source: SampleLocalSourceImpl): SampleLocalSource
}