package com.tematihonov.fooddeliverytest.presentation.catalog

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tematihonov.fooddeliverytest.data.repositoryImpl.NetworkRepositoryImpl
import com.tematihonov.fooddeliverytest.domain.models.responceCategories.CategoriesListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val networkRepositoryImpl: NetworkRepositoryImpl
): ViewModel() {

    var catalogCategories: MutableStateFlow<ArrayList<CategoriesListItem>> = MutableStateFlow(arrayListOf())
    var isLoadingCategories by mutableStateOf(true)

    fun checkCategories() {
        viewModelScope.launch {
            isLoadingCategories = true
            val result = networkRepositoryImpl.getCategories()
            if (result.isSuccessful) {
                catalogCategories.value = networkRepositoryImpl.getCategories().body()!!
                isLoadingCategories = false
                catalogCategories.value.forEach {
                    Log.d("GGG", it.name)
                }
            }
        }
    }
}