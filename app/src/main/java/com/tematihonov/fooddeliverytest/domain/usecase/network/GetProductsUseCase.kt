package com.tematihonov.fooddeliverytest.domain.usecase.network

import com.tematihonov.fooddeliverytest.domain.models.responceProducts.ProductsListItem
import com.tematihonov.fooddeliverytest.domain.repository.NetworkRepository
import retrofit2.Response
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {
    suspend fun invoke(): Response<ArrayList<ProductsListItem>> {
        return networkRepository.getProducts()
    }
}