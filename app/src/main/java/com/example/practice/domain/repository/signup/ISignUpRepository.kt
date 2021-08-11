package com.example.practice.domain.repository.signup

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface ISignUpRepository {
    suspend fun signupUser(user: String, pwd: String): Task<AuthResult>
}