package com.example.helloworld

import com.example.helloworld.otherPackage.Singleton1
import com.example.testpackage.Singleton2
import java.lang.StringBuilder
import kotlin.math.max
import kotlin.math.min
import java.util.function.BiConsumer


open class Person(val name: String, val age: Int) {
//    val name = ""
//    val age = 10

    fun eat() {
        println("name = " + name + ";age = " + age)
    }
}

//var food : Food? = Food()


class Student(val sno:Int, val grade: Int, name: String, age: Int ):Person(name, age) {
    fun study(info : Info?) {
//        info?.dosomething() ?: print("qinhou")
//        info!!.dosomething()

        info?.let {
            it.dosomething()
            it.do2things()
        } ?: print("放假，不做事情")
    }

    fun eat(food : Food?){
        food?.height()
    }
}

object Food {
    init {
        println("food 初始化")
    }
    fun height() {
        println("称重")
    }
}

class Info {
    fun dosomething() {
        println("做事情")
    }

    fun do2things() {
        println("做第二件事")
        Food
    }

}

fun test1() {
    val list = listOf("apple", "banana", "orange", "pear")
    val result1 = with(StringBuilder()) {
        append("start eating fruits.\n")
        for (fruit in list) {
            append(fruit).append("\n")
        }
        toString()
    }
    println(result1)

    val result2 = StringBuilder().run {
        append("start eating fruits.\n")
        for (fruit in list) {
            append(fruit).append("\n")
        }
        toString()
    }
    println(result2)

    val result3 = StringBuilder().apply {
        append("start eating fruits.\n")
        for (fruit in list) {
            append(fruit).append("\n")
        }
        toString()
    }

    println(result3)

}

class Test1 {
    fun isOdd(x: Int) = x % 2 != 0

    fun test() {
        var list = listOf(1, 2, 3, 4, 5)
        println(list.filter(this::isOdd))
    }
}

fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    val result = operation(num1, num2)
    return result
}

fun plus(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun minus(num1: Int, num2: Int): Int {
    return num1 - num2
}

fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
    block()
    return this
}

fun main() {

    Singleton2.singletonTest()

    var str1 = "onReply: HTTP/1.1 200 OK\n" +
            "    vary: Origin\n" +
            "    access-control-allow-credentials: true\n" +
            "    content-type: application/json; charset=utf-8\n" +
            "    etag: \"teuof0\"\n" +
            "    timing-allow-origin: *\n" +
            "    x-content-type-options: nosniff\n" +
            "    request-id: tYIDbcir1_IwAlFr-ubPa\n" +
            "    content-length: 987\n" +
            "    date: Tue, 15 Mar 2022 15:24:40 GMT\n" +
            "    connection: keep-alive\n" +
            "    keep-alive: timeout=5\n" +
            "    \n" +
            "    {\"code\":0,\"subcode\":0,\"message\":\"success\",\"data\":{\"sign_in_tip_ext\":{\"award_10_0_0\":\",path/4242480.png,path/4242482.png\",\"award_4_0_0\":\"Treasure chest,path/3462328.png,\",\"info_4\":\"8,0,0,100,0\",\"record\":\"0000000\",\"award_7_0_2\":\"Lollipop,t_ws_gift_info/1951716.png,t_ws_gift_info/1951717.png\",\"info_0\":\"10,0,0,100,0\",\"period_range\":\"2022-03-15 22:19~2022-03-15 22:25\",\"award_1_0_0\":\",path/3462291.png,path/4242417.png\",\"award_7_0_3\":\"I Love You,t_ws_gift_info/2078603.png,t_ws_gift_info/2078604.png\",\"info_6\":\"7,0,2,2,0\",\"url_prefix\":\"https://y.qq.com/music/common/upload/\",\"award_config_type\":\"2\",\"info_5\":\"4,0,0,1,3\",\"award_8_0_0\":\",path/4250570.png,path/4242957.png\",\"info_2\":\"1,0,0,3,0\",\"info_1\":\"1,0,0,2,0\",\"info_3\":\"7,0,3,1,0\",\"today_num\":\"0\"},\"need_show_sign_in_tip\":true,\"need_show_floating_window\":true,\"jump_pop_up_url\":\"http://wesingapp.com/signin-reward?hippy=signin-reward&isPopLayer=true&_wv=4097\",\"version_id\":\"VERSION_ID_V3\",\"award_conf_type\":\"AWARD_CONFIG_TYPE_OLD_USER\"}}\n"

    var str2 = "{\"code\":13,\"subcode\":0,\"message\":\"Response message parsing error: invalid wire type 6 at offset 629\",\"data\":null}"
//    var indexStart = str1.indexOf("{")
//    var indexEnd = str1.length - 1
//    println("indexStart=$indexStart; indexEnd=$indexEnd")
//    var str3 = str1.replaceRange(indexStart, indexEnd, str2)
//    println(str3)

//    var list = str1.split("\n").toMutableList()
//    list[list.size -2] =  "{\"code\":13,\"subcode\":0,\"message\":\"Response message parsing error: invalid wire type 6 at offset 629\",\"data\":null}"
//    //= "{\"code\":13,\"subcode\":0,\"message\":\"Response message parsing error: invalid wire type 6 at offset 629\",\"data\":null}"
//    for (line in list) {
//        println(line + "222")
//    }
//    println("end!!")



    //test1()
   // Test1().test()
   // println(num1AndNum2(5, 3, ::plus))
   // println(num1AndNum2(5, 3, {n1, n2 -> n1 + n2}))
    //println(num1AndNum2(5, 3, ::minus))

//    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
//    val result = StringBuilder().build {
//        append("Start eating fruits.\n")
//        for (fruit in list) {
//            append(fruit).append("\n")
//        }
//        append("Ate all fruits.")
//    }
//    println(result.toString())


    //Info().do2things()


//    val s = Student(12, 123)
//    s.eat()
//    val a = 10
//    var b =9
//    println(largeNumber(a, b))
//    println(getScore("Toms"))
//    println(getScores("Jim"))
//    val range = 0 .. 10
//    val until = 0 until 10
//    for (i in until) {
//        println(i)
//    }
//    println( 18 in range)

//    Singleton.singletonTest()
//    Singleton1.singletonTest()
//    Singleton2.singletonTest()

//    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
//    val newList = list.map { it.toUpperCase() }
//    for (fruit in newList) {
//        println(fruit)
//    }

//    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
//    val maxLengthFruit = list.maxBy { it.length }
//    println("max length fruit is " + maxLengthFruit)

//    Thread {
//        println("Thread is running")
//    }.start()
//    var st = Student(11, 2, "qinhou", 30)
//    st.study(Info())
//    st.study(null)

}

fun largeNumber(a: Int, b: Int)= if (a > b) a else b

fun getScore(name: String) = if (name == "Tom") {
    86
} else if (name == "Jim") {
    97
} else {
    57
}

fun getScores(name: String) = when (name) {
    is String -> "right"
    "Tom" -> 86
    "Jim" -> "qinhou"
    else -> 57
}

