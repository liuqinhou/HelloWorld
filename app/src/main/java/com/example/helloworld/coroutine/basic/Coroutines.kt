package com.example.helloworld.coroutine.basic

import android.util.Log
import com.example.helloworld.server.HttpService
import kotlinx.coroutines.*
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine

class Coroutines {
    var aa = Dispatchers.Default

}

fun startCoroutine(block: suspend () -> Unit) {
    block.startCoroutine(BaseContinuation())
}

suspend fun startLoadImage(url: String) = suspendCoroutine<ByteArray> {
    //continuation ->  HttpService.service.getLogo(url).excute()
}

suspend fun getToken(): String {
    delay(300)
    println("getToken 开始执行，时间:  ${System.currentTimeMillis()}")
    return "ask"
}

suspend fun getResponse(token: String): String {
    delay(100)
    println("getResponse 开始执行，时间:  ${System.currentTimeMillis()}")
    return "response"
}

fun setText(response: String) {
    println("setText 执行，时间:  ${System.currentTimeMillis()}")
}


fun main() {
//    println("协程初始化开始，时间: " + System.currentTimeMillis())
//    GlobalScope.launch(Dispatchers.Unconfined) {
//        println( "协程初始化完成，时间: " + System.currentTimeMillis())
//        for (i in 1..3) {
//            println("协程任务1打印第$i 次，时间: " + System.currentTimeMillis())
//        }
//        delay(500)
//        for (i in 1..3) {
//            println("协程任务2打印第$i 次，时间: " + System.currentTimeMillis())
//        }
//        println(this)
//        println(this.coroutineContext)
//    }
//    println("主线程 sleep ，时间: " + System.currentTimeMillis())
//    Thread.sleep(1000)
//    println( "主线程运行，时间: " + System.currentTimeMillis())
//    for (i in 1..3) {
//        println("主线程打印第$i 次，时间: " + System.currentTimeMillis())
//    }

    // 运行代码
    GlobalScope.launch(Dispatchers.Main) {
        println("协程 开始执行，时间:  ${System.currentTimeMillis()}")
        val token = getToken()
        val response = getResponse(token)
        setText(response)
    }

    runBlocking {

    }
}
