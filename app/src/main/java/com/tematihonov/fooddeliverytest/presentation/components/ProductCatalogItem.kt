package com.tematihonov.fooddeliverytest.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tematihonov.fooddeliverytest.R
import com.tematihonov.fooddeliverytest.presentation.ui.colors
import com.tematihonov.fooddeliverytest.presentation.ui.spacing
import com.tematihonov.fooddeliverytest.presentation.ui.theme.Typography

@Composable
fun ProductCatalogItem() {
    Box(
        modifier = Modifier.width(170.dp).clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.TopStart
    ) {
        //TODO add icon
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.backgroundCard),
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
                    text = "Название блюда",
                    style = Typography.headlineMedium,
                    color = Color.Black
                ) //TODO add
                Text(
                    text = "500 г",
                    style = Typography.headlineMedium,
                    color = MaterialTheme.colors.textGray
                ) //TODO add
            }
            Box(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
                if (true) { //TODO add
                    ButtonAddToCard(480) { } //TODO add
                } else {
                    ButtonAddToCardDiscount(480, 480) {} //TODO add
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductCatalogItemPreview() {
    ProductCatalogItem()
}