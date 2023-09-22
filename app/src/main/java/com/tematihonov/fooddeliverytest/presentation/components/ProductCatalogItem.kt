package com.tematihonov.fooddeliverytest.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tematihonov.fooddeliverytest.R
import com.tematihonov.fooddeliverytest.domain.models.responceProducts.ProductsListItem
import com.tematihonov.fooddeliverytest.presentation.ui.colors
import com.tematihonov.fooddeliverytest.presentation.ui.spacing
import com.tematihonov.fooddeliverytest.presentation.ui.theme.Typography

@Composable
fun ProductCatalogItem(productsListItem: ProductsListItem, selectProduct: () -> Unit) {
    Box(
        modifier = Modifier.width(170.dp).clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.TopStart
    ) {
        //TODO add icon
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.backgroundCard)
                .clickable(onClick = selectProduct),
        ) {
            Image(
                painter = painterResource(id = R.drawable.item), contentDescription = "",
                Modifier.size(170.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(
                    start = MaterialTheme.spacing.medium,
                    end = MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.medium
                )
            ) {
                Text(
                    text = productsListItem.name, //TODO change to 1 line?
                    style = Typography.headlineMedium,
                    color = Color.Black
                ) //TODO add
                Text(
                    text = "${productsListItem.measure} ${productsListItem.measure_unit}",
                    style = Typography.headlineMedium,
                    color = MaterialTheme.colors.textGray
                ) //TODO add
            }
            Box(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
                if (productsListItem.price_old == null || productsListItem.price_old == 0) { //TODO add
                    ButtonAddToCard(productsListItem.price_current/100) { }
                } else {
                    ButtonAddToCardDiscount(productsListItem.price_current/100, productsListItem.price_old/100) {} //TODO add
                }
            }
        }
        if (productsListItem.tag_ids.isNotEmpty()) {
            ProductTag(productsListItem.tag_ids[0])
        }
    }
}