package com.example.helloworld

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import com.example.helloworld.activity.CameraAlbumActivity
import com.example.helloworld.activity.PlayAudioActivity
import com.example.helloworld.activity.PlayVideoActivity
import com.example.helloworld.database.MyDatabaseHelper
import com.example.helloworld.otherPackage.ThirdActivity
import com.example.helloworld.service.MyIntentService
import com.example.helloworld.service.TestService1
import kotlinx.android.synthetic.main.activity_second.*
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter
import java.lang.Exception

//class SecondActivity : BaseActivity() {
class SecondActivity : BaseActivity() {

    val TAG = "qinhouliu"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "secondactivity onCreate")
        Log.d("qinhouliu", "secondactivity --- $taskId")
        // 打印主线程的id
        setContentView(R.layout.activity_second)


        jump.setOnClickListener {
            var intent: Intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

//        jumpMain.setOnClickListener {
//            var intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }

//        jumpMain.setOnClickListener(View.OnClickListener {
//            Log.e(TAG, this.toString())
//            var intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        })

        //jumpMain.setOnClickListener(MyClickListen())

        jumpMain.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                Log.e(TAG, this.toString())
                var intent = Intent(this@SecondActivity, MainActivity::class.java)
                startActivity(intent)
            }

            override fun toString(): String {
                return "我是 OnClickListener"

            }


        })

        openDialog.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("this is a dialog")
                setMessage("SOMETHING IS IMPORTANT")
                setCancelable(false)
                setPositiveButton("OK") { dial, whi -> }
                setNegativeButton("cancle") { dialog, which -> }
                show()
            }
        }

        writetofile.setOnClickListener{
            try {
                val output = openFileOutput("names", MODE_PRIVATE)
                val writer = BufferedWriter(OutputStreamWriter(output))
                writer.use {
                    it.write("i am qinhouliu")
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        writetoPref.setOnClickListener{
            //初级写法
//            var preference = getPreferences(MODE_PRIVATE)
//            val edit = preference.edit()
//            edit.putString("name", "qinhouliu")
//            edit.putInt("age", 30)
//            edit.apply()

            //使用高级函数的高级写法
            getPreferences(MODE_PRIVATE).open {
                putString("name", "qinhouliu")
                putInt("age", 30)
            }
        }

        val databaseHelper = MyDatabaseHelper(this, "BookStore.db", null, 2)
        var database: SQLiteDatabase
        writetoDateBase.setOnClickListener{
            database = databaseHelper.readableDatabase
        }

        insertDate.setOnClickListener{
            database = databaseHelper.readableDatabase
            var contentValue = ContentValues().apply {
                put("name", "the da viniv code")
                put("author", "dab brown")
                put("pages", 454)
                put("price", 16.96)
            }
            database.insert("book", null, contentValue)

            var contentValue2 = ContentValues().apply {
                put("name", "the lost symbol")
                put("author", "dan brown")
                put("pages", 510)
                put("price", 19.96)
            }
            database.insert("book", null, contentValue2)

        }

        updateDate.setOnClickListener{
            database = databaseHelper.readableDatabase
            var contentvalue = ContentValues().apply {
                put("price", 10.99)
            }
            database.update("book", contentvalue, "name = ?", arrayOf("the da viniv code"))
        }

        deleteDate.setOnClickListener{
            database = databaseHelper.readableDatabase
            database?.delete("book", "pages > ?", arrayOf("500"))
        }

        queryDate.setOnClickListener{
            database = databaseHelper.readableDatabase
            var cursor = database.query("book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.e("qinhouliu", "$name $author $pages $price")
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

        replaceDate.setOnClickListener {
            database = databaseHelper.readableDatabase
            database.beginTransaction()
            try {
                database.delete("book", null, null)
//                if (true) {
//                    throw Exception()
//                }

                // 普通写法
//                val values = ContentValues().apply {
//                    put("name", "game of throns")
//                    put("author", "george martin")
//                    put("pages", 720)
//                    put("price", 20.96)
//                }
                //高级一点写法
                //val values = cvOf("name" to "game of throns", "author" to "george martin", "pages" to 720, "price" to 20.96)
                //再高级一点写法
                val values = cvOf2("name" to "game of throns", "author" to "george martin", "pages" to 720, "price" to 20.96)

                database.insert("book", null, values)
                database.setTransactionSuccessful() //事务已经执行成功
            } catch (e : Exception) {
                e.printStackTrace()
            } finally {
                database.endTransaction() //结束事务
            }
        }

        makeCall.setOnClickListener {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), 1)
            } else {
                call()
            }
        }

        sendNotification.setOnClickListener {
            val nfManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                val channel = NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_HIGH)
                nfManager.createNotificationChannel(channel)
            }
            val intent = Intent(this, ForthActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, 0)
            val notification = NotificationCompat.Builder(this, "normal")
                .setContentTitle("this is content title")
                .setContentText("this is content text")
                //.setStyle(NotificationCompat.BigTextStyle().bigText("this is content textn this is content text this is content text this is content text this is content text this is content text this is content text"))
                .setSmallIcon(R.drawable.apple_pic)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.cherry_pic))
                .setContentIntent(pi)
                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(resources, R.drawable.orange_pic)))
                .setAutoCancel(true)
                .build()
            nfManager.notify(1, notification)
        }

        takePhoto.setOnClickListener {
            var intent = Intent(this, CameraAlbumActivity::class.java)
            startActivity(intent)
        }

        playAudio.setOnClickListener {
            var intent = Intent(this, PlayAudioActivity::class.java)
            startActivity(intent)
        }
        playVideo.setOnClickListener {
            var intent = Intent(this, PlayVideoActivity::class.java)
            startActivity(intent)
        }

        openForegroundService.setOnClickListener {
            var intent = Intent(this, TestService1::class.java)
            startService(intent)
        }

        openIntentService.setOnClickListener {
            Log.d("SecondActivity", "Thread id is ${Thread.currentThread().name}")
            var intent = Intent(this, MyIntentService::class.java)
            startService(intent)
        }
    }

    fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:18708117205")
            startActivity(intent)
        } catch (e :Exception) {
            e.printStackTrace()
        }
    }

    override fun toString(): String {
        return "我是 second activity"
    }

    fun SharedPreferences.open(block: SharedPreferences.Editor.() -> Unit) {
        val editor = edit()
        editor.block()
        editor.apply()
    }

    fun cvOf(vararg pairs : Pair<String, Any?>): ContentValues {
        var contentValues = ContentValues()
        for (item in pairs) {
            val key = item.first
            val value = item.second
            //这里之所以要判断类型和做类型转换，是由于put函数只支持几种常见数据类型
            when (value) {
                is Int -> contentValues.put(key, value)
                is Long -> contentValues.put(key, value)
                is Double -> contentValues.put(key, value)
                is Float -> contentValues.put(key, value)
                is Boolean -> contentValues.put(key, value)
                is String -> contentValues.put(key, value)
                is Byte -> contentValues.put(key, value)
                is ByteArray -> contentValues.put(key, value)
                is Short -> contentValues.put(key, value)
                null -> contentValues.putNull(value)
            }
        }
        return contentValues
    }

    fun cvOf2(vararg pairs : Pair<String, Any?>) = ContentValues().apply {
        for (item in pairs) {
            val key = item.first
            val value = item.second
            //这里之所以要判断类型和做类型转换，是由于put函数只支持几种常见数据类型
            when (value) {
                is Int -> put(key, value)
                is Long -> put(key, value)
                is Double -> put(key, value)
                is Float -> put(key, value)
                is Boolean -> put(key, value)
                is String -> put(key, value)
                is Byte -> put(key, value)
                is ByteArray -> put(key, value)
                is Short -> put(key, value)
                null -> putNull(value)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call()
            } else {
                Toast.makeText(this, "you denied the call permission", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        Log.e(TAG, "secondactivity onstart")
        super.onStart()
    }

    override fun onRestart() {
        Log.e(TAG, "secondactivity onRestart")
        super.onRestart()
    }

    override fun onResume() {
        Log.e(TAG, "secondactivity onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.e(TAG, "secondactivity onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.e(TAG, "secondactivity onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.e(TAG, "secondactivity onDestroy")
        super.onDestroy()
    }

    override fun onNewIntent(intent: Intent?) {
        Log.e(TAG, "secondactivity onNewIntent")

        super.onNewIntent(intent)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        Log.e(TAG, "secondactivity onSaveInstanceState")

        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.e(TAG, "secondactivity onRestoreInstanceState")

        super.onRestoreInstanceState(savedInstanceState)
    }

    inner class MyClickListen() : View.OnClickListener {
        val TAG = "qinhouliu"

        override fun onClick(v: View?) {
            Log.e(TAG, this.toString())
            var intent = Intent(this@SecondActivity, MainActivity::class.java)
            startActivity(intent)
        }

        override fun toString() : String {
            return "我是 OnClickListener"
        }

    }


}