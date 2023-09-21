package com.tematihonov.fooddeliverytest.data.network

import com.tematihonov.fooddeliverytest.domain.models.responceCategories.CategoriesListItem
import com.tematihonov.fooddeliverytest.utils.RetrofitConstants.CATEGORIES
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(CATEGORIES)
    suspend fun getCategories(): Response<ArrayList<CategoriesListItem>>
}