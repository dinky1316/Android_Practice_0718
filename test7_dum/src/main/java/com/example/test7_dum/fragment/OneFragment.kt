package com.example.test7_dum.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test7_dum.LoginActivity
import com.example.test7_dum.RegisterActivity
import com.example.test7_dum.databinding.FragmentOneBinding


class OneFragment : Fragment() {
    lateinit var binding: FragmentOneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root

    }
    

}