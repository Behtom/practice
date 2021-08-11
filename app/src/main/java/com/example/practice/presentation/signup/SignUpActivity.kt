package com.example.practice.presentation.signup

import android.os.Bundle
import com.example.practice.core.application.BaseApplication
import com.example.practice.core.base.BaseActivity
import com.example.practice.databinding.ActivitySignUpBinding
import com.example.practice.presentation.signup.fragments.EmailFragment

class SignUpActivity : BaseActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun rootView(): Int = binding.rootContainer.id

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as BaseApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nextFragment(
            EmailFragment.newInstance(),
            EmailFragment.TAG
        )
    }
}