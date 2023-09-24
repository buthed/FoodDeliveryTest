package com.tematihonov.fooddeliverytest.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tematihonov.fooddeliverytest.R
import com.tematihonov.fooddeliverytest.domain.models.responceProducts.ProductsListItem
import com.tematihonov.fooddeliverytest.presentation.catalog.CatalogViewModel
import com.tematihonov.fooddeliverytest.presentation.ui.colors
import com.tematihonov.fooddeliverytest.presentation.ui.spacing
import com.tematihonov.fooddeliverytest.presentation.ui.theme.Typography
import com.tematihonov.fooddeliverytest.utils.SearchAppBarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    onFilterClicked: () -> Unit,
    catalogViewModel: CatalogViewModel,
    searchAppBarState: SearchAppBarState,
) {
    Box(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.extraSmall),
        contentAlignment = Alignment.BottomCenter
    ) {
        when(searchAppBarState) {
            SearchAppBarState.CLOSED -> {
                TopAppBar(
                    title = {
                        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "",
                                Modifier
                                    .height(44.dp)
                                    .padding(top = MaterialTheme.spacing.extraSmall),
                                contentScale = ContentScale.FillHeight
                            )
                        }
                    },
                    navigationIcon = {
                        ButtonFilter(onFilterClicked = onFilterClicked)
                    },
                    actions =  {
                        IconButton(onClick = {
                            catalogViewModel.searchAppBarState = SearchAppBarState.OPENED
                            catalogViewModel.productsList = ArrayList<ProductsListItem>()
                        }) {
                            Image(
                                painter = painterResource(id = R.drawable.search), contentDescription = "",
                                Modifier
                                    .size(24.dp))
                        }
                    },
                    modifier = Modifier.shadow(elevation = 16.dp, spotColor = Color(0x1A000000), ambientColor = Color(0x1A000000))
                )
            }
            SearchAppBarState.OPENED -> {
                var searchInput by remember { mutableStateOf("") }

                TopAppBar(
                    title = {
                        TextField(value = searchInput,
                            onValueChange = {
                                searchInput = it
                                catalogViewModel.searchProducts(searchInput) },
                            textStyle = Typography.bodyMedium,
                            placeholder = { Text(stringResource(id = R.string.find_a_dish), style = Typography.bodyMedium) },
                            singleLine = true, //TODO fix length?
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                errorIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = MaterialTheme.colors.textGray,
                                placeholderColor = MaterialTheme.colors.textGrayDiscount,
                                disabledPlaceholderColor = Color.Transparent
                            ),
                        )
                    },
                    navigationIcon = {
                        Icon(painter = painterResource(id = R.drawable.arrowleft), contentDescription = "",
                            Modifier
                                .size(24.dp)
                                .padding(1.dp)
                                .clickable {
                                    catalogViewModel.searchAppBarState = SearchAppBarState.CLOSED
                                    catalogViewModel.checkCategories()
                                }, tint = MaterialTheme.colors.mainOrange)
                    },
                    actions =  {
                        IconButton(onClick = {  }) {
                            Icon(painter = painterResource(id = R.drawable.cancel), contentDescription = "",
                                Modifier
                                    .size(24.dp)
                                    .clickable {
                                        searchInput = ""
                                        catalogViewModel.searchProducts(searchInput)
                                    })
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasketTopBar(backClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.basket), style = Typography.titleSmall,
                modifier = Modifier.padding(start = MaterialTheme.spacing.extraLarge))
        },
        navigationIcon = {
            Icon(painter = painterResource(id = R.drawable.arrowleft), contentDescription = "",
                Modifier
                    .size(24.dp)
                    .padding(1.dp)
                    .clickable(onClick = backClick),
                tint = MaterialTheme.colors.mainOrange)
        },
        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium2),
    )
}