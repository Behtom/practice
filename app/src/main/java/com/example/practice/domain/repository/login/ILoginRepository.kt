package com.example.practice.domain.repository.login

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface ILoginRepository {
    suspend fun doLogin(user: String, pwd: String): Task<AuthResult>
}