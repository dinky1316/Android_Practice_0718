package com.example.android_practice_0718

import java.util.*

val data5: Int = 1


val data4: Int by lazy {
    println("in lazy...")
    10 // data4 = 10
}

fun hofFun(arg: (Int) -> Boolean): () -> String {
    val result = if(arg(10)) {
        "valid"
    } else {
        "invalid"
    }
    return {"hofFun result : $result"}
}

fun main() {

    val result = hofFun({no -> no > 0})
    println(result())

    println("in main 순서 1")
    println("data4 사용하자: " + (data4 + 10))
//    val name: String // -> 초기화 오류
    val name: String = "hi hi" // -> 초기화 오류
    println("이름은: $name") // jsp EL 표기법 - ${} 해당 변수 사용 || 코틀린 - $변수  사용
    println("Hello world")
    // data5. : 모든 타입이 객체이기 때문에 닷 연산자 통해서
    // 멤버에 다 접근 가능함

    // 널 허용과 불허용 *****************

    // null 불허용
//    var data6: Int = 10
//    data6 = null

    // null 허용
    var data7: Int? = 10
    data7 = null

    val data10: Array<Int> = Array(3, { 0 })
    data10[0] = 10
    data10[1] = 20
    data10[2] = 40
    data10.set(2, 30)
    println(
        """
           array size : ${data10.size}
           array data : ${data10[0]}, ${data10[1]}, ${data10.get(2)}
        """

    )
}


// val name:String = "dum"; -> 성공
// val name2:String;        -> 오류

class Test {

}