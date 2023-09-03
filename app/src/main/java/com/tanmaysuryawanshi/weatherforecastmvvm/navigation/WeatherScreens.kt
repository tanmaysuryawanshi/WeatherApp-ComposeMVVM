package com.tanmaysuryawanshi.weatherforecastmvvm.navigation

sealed class WeatherScreens(val route:String) {

    object SplashScreen:WeatherScreens("spash_screen")
    object MainScreen:WeatherScreens("main_screen")
    object AboutScreen:WeatherScreens("about_screen")
    object FavoriteScreen:WeatherScreens("favorite_screen")
    object SearchScreen:WeatherScreens("search_screen")
    object SettingsScreen:WeatherScreens("setting_screen")

}