package com.example.practice.core.di.component

import com.example.practice.MainActivity
import com.example.practice.core.di.module.ApplicationModule
import com.example.practice.presentation.home.HomeActivity
import com.example.practice.presentation.home.di.HomeModule
import com.example.practice.presentation.home.fragments.DetailFragment
import com.example.practice.presentation.home.fragments.ProductsFragment
import com.example.practice.presentation.home.fragments.ShoppingCartFragment
import com.example.practice.presentation.login.LoginActivity
import com.example.practice.presentation.login.di.LoginModule
import com.example.practice.presentation.signup.SignUpActivity
import com.example.practice.presentation.signup.di.SignUpModule
import com.example.practice.presentation.signup.fragments.CreatePasswordFragment
import com.example.practice.presentation.signup.fragments.EmailFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    SignUpModule::class,
    LoginModule::class,
    HomeModule::class
])
interface ApplicationComponent {
    fun inject(activity:MainActivity)
    fun inject(activity:SignUpActivity)
    fun inject(activity:LoginActivity)
    fun inject(activity:HomeActivity)
    fun inject(fragment:EmailFragment)
    fun inject(fragment:CreatePasswordFragment)
    fun inject(fragment:ProductsFragment)
    fun inject(fragment:DetailFragment)
    fun inject(fragment:ShoppingCartFragment)
}