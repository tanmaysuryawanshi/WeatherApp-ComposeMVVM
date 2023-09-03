package com.tanmaysuryawanshi.weatherforecastmvvm.repository

import com.tanmaysuryawanshi.weatherforecastmvvm.data.DataOrException
import com.tanmaysuryawanshi.weatherforecastmvvm.model.Weather
import com.tanmaysuryawanshi.weatherforecastmvvm.model.WeatherObject
import com.tanmaysuryawanshi.weatherforecastmvvm.network.WeatherApi
import retrofit2.http.Query
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi){

    suspend fun getWeather(cityQuery: String):DataOrException<Weather, Boolean, Exception>{
        val response=try {
api.getWeather(query = cityQuery)
        }catch (e:Exception){
            return DataOrException(e=e)
        }
return DataOrException(data = response)
    }

}