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

var food : Food? = Food()


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

class Food {
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
    //test1()
   // Test1().test()
   // println(num1AndNum2(5, 3, ::plus))
   // println(num1AndNum2(5, 3, {n1, n2 -> n1 + n2}))
    //println(num1AndNum2(5, 3, ::minus))

    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    val result = StringBuilder().build {
        append("Start eating fruits.\n")
        for (fruit in list) {
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
    }
    println(result.toString())



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

