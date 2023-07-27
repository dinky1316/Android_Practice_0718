package com.example.test7_dum.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.test7_dum.LoginActivity
import com.example.test7_dum.R
import com.example.test7_dum.RegisterActivity
import com.example.test7_dum.databinding.FragmentOneBinding


class OneFragment : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentOneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {

        binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {

        binding.goToRegister.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.goToRegister -> {
                Log.d("lsy", "test clicked..")
                val intent = Intent(activity, RegisterActivity::class.java)
                startActivity(intent)
            }

        }
    }


}