package com.example.practice.domain.repository.login

import com.example.practice.data.firebase.authentication.email_auth.IEmailAuthManager
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class LoginRepository @Inject constructor(private val emailManager: IEmailAuthManager): ILoginRepository {
    override suspend fun doLogin(user: String, pwd: String): Task<AuthResult> {
        return emailManager.signinUser(user, pwd)
    }
}