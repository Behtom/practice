package com.example.practice.data.firebase.di

import com.example.practice.data.firebase.authentication.email_auth.FirebaseEmailAuth
import com.example.practice.data.firebase.authentication.email_auth.IEmailAuthManager
import dagger.Module
import dagger.Provides

@Module
class FirebaseModule {
    @Provides
    fun providesEmailAuth(): IEmailAuthManager = FirebaseEmailAuth()
}