package com.example.myfilms.framework.ui

import android.os.SystemClock.sleep
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfilms.model.AppState

class MainViewModel (private val liveDataToObserve : MutableLiveData<Any> =
        MutableLiveData()) :
        ViewModel() {

            fun getData() : LiveData<Any> {
                getDataFromLocalSource()
                return liveDataToObserve
                        //возвращает LiveData всем, кто хочет подписаться на изменения данных
            }
// //liveData - оберточный класс над данными. MutableLiveData можно изменять, на LiveData можно только подписаться

    fun getLiveData() = liveDataToObserve
    fun getMovies() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading //liveData хранит состояние приложения
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(Any())) //об изменениях сразу узнает фрагмент, он
            //подписан на liveData
        }.start()
    }

}