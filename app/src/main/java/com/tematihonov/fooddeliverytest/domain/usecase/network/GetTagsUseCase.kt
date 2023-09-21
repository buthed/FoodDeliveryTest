package com.tematihonov.fooddeliverytest.domain.usecase.network

import com.tematihonov.fooddeliverytest.domain.models.responseTags.TagsListItem
import com.tematihonov.fooddeliverytest.domain.repository.NetworkRepository
import retrofit2.Response
import javax.inject.Inject

class GetTagsUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {
    suspend fun invoke(): Response<ArrayList<TagsListItem>> {
        return networkRepository.getTags()
    }
}