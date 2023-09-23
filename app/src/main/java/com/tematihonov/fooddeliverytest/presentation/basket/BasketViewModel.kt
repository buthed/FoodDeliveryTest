package com.tematihonov.fooddeliverytest.presentation.basket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tematihonov.fooddeliverytest.domain.models.responceProducts.ProductsListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(

): ViewModel() {

    var totalPrice by mutableStateOf(150)
    var productsInBasket by mutableStateOf(arrayListOf<ProductsListItem>())
    var productsInBasketCount by mutableStateOf(arrayListOf<Int>())


}