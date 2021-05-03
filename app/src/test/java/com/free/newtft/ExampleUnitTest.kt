package com.free.newtft

import org.junit.Test
import java.util.*
import kotlin.Comparator

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val list = mutableListOf(1,2,3,4)
        list.removeAt(2)
        //list.add(0,2)
        println(list)
    }
}