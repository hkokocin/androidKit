package com.github.hkokocin.androidkit.widget

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.github.hkokocin.androidkit.AndroidKit
import com.github.hkokocin.androidkit.kit

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