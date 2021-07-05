package com.example.myfilms.model.rest.rest_entities

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.myfilms.model.rest.rest_entities.MoviesApi

object MoviesRepo {
    //ссылка на наш апи
    val api : MoviesApi by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(ApiUtils.baseUrl)
            .addConverterFactory(GsonConverterFactory.create()) //это то, что подключилось 2й зависимостью
            .client(ApiUtils.getOkHTTPBuilderWithHeaders()) //клиент опционально, можно и не подключать
            .build()
        adapter.create(MoviesApi::class.java) //подключили наш интерфейс с нашим запросом к нашему адаптеру
    }
}