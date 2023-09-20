package com.tematihonov.fooddeliverytest.presentation.catalog

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tematihonov.fooddeliverytest.data.repositoryImpl.NetworkRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val networkRepositoryImpl: NetworkRepositoryImpl
): ViewModel() {

    fun checkCategories() {
        viewModelScope.launch {
            val result = networkRepositoryImpl.getCategories()
            if (result.isSuccessful) {
                result.body()?.forEach {
                    Log.d("GGG", it.name)
                }
            }
        }
    }
}