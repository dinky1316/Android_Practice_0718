package com.example.test7_dum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.test7_dum.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val binding = ActivityLoginBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setContentView(binding.root)

        binding.login.setOnClickListener{
            val adminId: String = "admin"
            val adminPW: String = "1234"
            val id: String = binding.loginID.text.toString()
            val pw: String = binding.loginPW.text.toString()
            if(adminId == id && adminPW == pw){
                Toast.makeText(this,"id의 값 : $id , pw의 값 : $pw", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"아이디 혹은 비밀번호를 확인해주세요",Toast.LENGTH_SHORT).show()
            }
        }

    }
}