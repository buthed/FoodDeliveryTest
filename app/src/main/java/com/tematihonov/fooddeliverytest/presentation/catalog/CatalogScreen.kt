package com.tematihonov.fooddeliverytest.presentation.catalog

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.tematihonov.fooddeliverytest.presentation.components.ButtonPurchaseWithIcon
import com.tematihonov.fooddeliverytest.presentation.components.Category
import com.tematihonov.fooddeliverytest.presentation.components.CustomAppBar
import com.tematihonov.fooddeliverytest.presentation.components.ProductCatalogItem
import com.tematihonov.fooddeliverytest.presentation.components.SelectedCategory
import com.tematihonov.fooddeliverytest.presentation.components.SplashScreen
import com.tematihonov.fooddeliverytest.presentation.ui.spacing

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CatalogScreen() {
    val viewModel = hiltViewModel<CatalogViewModel>()

    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()

    ) {
        CustomAppBar({},{})

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.medium2,
                    vertical = MaterialTheme.spacing.extraSmall
                )
        ) {
            items(viewModel.catalogCategories) {
                if (it.id == viewModel.currentCategory) SelectedCategory(selectCategory = { viewModel.selectNewCategory(it.id) }, category = it.name) //TODO add selector
                else Category(selectCategory = { viewModel.selectNewCategory(it.id) }, category = it.name)
            }
        }
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium2)
            .weight(1f, fill = false),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
        ) {
            items(viewModel.productsList) {
                ProductCatalogItem(it) //TODO add class
            }
        }
        ButtonPurchaseWithIcon(buttonPurchase = {}, totalPrice = 225) //TODO add del fix?
    }
    
    AnimatedVisibility(visible = viewModel.isLoadingCategories,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        SplashScreen()
    }

    LaunchedEffect(true) {
        viewModel.checkCategories()
    }
}