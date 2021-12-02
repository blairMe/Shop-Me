package com.blair.shopme.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blair.shopme.R
import com.blair.shopme.databinding.FragmentSoldProductsBinding

class SoldProductsFragment : Fragment() {

    private lateinit var binding : FragmentSoldProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSoldProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

}