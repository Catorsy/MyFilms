package com.example.myfilms.experiments

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.myfilms.model.MoviesLoader

const val INTENT_FILTER = "INTENT_FILTER"
const val MOVIE_NAME_EXTRA = "MOVIE_NAME_EXTRA"
const val LOAD_EMPTY = "LOAD_EMPTY"
const val DETAILS_LOAD_RESULT_EXTRA = "LOAD RESULT"
const val MY_MOVIE = "MY_MOVIE"
const val OK = "OK"

class MoviesService(name : String = "MoviesService") : IntentService(name) {

    private val broadcastIntent = Intent(INTENT_FILTER)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onHandleIntent(intent: Intent?) {
        intent.let {
            val name = intent?.getStringExtra(MOVIE_NAME_EXTRA)
            if (intent == null) {
                putResult(LOAD_EMPTY)
            } else {
                try {
                    val movie = MoviesLoader.loadMovie(name)
                    broadcastIntent.putExtra(MY_MOVIE, movie)
                    putResult(OK)
                }catch (e : Exception) {
                    e.printStackTrace()
                    putResult(LOAD_EMPTY)
                }
            }
        }
    }

    private fun putResult(result: String) {
        broadcastIntent.putExtra(DETAILS_LOAD_RESULT_EXTRA, result)
    }


}