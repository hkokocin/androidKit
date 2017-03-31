package com.github.hkokocin.androidkit.widget

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import com.github.hkokocin.androidkit.kit

class ItemSelectedListener<T>(
        private val adapterView: AdapterView<*>,
        private val callback: (item: T) -> Unit
) : AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    @Suppress("UNCHECKED_CAST")
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        callback(adapterView.getItemAtPosition(position) as T)
    }
}