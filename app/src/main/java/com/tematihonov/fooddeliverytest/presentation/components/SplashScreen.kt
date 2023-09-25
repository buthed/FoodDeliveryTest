package com.tematihonov.fooddeliverytest.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.tematihonov.fooddeliverytest.R
import com.tematihonov.fooddeliverytest.presentation.ui.colors

@Composable
fun SplashScreen() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.splash_screen_animation))
    Box(Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.mainOrange)) {
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
    }
}