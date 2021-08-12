package com.example.practice.utils

import android.content.Context
import android.widget.Toast

fun Context.showLongToast(value: String) {
    Toast.makeText(this, value, Toast.LENGTH_LONG).show()
}