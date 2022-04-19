package com.example.helloworld

import kotlinx.coroutines.*
import org.junit.Test

import org.junit.Assert.*
import java.lang.Exception
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.system.measureTimeMillis

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testCoroutine01() = runBlocking {
        var time = measureTimeMillis {
            var job1 = launch {
                delay(200)
                println("ONE")
            }
            println("####MAIN PROCESS 1")
            var job2 = launch {
                delay(100)
                println("TWO")
            }
            //job1.join()
            delay(50)
            println("####MAIN PROCESS 2")
            var job3 = launch {
                delay(40)
                println("THREE")
            }
            delay(45)
            println("####MAIN PROCESS 3")
        }
        println("it cost $time s")
    }

//    @Test
//    fun testDispatched() = runBlocking {
//
//        async(context = Dispatchers.IO, start = CoroutineStart.DEFAULT) {
//            println("thread: ${Thread.currentThread().name}")
//        }
//        //println("thread: ${Thread.currentThread().name}")
//    }

    @Test
    fun testReleaseResource() = runBlocking {
        var job = launch {
            repeat(1000) {
                try {
                    println("job: i'm sleeping ... $it")
                    delay(500)
                } finally {
                    println("job: release resource success")
                }

            }
        }
        delay(1000)
        println("main: i'm tired of waiting")
        job.cancelAndJoin()
        job.cancel()
        println("main: now i can quit")
    }

    fun computeRunTime(action: (() -> Unit)?) {
        var startTime = System.currentTimeMillis()
        action?.invoke()
        println("the code run cost time is: ${System.currentTimeMillis() - startTime}")
    }

    @Test
    fun testComputeByList() {
        computeRunTime {
            (0..1000)
                .map { it + 1}
                .filter { it % 2 ==0 }
                .count { it < 10 }
                .run { println("by using list way, result is: $this") }
        }
    }

    @Test
    fun testComputeBySequence() {
        computeRunTime {
            (0..100)
                .map { it + 1}
                .filter { it % 2 ==0 }
                .count { it < 10 }
                .run { println("by using list way, result is: $this") }
        }

        computeRunTime {
            (0..100)
                .asSequence()
                .map { it + 1}
                .filter { it % 2 ==0 }
                .count { it < 10 }
                .run { println("by using sequence way, result is: $this") }
        }
    }

    @Test
    fun test01() {
        (0..6)
            .asSequence()
            .map {//map返回是Sequence<T>，故它属于中间操作
                println("map: $it")
                return@map it + 1
            }
            .filter {//filter返回是Sequence<T>，故它属于中间操作
                println("filter: $it")
                return@filter it % 2 == 0
            }
    }

    @Test
    fun test02() {
        (0..6)
            .asSequence()
            .map {//map返回是Sequence<T>，故它属于中间操作
                println("map: $it")
                return@map it + 1
            }
            .filter {//filter返回是Sequence<T>，故它属于中间操作
                println("filter: $it")
                return@filter it % 2 == 0
            }
            .count {//count返回是Int，返回的是一个结果，故它属于末端操作
                println("count: $it")
                it < 6
            }
            .run {
                println("result is $this");
            }
    }

    @Test
    fun test03() {
        (0..6)
            .asSequence()
            .filter {//filter返回是Sequence<T>，故它属于中间操作
                println("filter: $it")
                return@filter it % 2 == 0
            }
            .map {//map返回是Sequence<T>，故它属于中间操作
                println("map: $it")
                return@map it + 1
            }
            .count {//count返回是Int，返回的是一个结果，故它属于末端操作
                println("count: $it")
                it < 6
            }
            .run {
                println("result is $this");
            }
    }

    @Test
    fun test04() {
        var list = CopyOnWriteArrayList<String>()
        list.add("l")
        list.add("i")
        list.add("u")
        list.add("q")
        list.add("i")
        list.add("n")
        println(list.size)
        list.removeAt(0)
        list.removeAt(0)
        println(list.size)
        println(list.toString())
    }

    @Test
    fun test05() {
        //Student.INSTANCE

    }


//    @Test
//    fun testSequencesScope() {
//        seque  nce<Int> {  }
//    }
}