package com.example.practice.data.persistence.shared_preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Preferences(context: Context) {
    private val KEY_USER: String by lazy { "com.example.practice.KEY_USER" }
    private val KEY_IS_LOGGED: String by lazy { "com.example.practice.KEY_IS_LOGGED" }
    private val KEY_IS_ENROLLED: String by lazy { "com.example.practice.KEY_IS_ENROLLED" }
    private val KEY_PREFERENCES: String by lazy { "com.example.practice.Preferences" }

    private val preferences: SharedPreferences = context.getSharedPreferences(KEY_PREFERENCES, MODE_PRIVATE)
    private val edit = preferences.edit()

    fun isLogged(value: Boolean) {
        edit.putBoolean(KEY_IS_LOGGED, false).commit()
    }
    fun isLogged(): Boolean {
        return preferences.getBoolean(KEY_IS_LOGGED, false)
    }

    fun isEnrolled(value: Boolean) {
        edit.putBoolean(KEY_IS_ENROLLED, false).commit()
    }
    fun isEnrolled(): Boolean {
        return preferences.getBoolean(KEY_IS_ENROLLED, false)
    }
}