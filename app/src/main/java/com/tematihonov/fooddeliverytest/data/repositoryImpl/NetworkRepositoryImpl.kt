package com.tematihonov.fooddeliverytest.data.repositoryImpl

import com.tematihonov.fooddeliverytest.data.network.ApiService
import com.tematihonov.fooddeliverytest.domain.models.responceCategories.CategoriesList
import com.tematihonov.fooddeliverytest.domain.repository.NetworkRepository
import retrofit2.Response
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): NetworkRepository {

    override suspend fun getCategories(): Response<CategoriesList> {
        return apiService.getCategories()
    }
}