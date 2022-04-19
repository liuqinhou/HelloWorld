package com.example.helloworld.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.helloworld.server.HttpService
import java.lang.Exception

class NanoHttpservice : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val httpService = HttpService(8080)
        try {
            httpService.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return super.onStartCommand(intent, flags, startId)
    }
}