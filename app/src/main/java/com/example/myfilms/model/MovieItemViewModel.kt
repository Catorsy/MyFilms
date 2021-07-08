package com.example.myfilms.model

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfilms.model.repository.Repository
import com.example.myfilms.viewModel.AppState
import kotlinx.coroutines.*

class MovieItemViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver,
    CoroutineScope by MainScope() {
    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun loadData(id: Int) {
        liveDataToObserve.value = AppState.Loading

        launch {
            val job = async(Dispatchers.IO) {
                val data = repository.getMoviesFromServer(id)
                repository.saveEntity(data) //отправляем в нашу БД
                data //вернем
            }
            liveDataToObserve.value = AppState.Success(listOf(job.await()))
        }
    }
}