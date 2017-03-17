package com.github.hkokocin.androidkit.widget

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.textChange(callback: (String) -> Unit) {
    addTextChangedListener(TextChangeListener(onChanged = { callback(it) }))
}

fun EditText.beforeTextChange(callback: () -> Unit) {
    addTextChangedListener(TextChangeListener(beforeChanged = { callback() }))
}

fun EditText.afterTextChange(callback: (String) -> Unit) {
    addTextChangedListener(TextChangeListener(afterChanged = { callback(it) }))
}

class TextChangeListener(
        private val onChanged: (String) -> Unit = {},
        private val beforeChanged: (String) -> Unit = {},
        private val afterChanged: (String) -> Unit = {}
) : TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        afterChanged(s.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeChanged(s.toString())
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onChanged(s.toString())
    }
}