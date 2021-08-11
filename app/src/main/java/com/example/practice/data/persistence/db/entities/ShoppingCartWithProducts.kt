package com.example.practice.data.persistence.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ShoppingCartWithProducts(
    @Embedded val shoppingCart: ShoppingCart,
    @Relation(
        parentColumn = "scId",
        entityColumn = "shoppingCartId"
    )
    val products: List<Products>
)
