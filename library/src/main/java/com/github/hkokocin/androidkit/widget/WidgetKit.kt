package com.github.hkokocin.androidkit.widget

import android.widget.EditText
import android.widget.Spinner
import com.github.hkokocin.androidkit.AndroidKit

fun <T> Spinner.onItemSelected(callback: (item: T) -> Unit) = AndroidKit.instance.onItemSelected(this, callback)

fun EditText.onAfterTextChanged(callback: (String) -> Unit) = AndroidKit.instance.onAfterTextChanged(this, callback)

interface WidgetKit {
    fun onAfterTextChanged(editText: EditText, callback: (String) -> Unit) {
        editText.addTextChangedListener(TextChangeListener(afterChanged = { callback(it) }))
    }

    fun onTextChanged(editText: EditText, callback: (CharSequence, Int, Int, Int) -> Unit): TextChangeListener {
        val listener = TextChangeListener(onChanged = { s: CharSequence, start: Int, count: Int, after: Int ->
            callback(s, start, count, after)
        })

        editText.addTextChangedListener(listener)
        return listener
    }

    fun beforeTextChanged(editText: EditText, callback: (CharSequence, Int, Int, Int) -> Unit): TextChangeListener {
        val listener = TextChangeListener(beforeChanged = { s: CharSequence, start: Int, before: Int, count: Int ->
            callback(s, start, before, count)
        })

        editText.addTextChangedListener(listener)
        return listener
    }

    fun <T> onItemSelected(spinner: Spinner, callback: (item: T) -> Unit) {
        spinner.onItemSelectedListener = ItemSelectedListener(spinner, callback)
    }
}