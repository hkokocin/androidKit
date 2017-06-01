package com.github.hkokocin.androidkit.app

import android.app.AlertDialog
import android.content.DialogInterface

@Suppress("UNUSED_PARAMETER")
@Deprecated("use native setPositiveButton instead. The use of this delegate is questionable")
fun AlertDialog.Builder.positiveButton(label: Int, callback: () -> Unit = {}) {
    setPositiveButton(label, DialogInterface.OnClickListener { dialogInterface, i -> callback() })
}

@Suppress("UNUSED_PARAMETER")
@Deprecated("use native setPositiveButton instead. The use of this delegate is questionable")
fun AlertDialog.Builder.positiveButton(label: String, callback: () -> Unit = {}) {
    setPositiveButton(label, DialogInterface.OnClickListener { dialogInterface, i -> callback() })
}

@Suppress("UNUSED_PARAMETER")
@Deprecated("use native setNegativeButton instead. The use of this delegate is questionable")
fun AlertDialog.Builder.negativeButton(label: Int, callback: () -> Unit = {}) {
    setNegativeButton(label, DialogInterface.OnClickListener { dialogInterface, i -> callback() })
}

@Suppress("UNUSED_PARAMETER")
@Deprecated("use native setNegativeButton instead. The use of this delegate is questionable")
fun AlertDialog.Builder.negativeButton(label: String, callback: () -> Unit = {}) {
    setNegativeButton(label, DialogInterface.OnClickListener { dialogInterface, i -> callback() })
}

@Deprecated("use native setMessage instead. The use of this delegate is questionable")
fun AlertDialog.Builder.message(label: Int) {
    setMessage(label)
}

@Deprecated("use native setMessage instead. The use of this delegate is questionable")
fun AlertDialog.Builder.message(label: String) {
    setMessage(label)
}
