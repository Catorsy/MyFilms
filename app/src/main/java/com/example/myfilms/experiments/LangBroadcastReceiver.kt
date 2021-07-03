package com.example.myfilms.experiments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.lang.StringBuilder

class LangBroadcastReceiver : BroadcastReceiver () {
    override fun onReceive(context: Context?, intent: Intent?) {
        StringBuilder().apply {
            Toast.makeText(context, "Ооой! Кто-то сменил язык!", Toast.LENGTH_LONG).show()
        }
    }
}