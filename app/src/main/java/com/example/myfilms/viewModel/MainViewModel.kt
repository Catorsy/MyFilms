package com.example.myfilms.viewModel

import android.os.SystemClock.sleep
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfilms.model.repository.Repository
import kotlinx.coroutines.*

class MainViewModel(private val repository: Repository) :
        ViewModel(), LifecycleObserver, CoroutineScope by MainScope() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getData(): LiveData<AppState> {
        getDataFromLocalSource()
        return liveDataToObserve
        //возвращает LiveData всем, кто хочет подписаться на изменения данных
    }
// //liveData - оберточный класс над данными. MutableLiveData можно изменять, на LiveData можно только подписаться

    fun getLiveData() = liveDataToObserve
    fun getMoviesFromLocalSource() = getDataFromLocalSource()
    fun getMoviesFromNetSource() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading //liveData хранит состояние приложения
        launch {
            delay(1000)
            liveDataToObserve.value = AppState.Success(repository.getMoviesFromLocalStorage())
        }

//        Thread {
//            sleep(1000)
//            liveDataToObserve.postValue(AppState.Success(repository.getMoviesFromLocalStorage())) //об изменениях сразу узнает фрагмент, он
//            //подписан на liveData
//        }.start()
    }
}