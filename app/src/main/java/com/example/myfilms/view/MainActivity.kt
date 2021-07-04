package com.example.myfilms.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfilms.R

//С сервисом нормально не успела.

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}
