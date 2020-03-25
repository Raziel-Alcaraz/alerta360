package com.example.alerta360

import android.content.Intent
import androidx.core.app.JobIntentService

class jobconexiones: JobIntentService() {


    override fun onHandleWork(intent: Intent) {
        val dataString = intent?.dataString
        println(dataString)
    }
}