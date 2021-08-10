package com.example.practice.core.application

import android.app.Application
import com.example.practice.core.di.component.ApplicationComponent
import com.example.practice.core.di.component.DaggerApplicationComponent
import com.google.firebase.FirebaseApp

class BaseApplication: Application() {

    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()

    init {
        System.loadLibrary("native-lib")
    }
    external fun flavor(): String

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}