package com.example.myfilms.model

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfilms.model.repository.Repository
import com.example.myfilms.viewModel.AppState

class MovieItemViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

            //грузим наши данные
    fun loadData (name: String) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            val data = repository.getMoviesFromServer(name)
            liveDataToObserve.postValue(AppState.Success(listOf(data))) //postValue - синхронизация с потоком ui,
            //туда постим нашу дату
        }.start()
    }
}