package com.tematihonov.fooddeliverytest.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tematihonov.fooddeliverytest.R
import com.tematihonov.fooddeliverytest.domain.models.responseTags.TagsListItem
import com.tematihonov.fooddeliverytest.presentation.catalog.CatalogViewModel
import com.tematihonov.fooddeliverytest.presentation.ui.colors
import com.tematihonov.fooddeliverytest.presentation.ui.theme.Typography

@Composable
fun BottomSheet(tagsList: List<TagsListItem>, readyButton: () -> Unit) {
    Box(modifier = Modifier
            .shadow(
                elevation = 8.dp,
                spotColor = Color(0x0D000000),
                ambientColor = Color(0x0D000000)
            )
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(size = 24.dp))
            .background(color = Color(0xFFFFFFFF))
            .padding(start = 24.dp, top = 32.dp, end = 24.dp, bottom = 32.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(text = stringResource(id = R.string.select_dishes), style = Typography.titleMedium)
            LazyColumn() {
                itemsIndexed(tagsList) { index, item ->
                    BottomSheetItem(item)
                    if (index < tagsList.lastIndex) Divider(
                        thickness = 1.dp,
                        color = MaterialTheme.colors.textGrayDiscount
                    )

                }
            }
            ButtonBasic(buttonClick = readyButton, text = stringResource(id = R.string.ready))
        }
    }
}

@Composable
fun BottomSheetItem(tag: TagsListItem) {
    val viewModel = hiltViewModel<CatalogViewModel>()
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(tag.name, style = Typography.bodyMedium)
        val isChecked = remember { mutableStateOf(viewModel.currentTags.contains(tag)) }
        Checkbox(checked = isChecked.value,
            onCheckedChange = {
                viewModel.setNewTags(tag)
                isChecked.value = it
            },
            modifier = Modifier.clickable { viewModel.setNewTags(tag) })
    }
}