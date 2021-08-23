package com.example.practice.presentation.home

import android.os.Bundle
import com.example.practice.core.base.BaseActivity
import com.example.practice.databinding.ActivityHomeBinding
import com.example.practice.presentation.home.fragments.DetailFragment
import com.example.practice.presentation.home.fragments.ProductsFragment
import com.example.practice.presentation.home.fragments.ShoppingCartFragment
import com.example.practice.presentation.home.fragments.TicketFragment

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

    override fun onBackPressed() {
        when (supportFragmentManager.fragments.last()) {
            is ProductsFragment -> finish()
            is TicketFragment -> {}
            is ShoppingCartFragment, is DetailFragment -> {
                nextFragment(
                        ProductsFragment.newInstance(),
                        ProductsFragment.TAG
                )
            }
            else -> super.onBackPressed()
        }
    }
}