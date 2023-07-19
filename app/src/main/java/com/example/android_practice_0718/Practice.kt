package com.example.android_practice_0718

import java.util.*


// data class - 임시 DTO(=VO), 데이터 전달 하기 위한 객체
// 클래스에서 만들어서 사용할 때, 주 생성자를 이용하는데, 매개변수 부분에 변수 앞에 val 라는 키워드를 사용해서
// 전역으로 사용하는 부분.
data class User(val id: String, val pw: String) {

}

// 로그인 클래스 구현
class LoginTest {
    companion object {
        // 클래스 함수 -> 클래스명.함수
        fun login(user: User) {

            if (user.id.equals("admin") && user.pw.equals("1234")) {
                println("로그인 성공")
            } else {
                println("로그인 실패")
            }
        }

    }
}

fun main() {

    // 코틀린 : 모든 타입 객체
    // 변수 선언 =>  var(val) 변수명 : 타입 = 해당값 할당 형식
    // ide 1차 문법 체크
    val sc = Scanner(System.`in`)

    println("=회원 가입===============")
    println("ID 입력")
    val id = sc.nextLine()
    println("PW 입력")
    val pw = sc.nextLine()
    val user: User = User(id, pw)

    println("=======================")
    println("회원 정보:")
    println(user)

    println("=======================")

    LoginTest.login(user)

}