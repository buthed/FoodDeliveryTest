package com.tematihonov.fooddeliverytest.data.repositoryImpl

import com.tematihonov.fooddeliverytest.data.network.ApiService
import com.tematihonov.fooddeliverytest.domain.models.responceProducts.ProductsListItem
import com.tematihonov.fooddeliverytest.domain.models.responseCategories.CategoriesListItem
import com.tematihonov.fooddeliverytest.domain.models.responseTags.TagsListItem
import com.tematihonov.fooddeliverytest.domain.repository.NetworkRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): NetworkRepository {

    override suspend fun getCategories(): Response<ArrayList<CategoriesListItem>> {
        return apiService.getCategories()
    }

    override suspend fun getProducts(): Response<ArrayList<ProductsListItem>> {
        return apiService.getProducts()
    }

    override suspend fun getTags(): Response<ArrayList<TagsListItem>> {
        return apiService.getTags()
    }
}