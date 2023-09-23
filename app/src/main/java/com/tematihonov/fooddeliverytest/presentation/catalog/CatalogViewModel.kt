package com.tematihonov.fooddeliverytest.presentation.catalog

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tematihonov.fooddeliverytest.domain.models.responceProducts.ProductsListItem
import com.tematihonov.fooddeliverytest.domain.models.responseCategories.CategoriesListItem
import com.tematihonov.fooddeliverytest.domain.models.responseTags.TagsListItem
import com.tematihonov.fooddeliverytest.domain.usecase.NetworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val networkUseCase: NetworkUseCase
): ViewModel() {

    var catalogCategories by mutableStateOf(emptyList<CategoriesListItem>())
    var productsList by mutableStateOf(emptyList<ProductsListItem>())
    var tagsList by mutableStateOf(emptyList<TagsListItem>())

    var currentCategory by mutableStateOf(0)
    var currentProduct by mutableStateOf<ProductsListItem?>(null)
    var currentProductSelected by mutableStateOf(false)
    var currentTags by mutableStateOf(arrayListOf<TagsListItem>())
    var tagsSelected by mutableStateOf(0)
    var totalPrice by mutableStateOf(0)


    var isLoadingCategories by mutableStateOf(true)
    var isLoadingProducts by mutableStateOf(true)
    var isLoadingTags by mutableStateOf(true)

    init {
        viewModelScope.launch {
            do {
                if (isLoadingCategories)  {
                    Log.d("GGG", "start1 isLoadingCategories")
                    checkCategories()
                }

                if (isLoadingProducts) {
                    Log.d("GGG", "start2 isLoadingProducts")
                    checkProducts(0)
                }

                if (isLoadingTags)  {
                    Log.d("GGG", "start3 isLoadingTags")
                    checkTags()
                }
                delay(5000)
            } while (isLoadingProducts || isLoadingTags || isLoadingCategories)
        }
    }

    fun checkCategories() {
        viewModelScope.launch {
            isLoadingCategories = true
            val result = networkUseCase.getCategoriesUseCase.invoke()
            Log.d("GGG", "checkCategories ${result.body()}")
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
                if (currentTags.isEmpty()) productsList = newArr
                else {
                    val newArrWithTags = ArrayList<ProductsListItem>()
                    newArr.forEach { product ->
                        currentTags.forEach { tag ->
                            if (product.tag_ids.contains(tag.id)) newArrWithTags.add(product)
                        }
                    }
                    productsList = newArrWithTags
                }
                isLoadingProducts = false
            }
        }
    }

    fun checkTags() {
        viewModelScope.launch {
            val result = networkUseCase.getTagsUseCase.invoke()
            Log.d("GGG", "checkTags ${result.body()}")

            isLoadingTags = true
            if (result.isSuccessful) {
                tagsList = networkUseCase.getTagsUseCase.invoke().body()!!
                isLoadingTags = false
            }
        }
    }

    fun setNewTags(newTag: TagsListItem) {
        //val oldArr = currentTags as ArrayList<TagsListItem>
        Log.d("GGG", "before ${currentTags}")
        if (currentTags.contains(newTag)) currentTags.remove(newTag)
        else currentTags.add(newTag)
        tagsSelected = currentTags.size
        //currentTags = oldArr as Array<TagsListItem>
        Log.d("GGG", "after ${currentTags}")
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