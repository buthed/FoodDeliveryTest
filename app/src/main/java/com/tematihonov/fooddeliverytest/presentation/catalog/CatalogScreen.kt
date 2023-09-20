package com.tematihonov.fooddeliverytest.presentation.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tematihonov.fooddeliverytest.R
import com.tematihonov.fooddeliverytest.presentation.components.ButtonPurchaseWithIcon
import com.tematihonov.fooddeliverytest.presentation.components.Category
import com.tematihonov.fooddeliverytest.presentation.components.ProductCatalogItem
import com.tematihonov.fooddeliverytest.presentation.components.SelectedCategory
import com.tematihonov.fooddeliverytest.presentation.ui.spacing

@Composable
fun CatalogScreen() {
    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(top = MaterialTheme.spacing.medium2)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.extraSmall),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.filter), contentDescription = "",
                Modifier
                    .size(44.dp)
                    .padding(MaterialTheme.spacing.small)
            ) //TODO add
            Image(
                painter = painterResource(id = R.drawable.logo), contentDescription = "",
                Modifier.height(44.dp), contentScale = ContentScale.FillHeight
            )
            Image(
                painter = painterResource(id = R.drawable.search), contentDescription = "",
                Modifier
                    .size(44.dp)
                    .padding(MaterialTheme.spacing.small)
            ) //TODO add
        }
        val categoryList = listOf(
            "Роллы",
            "Суши",
            "Наборы",
            "Горячие блюда",
            "Суши",
            "Наборы"
        ) //TODO change to retrofit
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium2)
        ) {
            items(categoryList) {
                if (it == "Роллы") SelectedCategory(selectCategory = {}, category = it)
                else Category(selectCategory = {}, category = it)
            }
        }
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize().padding(MaterialTheme.spacing.medium2).weight(1f, fill = false),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
        ) {
            items(10) {
                ProductCatalogItem() //TODO add class
            }
        }
        ButtonPurchaseWithIcon(buttonPurchase = {}, totalPrice = 225) //TODO add del fix?
    }
}