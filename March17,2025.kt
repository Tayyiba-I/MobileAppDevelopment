package com.example.mad_class

fun main() {
    var a: String =" hello"

    var len : Int =  a.length
    println("length= $len")
    //
    var a1 : String = " hiiiiii "
    var a2 : String = " hiii "
    var a3 : Boolean = a1.equals(a2)
    println(a3)

    //
    var pt = a.isEmpty()
    println(pt)
    //
    var add = a.plus(a1)
    println(add)

    var up = a.uppercase()
    println(up)

    var low= a.lowercase()
    println(low)

    var t= a1.trim()
    println(t)

}