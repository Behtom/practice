package com.example.practice.presentation.signup.di

import com.example.practice.data.firebase.authentication.email_auth.FirebaseEmailAuth
import com.example.practice.domain.repository.ISignUpRepository
import com.example.practice.domain.repository.SignUpRepository
import com.example.practice.presentation.signup.viewmodel.SignUpVMFactory
import dagger.Module
import dagger.Provides

@Module
class SignUpModule {
    @Provides
    fun providesRepository(emailAuth: FirebaseEmailAuth): ISignUpRepository = SignUpRepository(emailAuth)
    @Provides
    fun provideViewModelFactory(repository: ISignUpRepository): SignUpVMFactory = SignUpVMFactory(repository)
}