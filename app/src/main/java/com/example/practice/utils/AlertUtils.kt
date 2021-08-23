package com.example.practice.utils

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.example.practice.R

class AlertUtils {
    companion object {
        fun showErrorAlert(context: Context, message: String) {
            AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.text_error))
                    .setMessage(message)
                    .setPositiveButton(context.getString(R.string.text_accept)) { dialog, _ -> dialog.dismiss() }
                    .show()
        }

        fun showChooseAlert(context: Context, title: String, message: String, positiveAction: () -> Unit) {
            AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.text_accept)) { dialog, which ->
                    positiveAction.invoke()
                }
                .setNegativeButton(context.getString(R.string.text_cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }
    }
}