package com.example.helloworld.activity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.helloworld.R
import kotlinx.android.synthetic.main.activity_http_learn.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class HttpLearnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_learn)

        var cardView:CardView

        send_Request.setOnClickListener {
            thread {
                val url = URL("https://www.baidu.com")
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.connectTimeout = 8000
                httpURLConnection.readTimeout = 8000
                val input = httpURLConnection.inputStream
                val bufferedReader = BufferedReader(InputStreamReader(input))
                val response = StringBuilder()
                bufferedReader.use {
                    bufferedReader.forEachLine {
                        response.append(it).append("\n")
                    }
                }
                runOnUiThread {
                    responseText.setText(response)
                }
            }

        }
    }
}