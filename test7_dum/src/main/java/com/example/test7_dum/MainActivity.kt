package com.example.test7_dum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.test7_dum.databinding.ActivityMainBinding
import com.example.test7_dum.fragment.OneFragment
import com.example.test7_dum.fragment.ThreeFragment
import com.example.test7_dum.fragment.TwoFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        setContentView(binding.root)

        val datas = mutableListOf<String>()
        for (i in 1..3) {
            datas.add("Item $i")
        }

        // 프래그먼트 방식으로 설정 + 적용
        val adapter = MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = adapter


    }

    class MyFragmentPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        val fragments: List<Fragment>

        init {
            fragments = listOf(OneFragment(), TwoFragment(), ThreeFragment())
            Log.d("kkang", "fragments size : ${fragments.size}")
        }

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}