package com.github.hkokocin.androidkit.widget

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner

fun <T> Spinner.itemSelected(callback: (item: T) -> Unit) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        @Suppress("UNCHECKED_CAST")
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            callback(getItemAtPosition(position) as T)
        }
    }
}
