package com.example.practice.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    abstract fun rootView(): Int

    fun nextFragment(fragment: BaseFragment, tag: String, bundle: Bundle? = null) {
        showNextFragment(fragment, tag, bundle)
    }

    private fun showNextFragment(fragment: BaseFragment, tag: String, bundle: Bundle?) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        transaction
            .replace(rootView(), fragment.also {
                if (bundle != null) {
                    it.arguments = bundle
                }
            })
            //.addToBackStack(tag)
            .commit()
    }
}