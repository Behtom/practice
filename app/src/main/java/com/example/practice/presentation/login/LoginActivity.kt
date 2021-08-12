package com.example.practice.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.practice.core.application.BaseApplication
import com.example.practice.core.base.BaseActivity
import com.example.practice.data.network.ConnectionState
import com.example.practice.databinding.ActivityLoginBinding
import com.example.practice.presentation.home.HomeActivity
import com.example.practice.presentation.login.viewmodel.LoginVM
import com.example.practice.presentation.login.viewmodel.LoginVMFactory
import com.example.practice.utils.extensions.hide
import com.example.practice.utils.extensions.show
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginActivity : BaseActivity(), View.OnClickListener {

    @Inject lateinit var factory: LoginVMFactory
    private lateinit var binding: ActivityLoginBinding

    private val loginVM: LoginVM by lazy {
        ViewModelProviders.of(this, factory)[LoginVM::class.java]
    }

    override fun rootView(): Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as BaseApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater).also {
            it.lifecycleOwner = this@LoginActivity
            it.onClickListener = this
            it.viewModel = loginVM
        }
        setContentView(binding.root)
    }

    override fun onClick(v: View?) {
        binding.loaderContainer.show()
        MainScope().launch {
            when (val result = loginVM.doLogin()) {
                is ConnectionState.Success -> {
                    Toast.makeText(this@LoginActivity, result.response, Toast.LENGTH_LONG).show()
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                is ConnectionState.Error -> {
                    Toast.makeText(this@LoginActivity, result.response, Toast.LENGTH_LONG).show()
                }
            }
        }
        binding.loaderContainer.hide()
    }
}