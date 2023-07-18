package com.example.android_practice_0718

import java.util.*

fun main(){

    val sc = Scanner(System.`in`)

    println("=회원 가입===============")
    println("ID 입력")
    val id = sc.nextLine()
    println("PW 입력")
    val pw = sc.nextLine()
    println("email 입력")
    val email = sc.nextLine()
    println("phone 입력")
    val phone = sc.nextLine()

    println("=======================")
    println("회원 정보:")
    println("id : $id")
    println("pw : $pw")
    println("email : $email")
    println("phone : $phone")

    println("=======================")
    println("로그인")
    println("ID : ")
    val scanId = sc.nextLine()
    println("PW : ")
    val scanPw = sc.nextLine()

    println("=======================")

    if(id == scanId && pw == scanPw){
        println("로그인 성공")
    } else {
        println("로그인 실패")
    }
}

class Practice {



}