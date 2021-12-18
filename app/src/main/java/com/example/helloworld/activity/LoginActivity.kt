package com.example.helloworld.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.helloworld.BaseActivity
import com.example.helloworld.MainActivity
import com.example.helloworld.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.setOnClickListener {
            var account = account.text?.toString() ?: ""
            var password = password.text?.toString() ?: ""
            if (account == "qinhouliu" && password == "123456") {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "please input right accout and password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}