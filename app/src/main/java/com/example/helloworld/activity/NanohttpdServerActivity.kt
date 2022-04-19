package com.example.helloworld.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.helloworld.R
import com.example.helloworld.server.HttpService
import com.example.helloworld.service.NanoHttpservice
import java.io.IOException


class NanohttpdServerActivity : AppCompatActivity() {

    var http = HttpService(9988)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nanohttpd_server)
        val intent = Intent(this, NanoHttpservice::class.java)
        startService(intent)
//        System.out.println("服务启动中");
//        try {
//            http.start();
//            System.out.println("服务启动完成");
//        } catch (e: IOException) {
//            e.printStackTrace();
//            System.out.println("服务启动错误");
//        }

    }

    override fun onDestroy() {
        super.onDestroy()
       // http.stop()
    }
}