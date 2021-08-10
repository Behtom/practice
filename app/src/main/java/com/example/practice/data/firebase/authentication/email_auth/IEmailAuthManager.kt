package com.example.practice.data.firebase.authentication.email_auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface IEmailAuthManager {
    suspend fun createUser(user: String, pwd: String): Task<AuthResult>
    suspend fun signinUser(user: String, pwd: String): Task<AuthResult>
}