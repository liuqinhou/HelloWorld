package com.example.helloworld

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.helloworld.activity.LoginActivity
import com.example.helloworld.utils.ActivityCollector

open class BaseActivity :AppCompatActivity() {
    lateinit var receiver: BroadcastReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("qinhouliu", javaClass.simpleName)
        ActivityCollector.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    override fun onResume() {
        super.onResume()
       // var intent = Intent("com.example.helloworld.LOGINOUT")
        var intentFilter = IntentFilter("com.example.helloworld.LOGINOUT")
        receiver = LoginOutBroadCastRecriver()
        registerReceiver(receiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
       // unregisterReceiver(receiver)
    }

    inner class LoginOutBroadCastRecriver: BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
           // TODO("Not yet implemented")
            Log.e("qinhouliu", "force logout now")
            AlertDialog.Builder(context).apply {
                setTitle("Warning")
                setMessage("you are force to be offline, please try to login again!")
                setCancelable(false)
                setPositiveButton("OK") { _, _ ->
                    ActivityCollector.finishAll()
                    var intent = Intent(context, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
                show()
            }

        }

    }
}