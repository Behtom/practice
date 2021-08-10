package com.example.practice.presentation.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practice.domain.repository.ISignUpRepository
import javax.inject.Inject

class SignUpVMFactory @Inject constructor(private val repo: ISignUpRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ISignUpRepository::class.java).newInstance(repo)
    }
}