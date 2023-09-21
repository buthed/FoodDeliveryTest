package com.tematihonov.fooddeliverytest.data.network

import com.tematihonov.fooddeliverytest.domain.models.responceProducts.ProductsListItem
import com.tematihonov.fooddeliverytest.domain.models.responseCategories.CategoriesListItem
import com.tematihonov.fooddeliverytest.domain.models.responseTags.TagsListItem
import com.tematihonov.fooddeliverytest.utils.RetrofitConstants.CATEGORIES
import com.tematihonov.fooddeliverytest.utils.RetrofitConstants.PRODUCTS
import com.tematihonov.fooddeliverytest.utils.RetrofitConstants.TAGS
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(CATEGORIES)
    suspend fun getCategories(): Response<ArrayList<CategoriesListItem>>

    @GET(PRODUCTS)
    suspend fun getProducts(): Response<ArrayList<ProductsListItem>>

    @GET(TAGS)
    suspend fun getTags(): Response<ArrayList<TagsListItem>>

}