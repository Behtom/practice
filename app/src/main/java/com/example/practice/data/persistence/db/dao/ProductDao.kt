package com.example.practice.data.persistence.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.practice.data.persistence.db.entities.Products

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Products)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(products: List<Products>)

    @Update
    suspend fun update(product: Products)
}