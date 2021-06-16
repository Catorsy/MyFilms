package com.example.myfilms.framework.ui

import android.os.SystemClock.sleep
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel (private val liveDataToObserve : MutableLiveData<Any> =
        MutableLiveData()) :
        ViewModel() {

            fun getData() : LiveData<Any> {
                getDataFromLocalSource()
                return liveDataToObserve
                        //возвращает LiveData всем, кто хочет подписаться на изменения данных
            }
// //liveData - оберточный класс над данными. MutableLiveData можно изменять, на LiveData можно только подписаться

    private fun getDataFromLocalSource() {
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(Any()) //postValue - обновление данных из рабочего потока, setValue из основного
        }.start()
    }

}