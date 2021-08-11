package com.example.practice.utils.tools

import com.example.practice.utils.extensions.toMoneyFormat

object Converter {
    @JvmStatic
    fun amountToString(amount: Double): String {
        return amount.toMoneyFormat()
    }
}