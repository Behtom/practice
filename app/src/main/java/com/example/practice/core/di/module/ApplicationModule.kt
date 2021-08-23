package com.example.practice.core.di.module

import android.content.Context
import com.example.practice.core.application.BaseApplication
import com.example.practice.data.firebase.authentication.email_auth.FirebaseEmailAuth
import com.example.practice.data.firebase.authentication.email_auth.IEmailAuthManager
import com.example.practice.data.persistence.firestore.FirestoreManager
import com.example.practice.data.persistence.shared_preferences.Preferences
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    val application: BaseApplication by lazy { BaseApplication() }

    @Provides
    fun providesContext(): Context = application
    @Provides
    fun providesPreferences(context: Context): Preferences = Preferences(context)
    @Provides
    fun providesEmailAuth(): IEmailAuthManager = FirebaseEmailAuth()
    @Provides
    fun provideFirestoreManager(auth: IEmailAuthManager): FirestoreManager = FirestoreManager(auth)
}