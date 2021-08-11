package com.example.practice.data.persistence.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practice.data.persistence.db.dao.ProductDao
import com.example.practice.data.persistence.db.dao.ShoppingCartDao
import com.example.practice.data.persistence.db.entities.Products
import com.example.practice.data.persistence.db.entities.ShoppingCart

@Database(entities = [Products::class, ShoppingCart::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun shoppingCartDao(): ShoppingCartDao
    abstract fun productsDao(): ProductDao
}