package com.tematihonov.fooddeliverytest.presentation.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.tematihonov.fooddeliverytest.R
import com.tematihonov.fooddeliverytest.presentation.catalog.CatalogViewModel
import com.tematihonov.fooddeliverytest.presentation.components.BasketTopBar
import com.tematihonov.fooddeliverytest.presentation.components.BottomShadow
import com.tematihonov.fooddeliverytest.presentation.components.ButtonBasic
import com.tematihonov.fooddeliverytest.presentation.components.InformationScreen
import com.tematihonov.fooddeliverytest.presentation.components.ProductBasketItem
import com.tematihonov.fooddeliverytest.presentation.ui.spacing
import com.tematihonov.fooddeliverytest.utils.BackHandler

@Composable
fun BasketScreen(viewModel: CatalogViewModel) {
    val basketViewModel = hiltViewModel<BasketViewModel>()

    // Delete BackPress from first screen
    BackHandler(onBack = { viewModel.basketScreenVisibility = false })

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BasketTopBar { viewModel.basketScreenVisibility = false }
        BottomShadow()
        when (basketViewModel.productsInBasket.isNotEmpty()) {
            true -> {
                LazyColumn() {
                    items(basketViewModel.productsInBasketCount.size) {
                        ProductBasketItem(basketViewModel.productsInBasket[it], basketViewModel)
                    }
                }
            }

            false -> {
                InformationScreen(infoText = stringResource(id = R.string.empty_select_dishes))
            }
        }
    }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            Modifier.padding(
                horizontal = MaterialTheme.spacing.medium2,
                vertical = MaterialTheme.spacing.small2
            )
        ) {
            ButtonBasic(
                buttonClick = {},
                text = stringResource(id = R.string.order_for, basketViewModel.totalPrice / 100)
            )
        }
    }
}