package com.example.practice.presentation.signup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practice.data.network.ConnectionState
import com.example.practice.domain.repository.signup.ISignUpRepository
import com.example.practice.utils.extensions.isValidEmail
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SignUpVM(val repo: ISignUpRepository): ViewModel() {

    private val _signupEmail = MutableLiveData<String>()
    private val _signupEmailError = MutableLiveData<String>()

    private val _signupPwd = MutableLiveData<String>()
    private val _signupPwdConfirm = MutableLiveData<String>()

    val signupEmail: LiveData<String>
        get() = _signupEmail
    val signupEmailError: LiveData<String>
        get() = _signupEmailError

    val signupPwd: LiveData<String>
        get() = _signupPwd
    val signupPwdConfirm: LiveData<String>
        get() = _signupPwdConfirm



    fun afterEmailTextChanged(editText: TextInputEditText) {
        val newText = editText.text.toString()
        val oldText = signupEmail.value
        if (newText != oldText) {
            _signupEmail.value = newText
            _signupEmailError.value = null
        }
    }

    fun afterPwdTextChanged(editText: TextInputEditText) {
        val newText = editText.text.toString()
        val oldText = signupPwd.value
        if (newText != oldText) {
            _signupPwd.value = newText
        }
    }
    fun afterPwdConfirmTextChanged(editText: TextInputEditText) {
        val newText = editText.text.toString()
        val oldText = signupPwdConfirm.value
        if (newText != oldText) {
            _signupPwdConfirm.value = newText
        }
    }

    fun isValidEmail(): Boolean {
        return signupEmail.value?.isValidEmail()?:false
    }

    fun isValidPwd(): Boolean {
        return !signupPwd.value.isNullOrEmpty() && signupPwd.value == signupPwdConfirm.value
    }

    fun setEmailError(value: String) {
        _signupEmailError.value = value
    }

    suspend fun signupUser(): ConnectionState<String> {
        return withContext(Dispatchers.IO) {
            try {
                repo.signupUser(signupEmail.value?:"", signupPwd.value?:"").await()
                ConnectionState.Success("El registro se realizó exitosamente.")
            } catch (e: Exception) {
                ConnectionState.Error("El registro falló, inténtalo nuevamente más tarde.", e)
            }

        }
    }
}