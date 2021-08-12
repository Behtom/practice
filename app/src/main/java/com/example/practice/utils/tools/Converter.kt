package com.example.practice.utils.tools

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.practice.utils.extensions.toMoneyFormat

object Converter {
    @JvmStatic
    fun amountToString(amount: Double): String {
        return amount.toMoneyFormat()
    }
    @JvmStatic
    fun getVisibility(value: Boolean): Int {
        return if (value) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
    @JvmStatic
    fun getDrawable(value: String, context: Context): Drawable? {
        val resources = context.resources
        val resId = resources.getIdentifier(value, "drawable", context.packageName)
        return ResourcesCompat.getDrawable(resources, resId, null)
    }
}