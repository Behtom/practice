package com.example.practice.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practice.domain.repository.home.IHomeRepository
import javax.inject.Inject

class HomeVMFactory @Inject constructor(private val repo: IHomeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IHomeRepository::class.java).newInstance(repo)
    }
}