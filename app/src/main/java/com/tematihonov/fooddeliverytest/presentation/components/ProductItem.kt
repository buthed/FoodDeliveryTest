package com.tematihonov.fooddeliverytest.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tematihonov.fooddeliverytest.R
import com.tematihonov.fooddeliverytest.presentation.ui.colors
import com.tematihonov.fooddeliverytest.presentation.ui.spacing
import com.tematihonov.fooddeliverytest.presentation.ui.theme.Typography

@Composable
fun ProductItem() {
    Box(
        modifier = Modifier.width(170.dp),
        contentAlignment = Alignment.TopStart
    ) {
        //TODO add icon
        Column(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.medium,
                    end = MaterialTheme.spacing.medium,
                    bottom = MaterialTheme.spacing.small2
                )
                .background(MaterialTheme.colors.backgroundCard),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.item), contentDescription = "",
                Modifier.size(170.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "Название блюда",
                    style = Typography.headlineMedium,
                    color = Color.White
                ) //TODO add
                Text(
                    text = "500 г",
                    style = Typography.headlineMedium,
                    color = Color.White
                ) //TODO add
            }
            if (true) { //TODO add
                ButtonAddToCard(480) { } //TODO add
            } else {
                ButtonAddToCardDiscount(480, 480) {} //TODO add
            }
        }
    }
}

@Preview
@Composable
fun ProductItemPreview() {
    ProductItem()
}