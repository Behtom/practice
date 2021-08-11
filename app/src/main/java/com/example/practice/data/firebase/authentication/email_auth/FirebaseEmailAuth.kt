package com.example.practice.data.firebase.authentication.email_auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class FirebaseEmailAuth @Inject constructor(): IEmailAuthManager {

    private val auth = FirebaseAuth.getInstance()

    override suspend fun createUser(user: String, pwd: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(user, pwd)
    }

    override suspend fun signinUser(user: String, pwd: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(user, pwd)
    }
}