package com.example.test13_16_17_18.test13

import android.content.Intent
import android.net.Uri
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain414Binding

// 경로 test13/src/main/java/com/example/test13/MainActivity414.kt

class MainActivity414 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain414Binding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("life cycle", "onCreate() 호출")

        binding.button1.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("http://www.google.com")
            startActivity(intent)
        }
        // 액션 문자열 : 시스템에서 제공하는 Intent.ACTION_VIEW
        // 웹 주소, 웹 브라우저 연결, 위도, 경도 값이면 지도로 알아서 연결 합니다
        binding.button2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.7749,127.4194"))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("life cycle", "onStart() 호출")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life cycle", "onResume() 호출")
    }

    override fun onPause() {
        super.onPause()
        Log.d("life cycle", "onPause() 호출")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life cycle", "onStop() 호출")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("life cycle", "onRestart() 호출")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life cycle", "onDestroy() 호출")
    }
}