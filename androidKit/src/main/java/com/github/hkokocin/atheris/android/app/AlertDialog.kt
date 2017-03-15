package com.github.hkokocin.atheris.android.app

import android.app.AlertDialog
import android.content.DialogInterface

@Suppress("UNUSED_PARAMETER")
fun AlertDialog.Builder.positiveButton(label: Int, callback: () -> Unit = {}) {
    setPositiveButton(label, DialogInterface.OnClickListener { dialogInterface, i -> callback() })
}

@Suppress("UNUSED_PARAMETER")
fun AlertDialog.Builder.positiveButton(label: String, callback: () -> Unit = {}) {
    setPositiveButton(label, DialogInterface.OnClickListener { dialogInterface, i -> callback() })
}

@Suppress("UNUSED_PARAMETER")
fun AlertDialog.Builder.negativeButton(label: Int, callback: () -> Unit = {}) {
    setNegativeButton(label, DialogInterface.OnClickListener { dialogInterface, i -> callback() })
}

@Suppress("UNUSED_PARAMETER")
fun AlertDialog.Builder.negativeButton(label: String, callback: () -> Unit = {}) {
    setNegativeButton(label, DialogInterface.OnClickListener { dialogInterface, i -> callback() })
}

fun AlertDialog.Builder.message(label: Int) {
    setMessage(label)
}

fun AlertDialog.Builder.message(label: String) {
    setMessage(label)
}
