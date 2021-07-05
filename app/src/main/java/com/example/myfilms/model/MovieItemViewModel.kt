package com.example.myfilms.model

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfilms.model.repository.Repository
import com.example.myfilms.viewModel.AppState
import kotlinx.coroutines.*

class MovieItemViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver, CoroutineScope by MainScope() {
    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

            //грузим наши данные
    fun loadData (id: Int) {
        liveDataToObserve.value = AppState.Loading

                launch {
                    val job = async(Dispatchers.IO) { repository.getMoviesFromServer(id) }
                    liveDataToObserve.value = AppState.Success(listOf(job.await()))
                }

//        Thread {
//            val data = repository.getMoviesFromServer(name)
//            liveDataToObserve.postValue(AppState.Success(listOf(data))) //postValue - синхронизация с потоком ui,
//            //туда постим нашу дату
//        }.start()
    }
}