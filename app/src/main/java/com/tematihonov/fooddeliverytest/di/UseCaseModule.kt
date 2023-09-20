package com.tematihonov.fooddeliverytest.di

import com.tematihonov.fooddeliverytest.domain.repository.NetworkRepository
import com.tematihonov.fooddeliverytest.domain.usecase.NetworkUseCase
import com.tematihonov.fooddeliverytest.domain.usecase.network.GetCategoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideNetworkUseCases(networkRepository: NetworkRepository): NetworkUseCase {
        return NetworkUseCase(
            getCategoriesUseCase = GetCategoriesUseCase(networkRepository)
        )
    }
}