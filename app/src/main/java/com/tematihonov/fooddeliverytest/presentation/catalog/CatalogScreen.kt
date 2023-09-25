package com.tematihonov.fooddeliverytest.presentation.catalog

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tematihonov.fooddeliverytest.R
import com.tematihonov.fooddeliverytest.presentation.basket.BasketScreen
import com.tematihonov.fooddeliverytest.presentation.basket.BasketViewModel
import com.tematihonov.fooddeliverytest.presentation.components.BottomShadow
import com.tematihonov.fooddeliverytest.presentation.components.BottomSheet
import com.tematihonov.fooddeliverytest.presentation.components.ButtonPurchaseWithIcon
import com.tematihonov.fooddeliverytest.presentation.components.Category
import com.tematihonov.fooddeliverytest.presentation.components.CustomAppBar
import com.tematihonov.fooddeliverytest.presentation.components.InformationScreen
import com.tematihonov.fooddeliverytest.presentation.components.ProductCatalogItem
import com.tematihonov.fooddeliverytest.presentation.components.ProductItem
import com.tematihonov.fooddeliverytest.presentation.components.SelectedCategory
import com.tematihonov.fooddeliverytest.presentation.components.SplashScreen
import com.tematihonov.fooddeliverytest.presentation.ui.colors
import com.tematihonov.fooddeliverytest.presentation.ui.spacing
import com.tematihonov.fooddeliverytest.utils.BackHandler
import com.tematihonov.fooddeliverytest.utils.SearchAppBarState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CatalogScreen() {
    val viewModel = hiltViewModel<CatalogViewModel>()
    val basketViewModel = hiltViewModel<BasketViewModel>()

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    
    val coroutine = rememberCoroutineScope()

    // Delete BackPress from first screen
    BackHandler(onBack = { })
    
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheet(tagsList = viewModel.tagsList) {
                coroutine.launch {
                    sheetState.collapse()
                    viewModel.checkProducts(viewModel.currentCategory)
                }
            }
        },
        sheetPeekHeight = 0.dp,
        topBar = { CustomAppBar(
            onFilterClicked = { coroutine.launch { sheetState.expand()} },
            catalogViewModel = viewModel,
            searchAppBarState = viewModel.searchAppBarState)
        },
    ) {
        when(viewModel.searchAppBarState) {
            SearchAppBarState.CLOSED -> {
                Column(
                    Modifier
                        .background(Color.White)
                        .fillMaxSize()
                ) {
                    BottomShadow()
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = MaterialTheme.spacing.medium2,
                                vertical = MaterialTheme.spacing.extraSmall
                            )
                    ) {
                        items(viewModel.catalogCategories) {
                            if (it.id == viewModel.currentCategory) SelectedCategory(selectCategory = {
                                viewModel.selectNewCategory(
                                    it.id
                                )
                            }, category = it.name)
                            else Category(
                                selectCategory = { viewModel.selectNewCategory(it.id) },
                                category = it.name
                            )
                        }
                    }
                    if (viewModel.isLoadingProducts) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(500.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(100.dp),
                                color = MaterialTheme.colors.mainOrange
                            )
                        }
                    } else {
                        when (viewModel.productsList.isEmpty()) {
                            true -> InformationScreen(stringResource(id = R.string.there_are_no_such_dishes))
                            false -> {
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(2), modifier = Modifier
                                        .fillMaxSize()
                                        .padding(MaterialTheme.spacing.medium2)
                                        .weight(1f, fill = false),
                                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
                                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
                                ) {
                                    items(viewModel.productsList) {
                                        ProductCatalogItem(productsListItem = it) { viewModel.selectNewProduct(it) }
                                    }
                                }
                                Log.d("GGG", "Catalog screen update List")
                            }
                        }
                    }
                    if (basketViewModel.totalPrice != 0) {
                        Box(Modifier.padding(bottom = MaterialTheme.spacing.small2)) {
                            ButtonPurchaseWithIcon(
                                buttonPurchase = { viewModel.basketScreenVisibility = true },
                                totalPrice = "${basketViewModel.totalPrice / 100} ₽"
                            )
                        }
                    }
                }
            }
            SearchAppBarState.OPENED -> {
                Column {
                    BottomShadow()
                    when (viewModel.isLoadingProducts) {
                        true -> {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(500.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(100.dp),
                                    color = MaterialTheme.colors.mainOrange
                                )
                            }
                        }
                        false -> {
                            when (viewModel.productsList.isEmpty()) {
                                true -> {
                                    when (viewModel.searchStartWriting) {
                                        true -> InformationScreen(stringResource(id = R.string.nothing_was_found))
                                        false -> InformationScreen(stringResource(id = R.string.enter_name_of_dish))
                                    }
                                }
                                false -> {
                                    LazyVerticalGrid(
                                        columns = GridCells.Fixed(2), modifier = Modifier
                                            .fillMaxSize()
                                            .padding(MaterialTheme.spacing.medium2),
                                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
                                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
                                    ) {
                                        items(viewModel.productsList) {
                                            ProductCatalogItem(productsListItem = it) { viewModel.selectNewProduct(it) }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    AnimatedVisibility(visible = viewModel.isLoadingCategories,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        SplashScreen()
    }

    AnimatedVisibility(visible = viewModel.currentProductSelected,
        enter = slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }),
        exit = slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth })
    ) {
        ProductItem(currentProduct = viewModel.currentProduct,
            backClick = { viewModel.selectNewProduct(null) },
            purchaseClick = {
                basketViewModel.addNewProduct(viewModel.currentProduct!!)
                viewModel.selectNewProduct(null)
            })
    }
    
    AnimatedVisibility(viewModel.basketScreenVisibility) {
        BasketScreen(viewModel)
    }
}

@Composable
fun BackPressHandler(onBackPressed: Any) {

}
