package com.example.practice.presentation.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practice.data.network.ConnectionState
import com.example.practice.domain.repository.login.ILoginRepository
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LoginVM(private val repo: ILoginRepository): ViewModel() {

    private val _loginEmail = MutableLiveData<String>()
    private val _loginPwd = MutableLiveData<String>()

    val loginEmail : LiveData<String>
        get() = _loginEmail
    val loginPwd : LiveData<String>
        get() = _loginPwd

    fun afterEmailTextChanged(editText: TextInputEditText) {
        val newText = editText.text.toString()
        val oldText = loginEmail.value
        if (newText != oldText) {
            _loginEmail.value = newText
        }
    }

    fun afterPwdTextChanged(editText: TextInputEditText) {
        val newText = editText.text.toString()
        val oldText = loginPwd.value
        if (newText != oldText) {
            _loginPwd.value = newText
        }
    }

    suspend fun doLogin(): ConnectionState<String> {
        return withContext(Dispatchers.IO) {
            try {
                repo.doLogin(loginEmail.value?:"", loginPwd.value?:"").await()
                ConnectionState.Success("La sesión se ha iniciado")
            } catch (e: Exception) {
                when (e) {
                    is FirebaseAuthInvalidCredentialsException -> {
                        ConnectionState.Error("Usuario y/o contraseña inválidos.", e)
                    }
                    is FirebaseNetworkException -> {
                        ConnectionState.Error("Verifica tu conexión de red e inténtalo nuevamente.", e)
                    }
                    else -> {
                        ConnectionState.Error("Ha ocurrido un error, inténtalo nuevamente más tarde.", e)
                    }
                }
            }
        }
    }
}