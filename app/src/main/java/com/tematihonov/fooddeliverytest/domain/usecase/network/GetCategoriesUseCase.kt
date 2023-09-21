package com.tematihonov.fooddeliverytest.domain.usecase.network

import com.tematihonov.fooddeliverytest.domain.models.responceCategories.CategoriesListItem
import com.tematihonov.fooddeliverytest.domain.repository.NetworkRepository
import retrofit2.Response
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {
    suspend fun invoke(): Response<ArrayList<CategoriesListItem>> {
        return networkRepository.getCategories()
    }
}