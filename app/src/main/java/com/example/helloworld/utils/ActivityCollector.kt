package com.example.helloworld.utils

import android.app.Activity

object ActivityCollector {

    var collector = ArrayList<Activity>()

    fun addActivity(activity: Activity) {
        collector.add(activity)
    }

    fun removeActivity(activity: Activity) {
        collector.remove(activity)
    }

    fun finishAll() {
        for (item in collector) {
            if (!item.isFinishing) {
                item.finish()
            }
        }
        collector.clear()
    }
}