package com.tematihonov.fooddeliverytest.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tematihonov.fooddeliverytest.R

@Composable
fun ProductTag(tag: Int) {
    if (tag == 2 || tag == 4 || tag == 5) {
        Box(modifier = Modifier
                .size(34.dp)
                .padding(5.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(
                    id = when (tag) {
                        2 -> R.drawable.type_3
                        4 -> R.drawable.type_2
                        5 -> R.drawable.type_1
                        else -> R.drawable.type_1
                    }
                ), contentDescription = "",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}