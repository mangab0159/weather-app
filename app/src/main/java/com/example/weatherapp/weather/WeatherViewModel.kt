package com.example.weatherapp.weather

import androidx.lifecycle.*
import com.example.weatherapp.data.PmData
import com.example.weatherapp.data.WeatherData
import com.example.weatherapp.data.source.DefaultRepository
import com.example.weatherapp.data.source.Repository
import com.example.weatherapp.util.WeatherGrid
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : ViewModel() {

    val weatherData: LiveData<WeatherData> by lazy { _weatherData }
    private val _weatherData: MutableLiveData<WeatherData> = MutableLiveData()

    val pmData: LiveData<PmData> by lazy { _pmData }
    private val _pmData: MutableLiveData<PmData> = MutableLiveData()

    val weatherText: LiveData<String> = Transformations.map(_weatherData) {
        if (it.curPty == "없음") it.curSky else it.curPty
    }

    fun refreshWeatherData(gridLocation: WeatherGrid.LatXLngY, stationName: String) {
        try {
            // loading flag live data
            repository.getObservablePmData(stationName).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe{
                _pmData.value = it
            }
            repository.getObservableWeatherData(gridLocation).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe{
                _weatherData.value = it
            }
        } catch (exception: Exception) {
            // to set exception flag live data  false
        }
    }
}