package com.example.helloworld

import android.content.Intent
import android.net.Uri
import android.os.SystemClock
import android.test.InstrumentationTestCase
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.runner.AndroidJUnitRunner
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import com.example.helloworld.activity.AndroidUnitTestActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.runner.JUnitCore

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.helloworld", appContext.packageName)
        InstrumentationRegistry.getInstrumentation()


        //AndroidJUnitRunner

    }

    @Test
    fun startActivity() {
        var intent = Intent()
        //intent.setClassName("com.example.helloworld", "MainActivity4Test")
        //intent.setClassName("com.example.helloworld", "com.example.helloworld.MainActivity4Test")
        intent.setClassName("com.example.helloworld", "com.example.helloworld.activity.AndroidUnitTestActivity")
        intent.setPackage("com.example.helloworld")
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        InstrumentationRegistry.getInstrumentation().startActivitySync(intent)
        SystemClock.sleep(2000)
        //AndroidJUnitRunner.REPORT_KEY_IDENTIFIER



        /*var intent : Intent = Intent()
        intent.setAction(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://www.baidu.com")
        //startActivity(intent)
        InstrumentationRegistry.getInstrumentation().startActivitySync(intent)*/

        /*var intent : Intent = Intent()
        intent.setAction(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://www.baidu.com")
        //startActivity(intent)
        InstrumentationRegistry.getInstrumentation().startActivitySync(intent)*/

        /*var intent : Intent = Intent()
        intent.setAction("just for love")
        intent.addCategory("android.intent.category.qinhou")
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        var instrumentation = InstrumentationRegistry.getInstrumentation()
        instrumentation.startActivitySync(intent)
        SystemClock.sleep(1000)
        var uiDevice = UiDevice.getInstance(instrumentation)
        var uiObject = UiObject(UiSelector().resourceId("com.example.helloworld:id/jump"))
        uiObject.click()
        SystemClock.sleep(2000)*/
        //uiDevice.

        /*var intent : Intent = Intent()
        intent.setAction("just for test")
        intent.addCategory("android.intent.liuqinhou")
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        var instrumentation = InstrumentationRegistry.getInstrumentation()
        instrumentation.startActivitySync(intent)
        SystemClock.sleep(1000)
        var uiDevice = UiDevice.getInstance(instrumentation)
        var uiObject = UiObject(UiSelector().resourceId("com.example.helloworld:id/jump"))
        uiObject.click()
        SystemClock.sleep(2000)*/

        //JUnitCore.runClasses()
    }
}