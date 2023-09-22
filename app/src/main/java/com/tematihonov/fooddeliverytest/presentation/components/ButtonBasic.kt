package com.tematihonov.fooddeliverytest.presentation.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tematihonov.fooddeliverytest.R
import com.tematihonov.fooddeliverytest.presentation.ui.colors
import com.tematihonov.fooddeliverytest.presentation.ui.spacing
import com.tematihonov.fooddeliverytest.presentation.ui.theme.Typography

@Composable
fun ButtonBasic(buttonPurchase: () -> Unit, text: String) {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 16.dp,
                spotColor = Color(0x801F1F1F),
                ambientColor = Color(0x801F1F1F)
            )
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.mainOrange)
            .padding(
                vertical = MaterialTheme.spacing.medium,
                horizontal = MaterialTheme.spacing.medium2
            )
            .clickable(onClick = buttonPurchase),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, style = Typography.bodySmall, color = Color.White)
    }
}

@Composable
fun ButtonPurchaseWithIcon(buttonPurchase: () -> Unit, totalPrice: String) {
    Box(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium2)) {
        Box(
            modifier = Modifier
                .shadow(
                    elevation = 16.dp,
                    spotColor = Color(0x801F1F1F),
                    ambientColor = Color(0x801F1F1F)
                )
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colors.mainOrange)
                .padding(
                    vertical = MaterialTheme.spacing.medium,
                    horizontal = MaterialTheme.spacing.medium2
                )
                .fillMaxWidth()
                .clickable(onClick = buttonPurchase),
            contentAlignment = Alignment.Center
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                Image(painter = painterResource(id = R.drawable.cart), contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.White))
                Text(text = totalPrice, style = Typography.bodySmall, color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun ButtonBasicPreview() {
    ButtonBasic(buttonPurchase = {}, text = "Label")
}

@Preview
@Composable
fun ButtonPurchaseWithIconPreview() {
    ButtonPurchaseWithIcon(buttonPurchase = {}, totalPrice = "225 ₽")
}