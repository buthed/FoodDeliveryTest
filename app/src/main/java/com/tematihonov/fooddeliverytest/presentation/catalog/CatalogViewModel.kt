package com.tematihonov.fooddeliverytest.presentation.catalog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tematihonov.fooddeliverytest.domain.models.responceProducts.ProductsListItem
import com.tematihonov.fooddeliverytest.domain.models.responseCategories.CategoriesListItem
import com.tematihonov.fooddeliverytest.domain.models.responseTags.TagsListItem
import com.tematihonov.fooddeliverytest.domain.usecase.NetworkUseCase
import com.tematihonov.fooddeliverytest.utils.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val networkUseCase: NetworkUseCase
): ViewModel() {

    var basketScreenVisibility by mutableStateOf(false)

    var catalogCategories by mutableStateOf(emptyList<CategoriesListItem>())
    var productsList by mutableStateOf(emptyList<ProductsListItem>())
    var tagsList by mutableStateOf(emptyList<TagsListItem>())

    var currentCategory by mutableStateOf(0)
    var currentProduct by mutableStateOf<ProductsListItem?>(null)
    var currentProductSelected by mutableStateOf(false)
    var currentTags by mutableStateOf(arrayListOf<TagsListItem>())
    var tagsSelected by mutableStateOf(0)
    var searchAppBarState by mutableStateOf(SearchAppBarState.CLOSED)
    var searchStartWriting by mutableStateOf(false)

    var isLoadingCategories by mutableStateOf(true)
    var isLoadingProducts by mutableStateOf(true)
    var isLoadingTags by mutableStateOf(true)

    init {
        viewModelScope.launch {
            do {
                if (isLoadingCategories)  { checkCategories() }
                if (isLoadingProducts) { checkProducts(0) }
                if (isLoadingTags)  { checkTags() }
                delay(5000)
            } while (isLoadingProducts || isLoadingTags || isLoadingCategories)
        }
    }

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
                if (currentCategory == 0) {
                    productsList = tempList
                } else {
                    tempList.forEach {
                        if (it.category_id == currentCategory) newArr.add(it)
                    }
                }
                productsList = if (currentTags.isEmpty()) newArr
                else {
                    val newArrWithTags = ArrayList<ProductsListItem>()
                    newArr.forEach { product ->
                        currentTags.forEach { tag ->
                            if (product.tag_ids.contains(tag.id)) newArrWithTags.add(product)
                        }
                    }
                    newArrWithTags
                }
                isLoadingProducts = false
            }
        }
    }

    fun searchProducts(search: String) {
        viewModelScope.launch {
            if (search.isEmpty()) {
                productsList = ArrayList<ProductsListItem>()
                searchStartWriting = false
            } else {
                isLoadingProducts = true
                val result = networkUseCase.getProductsUseCase.invoke()
                if (result.isSuccessful) {
                    val tempList = result.body()!!
                    val newArr = ArrayList<ProductsListItem>()
                    tempList.forEach {
                        if (it.name.lowercase().contains(search.lowercase())) newArr.add(it)
                    }
                    productsList = newArr
                }
                isLoadingProducts = false
            }
        }
    }

    fun checkTags() {
        viewModelScope.launch {
            val result = networkUseCase.getTagsUseCase.invoke()
            isLoadingTags = true
            if (result.isSuccessful) {
                tagsList = networkUseCase.getTagsUseCase.invoke().body()!!
                isLoadingTags = false
            }
        }
    }

    fun setNewTags(newTag: TagsListItem) {
        if (currentTags.contains(newTag)) currentTags.remove(newTag)
        else currentTags.add(newTag)
        tagsSelected = currentTags.size
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