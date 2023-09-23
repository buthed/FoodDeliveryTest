package com.tematihonov.fooddeliverytest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tematihonov.fooddeliverytest.presentation.basket.BasketScreen
import com.tematihonov.fooddeliverytest.presentation.catalog.CatalogScreen

@Composable
fun FoodDeliveryNavHost(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "CatalogScreen") {
        composable("CatalogScreen") { CatalogScreen(navHostController) }
        composable("BasketScreen") { BasketScreen(navHostController) }
    }
}