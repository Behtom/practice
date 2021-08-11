package com.example.practice.data.firebase.firestore

import com.google.gson.Gson

class Product {
    var name: String? = null
    var price: Double? = null
    var count: Int? = null
    var large_description: String? = null
    var short_description: String? = null
    override fun toString(): String {
        return Gson().toJson(this)
    }
}