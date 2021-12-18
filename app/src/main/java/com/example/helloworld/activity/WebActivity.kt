package com.example.helloworld.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.helloworld.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.weblearnBtn
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        webviewBtn.setOnClickListener {
            var intent : Intent = Intent(this, WebViewActivity::class.java )
            startActivity(intent)
        }

        httpBtn.setOnClickListener {
            var intent : Intent = Intent(this, HttpLearnActivity::class.java )
            startActivity(intent)
        }

        pullparsexml.setOnClickListener {
            var intent : Intent = Intent(this, PullParseXmlActivity::class.java )
            startActivity(intent)
        }
    }
}