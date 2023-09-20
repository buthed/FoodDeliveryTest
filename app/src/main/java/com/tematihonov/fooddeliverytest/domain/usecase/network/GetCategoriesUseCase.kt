package com.tematihonov.fooddeliverytest.domain.usecase.network

import com.tematihonov.fooddeliverytest.domain.models.responceCategories.CategoriesList
import com.tematihonov.fooddeliverytest.domain.repository.NetworkRepository
import retrofit2.Response
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {
    suspend fun invoke(): Response<CategoriesList> {
        return networkRepository.getCategories()
    }
}