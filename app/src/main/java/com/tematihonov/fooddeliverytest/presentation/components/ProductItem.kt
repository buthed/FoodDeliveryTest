package com.tematihonov.fooddeliverytest.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tematihonov.fooddeliverytest.R
import com.tematihonov.fooddeliverytest.presentation.ui.colors
import com.tematihonov.fooddeliverytest.presentation.ui.spacing
import com.tematihonov.fooddeliverytest.presentation.ui.theme.Typography

@Composable
fun ProductItem(backClick: () -> Unit) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {
        Column(Modifier.background(Color.White)) {
            Image(
                painter = painterResource(id = R.drawable.item), contentDescription = "",
                Modifier.fillMaxWidth(), contentScale = ContentScale.FillBounds
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium2)
            ) {
                Text(text = "Том Ям", style = Typography.titleLarge) //TODO add
                Text(
                    text = "Кокосовое молоко, кальмары, креветки, помидоры черри, грибы вешанки",
                    style = Typography.bodyMedium,
                    color = MaterialTheme.colors.textGrayDiscount
                ) //TODO add
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.large)) {
                Divider(thickness = 1.dp, color = MaterialTheme.colors.textGrayDiscount)
                ProductItemRow(stringResource(id = R.string.weight),"400 г") //TODO add
                ProductItemRow(stringResource(id = R.string.energy_value),"198,9 ккал") //TODO add
                ProductItemRow(stringResource(id = R.string.squirrels),"10 г") //TODO add
                ProductItemRow(stringResource(id = R.string.fats),"8,5 г") //TODO add
                ProductItemRow(stringResource(id = R.string.carbohydrates),"19,7 г") //TODO add
            }
            Box(
                Modifier.padding(
                    horizontal = MaterialTheme.spacing.medium2,
                    vertical = MaterialTheme.spacing.small2
                )
            ) {
                ButtonPurchaseWithIcon(buttonPurchase = {}, totalPrice = 225) //TODO check on all screens
            }
        }
        Box(Modifier.padding(MaterialTheme.spacing.medium2)) {
            Box(
                Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .shadow(
                        elevation = 16.dp,
                        spotColor = Color(0x801F1F1F),
                        ambientColor = Color(0x801F1F1F)
                    ), contentAlignment = Alignment.Center) {
                Image(painter = painterResource(id = R.drawable.arrowleft), contentDescription = "",
                    modifier = Modifier.size(24.dp).clickable(onClick = backClick))
            }
        }
    }
}

@Composable
private fun ProductItemRow(stringResource: String, value: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.spacing.medium2,
                vertical = MaterialTheme.spacing.small3
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource, style = Typography.bodyMedium,
            color = MaterialTheme.colors.textGrayDiscount
        )
        Text(text = value, style = Typography.bodyMedium)
    }
    Divider(thickness = 1.dp, color = MaterialTheme.colors.textGrayDiscount)
}

@Preview
@Composable
fun ProductItemPreview() {
    ProductItem {}
}