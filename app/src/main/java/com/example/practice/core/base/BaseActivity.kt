package com.example.practice.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    abstract fun rootView(): Int

    fun nextFragment(fragment: BaseFragment, tag: String, back: Boolean, bundle: Bundle? = null) {
        showNextFragment(fragment, tag, back)
    }

    private fun showNextFragment(fragment: BaseFragment, tag: String, back: Boolean) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        if (back) {
            transaction.add(fragment, tag)
        } else {
            transaction.replace(rootView(), fragment, tag)
        }
        transaction.commit()
    }
}