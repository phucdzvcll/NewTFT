package com.free.newtft

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val words = "a a a a a a b b b b b c c d d o e e e".split(' ')
        val frequenciesByFirstChar = words.distinct()
//            .groupingBy { it }
//            .eachCount()
//        val list = mutableListOf<Tessst>()
//        frequenciesByFirstChar.forEach {
//            list.add(Tessst(
//                name = it.key,
//                amount = it.value
//            ))
//        }
        println(frequenciesByFirstChar)

    }
//    data class Tessst(
//        val name: String,
//        val amount: Int
//    )
}