package com.tematihonov.fooddeliverytest.domain.repository

import com.tematihonov.fooddeliverytest.domain.models.responceCategories.CategoriesList
import retrofit2.Response

interface NetworkRepository {

    suspend fun getCategories(): Response<CategoriesList>

}