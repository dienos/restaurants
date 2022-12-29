package jth.kr.co.tabling.ui.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jth.kr.co.tabling.ui.utils.NetworkUtil

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {
    @Provides
    fun providesMetWorkUtil(): NetworkUtil = NetworkUtil()
}