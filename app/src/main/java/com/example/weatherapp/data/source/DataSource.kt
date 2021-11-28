package com.example.weatherapp.data.source

import com.example.weatherapp.WeatherGrid
import com.example.weatherapp.data.PmData
import com.example.weatherapp.data.WeatherData
import io.reactivex.rxjava3.core.Observable

interface DataSource {
    fun getObservablePmData(stationName: String): Observable<PmData>
    fun getObservableWeatherData(gridLocation: WeatherGrid.LatXLngY): Observable<WeatherData>
}