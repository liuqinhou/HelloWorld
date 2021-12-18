package com.example.helloworld.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.helloworld.MainActivity

class BootCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        //TODO("BootCompleteReceiver.onReceive() is not implemented")
        Toast.makeText(context, "boot complete", Toast.LENGTH_LONG).show()
        Log.e("BootCompleteReceiver", "收到广播")

        //接收广播：系统启动完成后运行程序
        if (intent.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.e("BootCompleteReceiver", "启动应用")
            val ootStartIntent = Intent(context, MainActivity::class.java)
            ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(ootStartIntent)
            Log.e("BootCompleteReceiver", "启动完成")
        }
    }
}