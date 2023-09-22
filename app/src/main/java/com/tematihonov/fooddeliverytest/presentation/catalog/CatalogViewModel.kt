package com.tematihonov.fooddeliverytest.presentation.catalog

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tematihonov.fooddeliverytest.domain.models.responceProducts.ProductsListItem
import com.tematihonov.fooddeliverytest.domain.models.responseCategories.CategoriesListItem
import com.tematihonov.fooddeliverytest.domain.usecase.NetworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val networkUseCase: NetworkUseCase
): ViewModel() {

    var catalogCategories by mutableStateOf(emptyList<CategoriesListItem>())
    var productsList by mutableStateOf(emptyList<ProductsListItem>())
    var currentCategory by mutableStateOf(676153)
    var currentProduct by mutableStateOf<ProductsListItem?>(null)
    var currentProductSelected by mutableStateOf(false)
    var totalPrice by mutableStateOf(0)


    var isLoadingCategories by mutableStateOf(true)
    var isLoadingProducts by mutableStateOf(true)

    fun checkCategories() {
        viewModelScope.launch {
            isLoadingCategories = true
            val result = networkUseCase.getCategoriesUseCase.invoke()
            if (result.isSuccessful) {
                catalogCategories = networkUseCase.getCategoriesUseCase.invoke().body()!!
                currentCategory = catalogCategories[0].id
                checkProducts(currentCategory)
                isLoadingCategories = false
            }
        }
    }

    fun checkProducts(currentCategory: Int) {
        viewModelScope.launch {
            val result = networkUseCase.getProductsUseCase.invoke()
            isLoadingProducts = true
            if (result.isSuccessful) {
                val tempList = result.body()!!
                val newArr = ArrayList<ProductsListItem>()
                tempList.forEach {
                    if (it.category_id == currentCategory) newArr.add(it)
                }
                productsList = newArr
                isLoadingProducts = false
                productsList.forEach {
                    Log.d("GGG", it.name)
                }
            }
        }
    }

    fun selectNewCategory(newCategoryId: Int) {
        isLoadingProducts = true
        currentCategory = newCategoryId
        checkProducts(newCategoryId)
    }

    fun selectNewProduct(productsListItem: ProductsListItem?) {
        currentProductSelected =  (productsListItem != null)
        currentProduct = productsListItem
    }
}