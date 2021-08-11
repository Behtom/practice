package com.example.practice.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practice.domain.repository.login.ILoginRepository
import javax.inject.Inject

class LoginVMFactory @Inject constructor(private val repo: ILoginRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ILoginRepository::class.java).newInstance(repo)
    }
}