package com.tematihonov.fooddeliverytest.domain.repository

import com.tematihonov.fooddeliverytest.domain.models.responceProducts.ProductsListItem
import com.tematihonov.fooddeliverytest.domain.models.responseCategories.CategoriesListItem
import com.tematihonov.fooddeliverytest.domain.models.responseTags.TagsListItem
import retrofit2.Response

interface NetworkRepository {

    suspend fun getCategories(): Response<ArrayList<CategoriesListItem>>

    suspend fun getProducts(): Response<ArrayList<ProductsListItem>>

    suspend fun getTags(): Response<ArrayList<TagsListItem>>

}