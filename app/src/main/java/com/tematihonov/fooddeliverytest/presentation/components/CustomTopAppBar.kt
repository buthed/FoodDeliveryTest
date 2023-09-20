package com.tematihonov.fooddeliverytest.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tematihonov.fooddeliverytest.R
import com.tematihonov.fooddeliverytest.presentation.ui.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    onFilterClicked: () -> Unit,
    onSearchClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth().padding(horizontal = MaterialTheme.spacing.extraSmall,),
        contentAlignment = Alignment.BottomCenter
    ) {
        TopAppBar(
            modifier = Modifier.padding(top = 24.dp),
            title = {
                Text(
                    text = "AppBar",
                    color = Color.White
                )
            },
            navigationIcon = {
                Image(
                    painter = painterResource(id = R.drawable.filter), contentDescription = "",
                    Modifier
                        .size(44.dp).padding(MaterialTheme.spacing.extraSmall).clickable(onClick = onFilterClicked))
            },
            actions =  {
                IconButton(onClick = onSearchClicked) {
                    Image(
                        painter = painterResource(id = R.drawable.search), contentDescription = "",
                        Modifier
                            .size(44.dp).padding(MaterialTheme.spacing.extraSmall))
                }
            }
        )
        Image(
            painter = painterResource(id = R.drawable.logo), contentDescription = "",
            Modifier.height(44.dp), contentScale = ContentScale.FillHeight
        )
    }
}

@Preview
@Composable
fun CustomAppBarPreview() {
    CustomAppBar(
        onFilterClicked = {},
        onSearchClicked = {},
    )
}