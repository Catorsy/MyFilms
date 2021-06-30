package com.example.myfilms.model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.myfilms.BuildConfig
import com.example.myfilms.model.rest_entities.MoviesDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

object MoviesLoader {

    private const val MOVIES_KEY = "697bdb3bdc1a9dfcf325c28b417a9ba6"

    //если не айпи 24, то вызываем метод для старых версий
    private fun getLinesForOld(reader: BufferedReader) : String {
        val rawData = StringBuilder(1024)
        var tempVariable: String?
        while (reader.readLine().also {tempVariable = it} != null) {
            rawData.append(tempVariable).append("/n") //читаем данные построчно
        }
        reader.close()
        return rawData.toString()
    }

    //парсит наш текст и дает ответ
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    //метод, который будет грузить наши данные
    @RequiresApi(Build.VERSION_CODES.N)
    fun loadMovie(name: String) : MoviesDTO? {
        try {
            val uri = URL("https://api.themoviedb.org/3/movie/${name}?api_key=$MOVIES_KEY&language=ru-RU") //какие параметры передаем,
            //такие приходят на вход в наш метод
            lateinit var urlConnection: HttpsURLConnection
            try {
                urlConnection = uri.openConnection() as HttpsURLConnection //открываем коннекшн как Https
                urlConnection.requestMethod = "GET" //прписываем метод гет
                urlConnection.readTimeout = 10000
                val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream)) //откроем коннект урл-у. Именно тут начнем
                //получать данные.
                val lines = if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    getLinesForOld(bufferedReader)
                } else {
                    getLines(bufferedReader)
                }
                //передадим текст и класс, который хотим преобразовать
                return Gson().fromJson(lines, MoviesDTO::class.java)

            } catch (e: Exception) {
                Log.e("", "Fail connection", e)
                e.printStackTrace()
            } finally {
                urlConnection.disconnect() //не забываем разорвать соединение
            }

        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
        }
        return null
    }
}