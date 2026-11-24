package com.jithin.akcademoapplication.di

import com.jithin.akcademoapplication.data.remote.datasource.ProductRemoteDataSource
import com.jithin.akcademoapplication.data.remote.datasource.ProductRemoteDataSourceImpl
import com.jithin.akcademoapplication.data.repository.ProductRepositoryImpl
import com.jithin.akcademoapplication.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRemoteDataSource(
        impl: ProductRemoteDataSourceImpl
    ): ProductRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository
}
