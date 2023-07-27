package com.example.test13_16_17_18.test13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMainBinding

// 경로 test13/src/main/java/com/example/test13/MainActivity.kt
// main408 : 구글이 권장하는 후처리 함수
// main443 : 코루틴

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            // ::class.java 클래스 래퍼런스 타입으로 전달은 보통, 내부 앱에서 전달하는 방식
            val intent: Intent = Intent(this, DetailActivity::class.java)
            // map 처럼 키와 value의 형태로 데이터를 설정 및 가져오기 작업을 할 예정
            intent.putExtra("data1", "hello")
            intent.putExtra("data2", 10)
            // 시스템에게 설정한 인텐트를 전달
            startActivity(intent)
        }

    }
}