package com.example.helloworld.otherPackage

import android.content.ComponentName
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.helloworld.*
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_second.jumpMain
import kotlinx.android.synthetic.main.activity_third.*
import java.util.jar.Manifest

class ThirdActivity : BaseActivity() {

    val TAG = "qinhouliu"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "ThirdActivity onCreate")

        if (checkSelfPermission(android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.READ_CONTACTS), 1)
        } else {
            readContracts()
        }

        Log.d("qinhouliu","thirdactivity --- $taskId")
        setContentView(R.layout.activity_third)

        jumpMain.setOnClickListener {
            var intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        jumpSecond.setOnClickListener {
            var intent: Intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        jumpForth.setOnClickListener {
            var intent: Intent = Intent(this, ForthActivity::class.java)
            startActivity(intent)
        }

        jumpClientAppActivity.setOnClickListener {
            var intent: Intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            var cn = ComponentName("com.example.clientapp", "com.example.clientapp.SecondActivity")
            intent.setComponent(cn)
            startActivity(intent)
        }

        getContract.setOnClickListener {
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, readContracts())
            contractList.adapter = adapter
        }

    }

    private fun readContracts(): List<String> {
        //TODO("Not yet implemented")
        val data = ArrayList<String>()
        Log.e("qinhouliu", "uri = " + ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
        val cr = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)?.apply {
            while (moveToNext()) {
                // 获取联系人姓名
                val displayName = getString(getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                // 获取联系人手机号
                val number = getString(getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER))
                data.add("$displayName\n$number")
            }
            close()
        }
        return data
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContracts()
                } else {
                    Toast.makeText(this, "you denied read contract permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onStart() {
        Log.e(TAG, "ThirdActivity onstart")
        super.onStart()
    }

    override fun onRestart() {
        Log.e(TAG, "ThirdActivity onRestart")
        super.onRestart()
    }

    override fun onResume() {
        Log.e(TAG, "ThirdActivity onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.e(TAG, "ThirdActivity onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.e(TAG, "ThirdActivity onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.e(TAG, "ThirdActivity onDestroy")
        super.onDestroy()
    }

    override fun onNewIntent(intent: Intent?) {
        Log.e(TAG, "ThirdActivity onNewIntent")
        super.onNewIntent(intent)
    }



}