package com.example.test10_11_12.test12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.test10_11_12.R
import com.example.test10_11_12.databinding.ActivityMain389Binding

// 경로 test12/src/main/java/com/example/test12/MainActivity389.kt
// 경로 test12/src/main/res/layout/activity_main389.xml
// 추가 뷰 부분에 
// navigation_header , menu_navigation 필요
class MainActivity389 : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain389Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        binding.mainDrawerView.setNavigationItemSelectedListener {
            Toast.makeText(this@MainActivity389,"navigation item click...",Toast.LENGTH_SHORT).show()
            Log.d("kkang","navigation item click... ${it.title}")
            true
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 toggle 버튼에서 제공된거라면..
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}