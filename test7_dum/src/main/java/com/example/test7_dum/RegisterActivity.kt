package com.example.test7_dum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.test7_dum.databinding.ActivityMainBinding
import com.example.test7_dum.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityRegisterBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setContentView(binding.root)

        binding.register.setOnClickListener{
            val name : String = binding.addName.text.toString()
            val id : String = binding.addID.text.toString()
            val pw : String = binding.addPW.text.toString()
            val pw2 : String = binding.addPW2.text.toString()
            Log.d("hi","id의 값 : $id , pw의 값 : $pw, name의 값 : $name")
            if(pw == pw2){
                Toast.makeText(this,"id의 값 : $id , pw의 값 : $pw, name의 값 : $name",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"비밀번호를 확인해주세요",Toast.LENGTH_LONG).show()
            }
        }

    }
}