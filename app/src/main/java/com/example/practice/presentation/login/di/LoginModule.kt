package com.example.practice.presentation.login.di

import com.example.practice.data.firebase.authentication.email_auth.FirebaseEmailAuth
import com.example.practice.domain.repository.login.ILoginRepository
import com.example.practice.domain.repository.login.LoginRepository
import com.example.practice.presentation.login.viewmodel.LoginVMFactory
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @Provides
    fun providesLoginRepository(emailAuth: FirebaseEmailAuth): ILoginRepository = LoginRepository(emailAuth)
    @Provides
    fun providesLoginVMFactory(repo: ILoginRepository): LoginVMFactory = LoginVMFactory(repo)
}