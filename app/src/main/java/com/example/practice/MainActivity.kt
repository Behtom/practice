package com.example.practice

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.practice.core.application.BaseApplication
import com.example.practice.core.base.BaseActivity
import com.example.practice.data.firebase.authentication.email_auth.IEmailAuthManager
import com.example.practice.databinding.ActivityMainBinding
import com.example.practice.presentation.login.LoginActivity
import com.example.practice.presentation.signup.SignUpActivity
import java.util.prefs.Preferences
import javax.inject.Inject

class MainActivity : BaseActivity(), View.OnClickListener {

    @Inject lateinit var emailAuth: IEmailAuthManager
    private lateinit var binding: ActivityMainBinding

    override fun rootView(): Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as BaseApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            it.lifecycleOwner = this
            it.onClickListener = this
        }
        setContentView(binding.root)

        binding.textView.text = BaseApplication().flavor()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnSignIn.id -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            binding.btnSignUp.id -> {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}