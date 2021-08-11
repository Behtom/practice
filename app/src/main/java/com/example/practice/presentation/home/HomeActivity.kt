package com.example.practice.presentation.home

import android.os.Bundle
import com.example.practice.core.base.BaseActivity
import com.example.practice.databinding.ActivityHomeBinding
import com.example.practice.presentation.home.fragments.ProductsFragment

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun rootView(): Int = binding.rootContainer.id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nextFragment(
            ProductsFragment.newInstance(),
            ProductsFragment.TAG
        )
    }
}