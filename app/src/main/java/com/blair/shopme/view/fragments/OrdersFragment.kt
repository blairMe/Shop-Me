package com.blair.shopme.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blair.shopme.databinding.FragmentOrdersBinding

class OrdersFragment : Fragment() {

    private lateinit var binding : FragmentOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //the view
        binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

}