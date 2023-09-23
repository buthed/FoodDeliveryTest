package com.tematihonov.fooddeliverytest.presentation.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

data class Colors(
    val backgroundWhite: Color = Color(0xFFFFFFFF),
    val backgroundCard: Color = Color(0x61F5F5F5),
    val mainOrange: Color = Color(0xFFFF5D26),
    val textGray: Color = Color(0xFF626262),
    val textGrayDiscount: Color = Color(0xFF666666),
)

val LocalColors = compositionLocalOf { Colors() }

val MaterialTheme.colors: Colors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current