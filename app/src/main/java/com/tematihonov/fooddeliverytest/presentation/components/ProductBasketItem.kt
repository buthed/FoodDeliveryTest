package com.tematihonov.fooddeliverytest.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.tematihonov.fooddeliverytest.R
import com.tematihonov.fooddeliverytest.domain.models.responceProducts.ProductsListItem
import com.tematihonov.fooddeliverytest.presentation.basket.BasketViewModel
import com.tematihonov.fooddeliverytest.presentation.ui.colors
import com.tematihonov.fooddeliverytest.presentation.ui.spacing
import com.tematihonov.fooddeliverytest.presentation.ui.theme.Typography

@Composable
fun ProductBasketItem(productsListItem: ProductsListItem, basketViewModel: BasketViewModel) {
    var basketListContainsProduct by remember { mutableStateOf(basketViewModel.detectProductOwn(productsListItem)) }

    when (basketListContainsProduct) {
        true -> {
            Box(
                Modifier
                    .background(Color.White)
                    .height(130.dp)
                    .fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
                Row(
                    Modifier
                        .padding(MaterialTheme.spacing.medium2)
                        .background(Color.White)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(modifier = Modifier.height(96.dp),
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium2)
                    ) {
                        Image(painter = painterResource(id = R.drawable.item), contentDescription = "",
                            modifier = Modifier.size(96.dp))
                        when (basketListContainsProduct && basketViewModel.detectProductCount(productsListItem) != 0) {
                            true -> {
                                Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.height(96.dp)) {
                                    Text(text = productsListItem.name, style = Typography.headlineMedium)
                                    var currentCount by remember { mutableStateOf(basketViewModel.detectProductCount(productsListItem)) }
                                    ItemCardCounter(
                                        currentCount = currentCount,
                                        minusCount = { basketViewModel.plusMinusCount(productsListItem, false)
                                            basketListContainsProduct = basketViewModel.detectProductOwn(productsListItem)
                                            if (currentCount >= 2) currentCount = basketViewModel.detectProductCount(productsListItem) },
                                        plusCount = { basketViewModel.plusMinusCount(productsListItem, true)
                                            currentCount = basketViewModel.detectProductCount(productsListItem) },
                                    )
                                }
                            }
                            false -> {}
                        }

                    }
                    Column(Modifier.width(100.dp), horizontalAlignment = Alignment.End) {
                        Text(text = "${productsListItem.price_current/100} ₽", style = Typography.bodySmall)
                        if (productsListItem.price_old != 0) {
                            Text(
                                text = "${productsListItem.price_old/100} ₽",
                                style = Typography.bodySmall,
                                color = MaterialTheme.colors.textGrayDiscount,
                                textDecoration = TextDecoration.LineThrough
                            )
                        }
                    }
                }
                Divider(thickness = 1.dp, color = MaterialTheme.colors.textGrayDiscount)
            }
        }
        false -> {}
    }
}
