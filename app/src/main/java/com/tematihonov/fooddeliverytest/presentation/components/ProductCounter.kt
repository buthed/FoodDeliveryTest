package com.tematihonov.fooddeliverytest.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
fun ItemCardCounter(
    currentCount: Int,
    minusCount: () -> Unit,
    plusCount: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .shadow(
                    elevation = 16.dp,
                    spotColor = Color(0x801F1F1F),
                    ambientColor = Color(0x801F1F1F)
                )
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(MaterialTheme.spacing.extraSmall)
                .clickable(onClick = minusCount),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.minus), contentDescription = "",
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.mainOrange)
            )
        }
        Box(modifier = Modifier.width(44.dp), contentAlignment = Alignment.Center) {
            Text(text = "$currentCount", style = Typography.bodySmall)
        }
        Box(
            modifier = Modifier
                .shadow(
                    elevation = 16.dp,
                    spotColor = Color(0x801F1F1F),
                    ambientColor = Color(0x801F1F1F)
                )
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(MaterialTheme.spacing.extraSmall)
                .clickable(onClick = plusCount),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.plus), contentDescription = "",
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.mainOrange)
            )
        }
    }
}

@Composable
fun CartRowCounter(
    currentCount: Int,
    minusCount: () -> Unit,
    plusCount: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .shadow(
                    elevation = 16.dp,
                    spotColor = Color(0x801F1F1F),
                    ambientColor = Color(0x801F1F1F)
                )
                .clip(RoundedCornerShape(8.dp))
                .padding(MaterialTheme.spacing.extraSmall)
                .clickable(onClick = minusCount),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.minus), contentDescription = "",
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.mainOrange)
            )
        }
        Box(modifier = Modifier.width(44.dp), contentAlignment = Alignment.Center) {
            Text(text = "$currentCount", style = Typography.bodySmall)
        }
        Box(
            modifier = Modifier
                .shadow(
                    elevation = 16.dp,
                    spotColor = Color(0x801F1F1F),
                    ambientColor = Color(0x801F1F1F)
                )
                .clip(RoundedCornerShape(8.dp))
                .padding(MaterialTheme.spacing.extraSmall)
                .clickable(onClick = plusCount),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.plus), contentDescription = "",
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.mainOrange)
            )
        }
    }
}

@Preview
@Composable
fun ItemCardCounterPreview() {
    ItemCardCounter(
        currentCount = 100,
        minusCount = {},
        plusCount = {},
    )
}

@Preview
@Composable
fun CartRowCounterPreview() {
    CartRowCounter(
        currentCount = 100,
        minusCount = {},
        plusCount = {},
    )
}