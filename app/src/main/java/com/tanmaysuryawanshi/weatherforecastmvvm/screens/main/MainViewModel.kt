package com.tanmaysuryawanshi.weatherforecastmvvm.screens.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tanmaysuryawanshi.weatherforecastmvvm.data.DataOrException
import com.tanmaysuryawanshi.weatherforecastmvvm.model.City
import com.tanmaysuryawanshi.weatherforecastmvvm.model.Weather
import com.tanmaysuryawanshi.weatherforecastmvvm.model.WeatherObject
import com.tanmaysuryawanshi.weatherforecastmvvm.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository)
    :ViewModel() {
    suspend fun getWeatherData(city: String):
            DataOrException<Weather, Boolean, Exception> {
       return repository.getWeather(cityQuery = city)
    }
}




