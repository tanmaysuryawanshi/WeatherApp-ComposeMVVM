package com.tanmaysuryawanshi.weatherforecastmvvm.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tanmaysuryawanshi.weatherforecastmvvm.screens.main.MainScreen
import com.tanmaysuryawanshi.weatherforecastmvvm.screens.main.MainViewModel
import com.tanmaysuryawanshi.weatherforecastmvvm.screens.search.SearchScreen
import com.tanmaysuryawanshi.weatherforecastmvvm.screens.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController= rememberNavController()

    NavHost(navController=navController,
        startDestination = WeatherScreens.SplashScreen.route){
     composable(WeatherScreens.SplashScreen.route){
         SplashScreen(navController)
     }
        composable(WeatherScreens.SearchScreen.route){
            SearchScreen(navController)
        }
        composable(WeatherScreens.MainScreen.route+"/{city}",
            arguments = listOf(
                navArgument("city"){
                    type= NavType.StringType
                }
            )
        ){
            it->
            it.arguments?.getString("city").let {city->
                val mainViewModel= hiltViewModel<MainViewModel>()
                MainScreen(navController = navController,mainViewModel,city=city)
            }

        }

    }



    
}