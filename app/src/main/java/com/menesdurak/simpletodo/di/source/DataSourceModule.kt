package com.menesdurak.simpletodo.di.source

import com.menesdurak.simpletodo.data.source.LocalDataSource
import com.menesdurak.simpletodo.data.source.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindDataSource(
        localDataSourceImpl: LocalDataSourceImpl,
    ): LocalDataSource
}