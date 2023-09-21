package com.tematihonov.fooddeliverytest.domain.usecase

import com.tematihonov.fooddeliverytest.domain.usecase.network.GetCategoriesUseCase
import com.tematihonov.fooddeliverytest.domain.usecase.network.GetProductsUseCase
import com.tematihonov.fooddeliverytest.domain.usecase.network.GetTagsUseCase

data class NetworkUseCase(
    val getCategoriesUseCase: GetCategoriesUseCase,
    val getProductsUseCase: GetProductsUseCase,
    val getTagsUseCase: GetTagsUseCase
)