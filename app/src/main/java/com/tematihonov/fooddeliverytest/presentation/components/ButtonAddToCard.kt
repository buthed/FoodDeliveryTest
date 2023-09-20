package com.tematihonov.fooddeliverytest.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tematihonov.fooddeliverytest.presentation.ui.colors
import com.tematihonov.fooddeliverytest.presentation.ui.spacing
import com.tematihonov.fooddeliverytest.presentation.ui.theme.Typography

@Composable
fun ButtonAddToCard(priceCurrent: Int, addToCard: () -> Unit) {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 16.dp,
                spotColor = Color(0x801F1F1F),
                ambientColor = Color(0x801F1F1F)
            )

            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(MaterialTheme.spacing.small2)
            .clickable(onClick = addToCard)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "$priceCurrent ₽", style = Typography.bodySmall)
    }
}

@Composable
fun ButtonAddToCardDiscount(priceCurrent: Int, priceOld: Int, addToCard: () -> Unit) {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 16.dp,
                spotColor = Color(0x801F1F1F),
                ambientColor = Color(0x801F1F1F)
            )
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(MaterialTheme.spacing.small2)
            .clickable(onClick = addToCard)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)) {
            Text(text = "$priceCurrent ₽", style = Typography.bodySmall)
            Text(
                text = "$priceOld ₽",
                style = Typography.bodySmall,
                color = MaterialTheme.colors.textGrayDiscount,
                textDecoration = TextDecoration.LineThrough
            )
        }
    }
}

@Preview
@Composable
fun ButtonAddToCardPreview() {
    ButtonAddToCard(480) {}
}

@Preview
@Composable
fun ButtonAddToCardDiscountPreview() {
    ButtonAddToCardDiscount(480, 480) {}
}