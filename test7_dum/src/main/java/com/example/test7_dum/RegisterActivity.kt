package com.example.test7_dum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.test7_dum.databinding.ActivityMainBinding
import com.example.test7_dum.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    var myDB: UserDatabase? = null

    lateinit var binding: ActivityRegisterBinding

    var buttonInsert: Button? = null
    var editName: EditText? = null
    var editPW: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDB = UserDatabase(this)

        editName = binding.editName
        editPW = binding.editPW
        buttonInsert = binding.buttonInsert

        AddData()
//        binding.register.setOnClickListener {
//            val name: String = binding.editName!!.text.toString()
//            val pw: String = binding.editPW!!.text.toString()
//            val pw2: String = binding.editPW2!!.text.toString()
//            Log.d("hi", "pw의 값 : $pw, name의 값 : $name")
//            if (pw == pw2) {
//                Toast.makeText(this, "pw의 값 : $pw, name의 값 : $name", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "비밀번호를 확인해주세요", Toast.LENGTH_LONG).show()
//            }
//        }

    }

    fun AddData() {
        buttonInsert!!.setOnClickListener {
            val isInserted = myDB!!.insertData(
                editName!!.text.toString(),
                editPW!!.text.toString()
            )
            if (isInserted == true)
                Toast.makeText(this@RegisterActivity, "데이터추가 성공", Toast.LENGTH_LONG)
                    .show()
            else Toast.makeText(this@RegisterActivity, "데이터추가 실패", Toast.LENGTH_LONG).show()

        }
    }

}