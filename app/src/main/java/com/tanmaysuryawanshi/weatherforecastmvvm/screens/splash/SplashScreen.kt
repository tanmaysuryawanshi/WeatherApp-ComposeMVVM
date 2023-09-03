package com.tanmaysuryawanshi.weatherforecastmvvm.screens.splash

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.tanmaysuryawanshi.weatherforecastmvvm.navigation.WeatherScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasQuadrantSize = size / 2F

        drawCircle( color = Color.Blue,
        center = center)
    }

  navController.navigate(WeatherScreens.MainScreen.route+"/Pune")
}