package com.tanmaysuryawanshi.weatherforecastmvvm.network

import com.tanmaysuryawanshi.weatherforecastmvvm.model.Weather
import com.tanmaysuryawanshi.weatherforecastmvvm.model.WeatherItem
import com.tanmaysuryawanshi.weatherforecastmvvm.model.WeatherObject
import com.tanmaysuryawanshi.weatherforecastmvvm.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units" )units:String="imperial",
        @Query("appid") appid:String = Constants.Api_Key
    ): Weather

}