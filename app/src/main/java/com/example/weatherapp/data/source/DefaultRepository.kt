package com.example.weatherapp.data.source

import com.example.weatherapp.util.WeatherGrid
import com.example.weatherapp.data.PmData
import com.example.weatherapp.data.WeatherData
import com.example.weatherapp.data.source.remote.RemoteDataSource
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: DataSource
): Repository {

    override fun getObservablePmData(
        stationName: String
    ): Observable<PmData> {
        try {
            return remoteDataSource.getObservablePmData(stationName)
        } catch (exception: Exception) {
            throw Exception(exception)
        }
    }

    override fun getObservableWeatherData(gridLocation: WeatherGrid.LatXLngY): Observable<WeatherData> {
        try {
            return remoteDataSource.getObservableWeatherData(gridLocation)
        } catch (exception: Exception) {
            throw Exception(exception)
        }
    }
}