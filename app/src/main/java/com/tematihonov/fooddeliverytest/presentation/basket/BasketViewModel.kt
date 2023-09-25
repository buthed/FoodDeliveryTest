package com.tematihonov.fooddeliverytest.presentation.basket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tematihonov.fooddeliverytest.domain.models.responceProducts.ProductsListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor() : ViewModel() {

    var totalPrice by mutableStateOf(0)
    var productsInBasket by mutableStateOf(arrayListOf<ProductsListItem>())
    var productsInBasketCount by mutableStateOf(arrayListOf<Int>())

    fun addNewProduct(product: ProductsListItem) {
        when (productsInBasket.contains(product)) {
            true -> plusMinusCount(product, true)
            false -> {
                productsInBasket.add(product)
                productsInBasketCount.add(1)
                calculateTotalPrice()
            }
        }
    }

    private fun calculateTotalPrice() {
        totalPrice = 0
        for (i in 0 until productsInBasket.size) {
            totalPrice += productsInBasket[i].price_current * productsInBasketCount[i]
        }
    }

    fun detectProductCount(product: ProductsListItem): Int {
        if (productsInBasket.indexOf(product) == -1) return 0
        return productsInBasketCount[productsInBasket.indexOf(product)]
    }

    fun detectProductOwn(product: ProductsListItem): Boolean {
        return productsInBasket.contains(product)
    }

    fun plusMinusCount(product: ProductsListItem, plusCount: Boolean) {
        when (plusCount) {
            true -> {
                productsInBasketCount[productsInBasket.indexOf(product)] += 1
            }

            false -> {
                when (productsInBasketCount[productsInBasket.indexOf(product)] <= 1) {
                    true -> {
                        val index = productsInBasket.indexOf(product)
                        productsInBasketCount.removeAt(index)
                        productsInBasket.removeAt(index)
                    }

                    false -> {
                        productsInBasketCount[productsInBasket.indexOf(product)] -= 1
                    }
                }
            }
        }
        calculateTotalPrice()
    }
}