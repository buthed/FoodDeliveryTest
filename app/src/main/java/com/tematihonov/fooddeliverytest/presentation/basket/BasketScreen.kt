package com.tematihonov.fooddeliverytest.presentation.basket

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tematihonov.fooddeliverytest.R
import com.tematihonov.fooddeliverytest.presentation.components.BasketTopBar
import com.tematihonov.fooddeliverytest.presentation.components.ButtonBasic
import com.tematihonov.fooddeliverytest.presentation.components.ProductBasketItem
import com.tematihonov.fooddeliverytest.presentation.ui.spacing

@Composable
fun BasketScreen(navHostController: NavHostController) {
    Column(Modifier.fillMaxSize()) {
        BasketTopBar()
        LazyColumn() {
            items(2) {
                ProductBasketItem()
            }
        }
    }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(Modifier.padding(horizontal = MaterialTheme.spacing.medium2, vertical = MaterialTheme.spacing.small2)) {
            ButtonBasic(buttonClick = {}, text = stringResource(id = R.string.order_for, 2160))
        }
    }
}


@Preview
@Composable
fun BasketScreenPreview() {
    val foodDeliveryNavigate = rememberNavController()
    BasketScreen(foodDeliveryNavigate)
}