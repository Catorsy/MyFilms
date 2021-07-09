package com.example.myfilms.framework.ui.History

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfilms.model.repository.Repository
import com.example.myfilms.viewModel.AppState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repository: Repository
): ViewModel(), CoroutineScope by MainScope() {
    val historyLiveData: MutableLiveData<AppState> = MutableLiveData()

    fun getAllHistory() {
        historyLiveData.value = AppState.Loading
        launch(Dispatchers.IO) {
            historyLiveData.postValue(AppState.Success(repository.getAllHistory()))
        }
    }
}