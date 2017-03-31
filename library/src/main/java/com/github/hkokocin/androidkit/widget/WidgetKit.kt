package com.github.hkokocin.androidkit.widget

import android.widget.EditText
import android.widget.Spinner
import com.github.hkokocin.androidkit.kit

fun <T> Spinner.onItemSelected(callback: (item: T) -> Unit) = kit.onItemSelected(this, callback)

fun EditText.onTextChange(callback: (String) -> Unit) = kit.onTextChange(this, callback)

interface WidgetKit {
    fun onTextChange(editText: EditText, callback: (String) -> Unit) {
        editText.addTextChangedListener(TextChangeListener(afterChanged = { callback(it) }))
    }

    fun <T> onItemSelected(spinner: Spinner, callback: (item: T) -> Unit) {
        spinner.onItemSelectedListener = ItemSelectedListener(spinner, callback)
    }
}