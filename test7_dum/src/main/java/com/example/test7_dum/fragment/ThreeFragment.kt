package com.example.test7_dum.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test7_dum.databinding.FragmentOneBinding
import com.example.test7_dum.databinding.FragmentThreeBinding
import com.example.test7_dum.databinding.FragmentTwoBinding


class ThreeFragment : Fragment() {
    lateinit var binding: FragmentThreeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThreeBinding.inflate(inflater, container, false)
        return binding.root
    }


}