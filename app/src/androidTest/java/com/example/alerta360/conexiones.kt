package com.example.alerta360

import android.app.IntentService
import android.content.Intent

class conexiones: IntentService(conexiones::class.simpleName) {
    override fun onHandleIntent(intent: Intent?) {
       val dataString = intent?.dataString
    }

}