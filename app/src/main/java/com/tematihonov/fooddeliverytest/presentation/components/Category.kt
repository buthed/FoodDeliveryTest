package com.tematihonov.fooddeliverytest.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tematihonov.fooddeliverytest.presentation.ui.colors
import com.tematihonov.fooddeliverytest.presentation.ui.spacing
import com.tematihonov.fooddeliverytest.presentation.ui.theme.Typography

@Composable
fun SelectedCategory(selectCategory: () -> Unit, category: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.mainOrange)
            .padding(vertical = MaterialTheme.spacing.small2, horizontal = MaterialTheme.spacing.medium2)
            .clickable(onClick = selectCategory),
        contentAlignment = Alignment.Center
    ) {
        Text(text = category, style = Typography.bodySmall, color = Color.White)
    }
}

@Composable
fun Category(selectCategory: () -> Unit, category: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .padding(vertical = MaterialTheme.spacing.small2, horizontal = MaterialTheme.spacing.medium2)
            .clickable(onClick = selectCategory),
        contentAlignment = Alignment.Center
    ) {
        Text(text = category, style = Typography.bodySmall)
    }
}

@Preview
@Composable
fun SelectedCategoryPreview() {
    SelectedCategory(selectCategory = {}, category = "Label")
}

@Preview
@Composable
fun CategoryPreview() {
    Category(selectCategory = {}, category = "Label")
}