package com.example.practice.domain.repository.signup

import com.example.practice.data.firebase.authentication.email_auth.IEmailAuthManager
import com.example.practice.domain.repository.signup.ISignUpRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class SignUpRepository @Inject constructor(private val emailManager: IEmailAuthManager): ISignUpRepository {
    override suspend fun signupUser(user: String, pwd: String): Task<AuthResult> {
        return emailManager.createUser(user, pwd)
    }
}