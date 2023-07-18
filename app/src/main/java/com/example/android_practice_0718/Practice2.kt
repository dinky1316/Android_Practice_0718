package com.example.android_practice_0718

import java.util.*

fun main(){
    val sc = Scanner(System.`in`)
    val id = "admin"
    val pw = "1234"

    println("ID : ")
    val scanId = sc.nextLine()
    println("PW : ")
    val scanPw = sc.nextLine()

    if(id == scanId && pw == scanPw){
        println("로그인 성공")
    } else {
        println("로그인 실패")
    }

}

class Practice2 {
}