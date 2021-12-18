package com.example.helloworld.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.helloworld.R
import kotlinx.android.synthetic.main.activity_http_learn.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Request.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import java.lang.Exception
import kotlin.concurrent.thread

class PullParseXmlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_parse_xml)

        send_Request.setOnClickListener {
            thread {
                try {
                    //OKHttpClient()
                    val okHttpClient = OkHttpClient()
                    val request = Request.Builder().url("http://127.0.0.1/get_date.xml")
                        .build()
                    val response = okHttpClient.newCall(request).execute()
                    val responseData = response.body?.string()
                    if (responseData != null) {
                        parseXmlWithPull(responseData)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun parseXmlWithPull(xmlData: String) {
        val factory = XmlPullParserFactory.newInstance()
        val xmlPullParser = factory.newPullParser()
        xmlPullParser.setInput(StringReader(xmlData))
        var eventType = xmlPullParser.eventType
        var id = ""
        var name = ""
        var version = ""
        while (eventType != XmlPullParser.END_DOCUMENT) {
            val nodeName = xmlPullParser.name
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    when (nodeName) {
                        "id" -> id = xmlPullParser.nextText()
                        "name" -> name = xmlPullParser.nextText()
                        "version" -> version = xmlPullParser.nextText()
                    }
                }
                XmlPullParser.END_TAG -> {
                    if ("app" == nodeName) {
                        Log.d("MainActivity", "id is $id")
                        Log.d("MainActivity", "name is $name")
                        Log.d("MainActivity", "version is $version")
                    }
                }
            }
            eventType = xmlPullParser.next()
        }
    }
}