package com.example.helloworld

import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
//import android.app.ActivityThread.main
import android.os.Handler
import android.os.HandlerThread
import android.os.PersistableBundle
import android.util.Log
import com.example.helloworld.activity.TestFragmentActivity
import com.example.helloworld.activity.WebActivity
import com.example.helloworld.activity.WebViewActivity
import com.example.helloworld.otherPackage.ThirdActivity


//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    val TAG = "qinhouliu"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "mainactivity oncreate")
        Log.d("qinhouliu","mainactivity --- $taskId")
        setContentView(R.layout.activity_main)
        var thread = HandlerThread("testThread")
        var looper = thread.looper

        btn1.setOnClickListener{Toast.makeText(this, "按下了按钮", Toast.LENGTH_SHORT).show()}
        btnFinish.setOnClickListener { finish() }
        btnJump.setOnClickListener {
            var intent : Intent = Intent(this, SecondActivity::class.java )
            startActivity(intent)
        }
        btnJump2_action1.setOnClickListener {
            var intent : Intent = Intent()
            intent.setAction("just for love")
            intent.addCategory("android.intent.category.qinhou")
            startActivity(intent)
        }

        btnJump2_action2.setOnClickListener {
            var intent : Intent = Intent()
            intent.setAction("just for love")
            startActivity(intent)
        }

        btnJump2_action3.setOnClickListener {
            var intent : Intent = Intent(this, ThirdActivity::class.java )
            startActivity(intent)
        }

        btnJumpForth.setOnClickListener {
            ForthActivity.actionStart(this, "liu", "qinhou")
        }

        btnJumpRecycle.setOnClickListener{
            var intent : Intent = Intent(this, TestRecycleViewActivity::class.java )
            startActivity(intent)
        }

        btnJumpFragmentActivity.setOnClickListener{
            var intent : Intent = Intent(this, TestFragmentActivity::class.java )
            startActivity(intent)
        }

        btnJumpBrower.setOnClickListener {
            var intent : Intent = Intent()
            intent.setAction(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com")
            startActivity(intent)
        }

        btnsendstandardBroadCast.setOnClickListener {
            var intent = Intent()
            intent.setAction("com.example.helloworld.broadcast")
            intent.setPackage("com.example.helloworld")
            //sendBroadcast(intent)
            sendOrderedBroadcast(intent, null)
        }

        btnforceLoginout.setOnClickListener {
            var intent = Intent("com.example.helloworld.LOGINOUT")
            intent.setPackage("com.example.helloworld")
            sendBroadcast(intent)
        }

        weblearnBtn.setOnClickListener {
            var intent : Intent = Intent(this, WebActivity::class.java )
            startActivity(intent)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.add_item -> Toast.makeText(this, "菜单 -> 增加++", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "菜单 -> 移除--", Toast.LENGTH_SHORT).show()
        }
        return false
    }

    override fun onStart() {
        Log.e(TAG, "mainactivity onstart")
        super.onStart()
    }

    override fun onRestart() {
        Log.e(TAG, "mainactivity onRestart")
        super.onRestart()
    }

    override fun onResume() {
        Log.e(TAG, "mainactivity onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.e(TAG, "mainactivity onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.e(TAG, "mainactivity onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.e(TAG, "mainactivity onDestroy")
        super.onDestroy()
    }

    override fun onNewIntent(intent: Intent?) {
        Log.e(TAG, "mainactivity onNewIntent")

        super.onNewIntent(intent)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        Log.e(TAG, "mainactivity onSaveInstanceState")

        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.e(TAG, "mainactivity onRestoreInstanceState")

        super.onRestoreInstanceState(savedInstanceState)
    }
}