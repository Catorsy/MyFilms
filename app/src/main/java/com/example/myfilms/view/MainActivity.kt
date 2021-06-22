package com.example.myfilms.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfilms.R
import com.example.myfilms.model.MovieItemFragment
import com.example.myfilms.view.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    //.replace(R.id.container, MovieItemFragment.newInstance())
                    .commitNow()
        }
    }
}
