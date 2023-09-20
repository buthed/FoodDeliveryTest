package com.tematihonov.fooddeliverytest.domain.usecase

import com.tematihonov.fooddeliverytest.domain.usecase.network.GetCategoriesUseCase

data class NetworkUseCase(
    val getCategoriesUseCase: GetCategoriesUseCase
)