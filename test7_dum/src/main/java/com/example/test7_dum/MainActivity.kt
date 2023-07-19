package com.example.test7_dum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test7_dum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContentView(binding.root)

        binding.goTologin.setOnClickListener{
            val goToLogin = Intent(this, LoginActivity::class.java)
            startActivity(goToLogin)
        }

        binding.goToRegister.setOnClickListener{
            val goToRegister = Intent(this, RegisterActivity::class.java)
            startActivity(goToRegister)
        }


    }
}