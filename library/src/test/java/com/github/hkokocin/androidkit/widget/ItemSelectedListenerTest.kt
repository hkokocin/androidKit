package com.github.hkokocin.androidkit.widget

import android.widget.AdapterView
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import org.junit.Test

internal class ItemSelectedListenerTest {

    val adapterView: AdapterView<*> = mock()
    val callback: (Any) -> Unit = mock()

    val classToTest = ItemSelectedListener(adapterView, callback)

    @Test
    fun delegatesOnItemSelectedEvents() {
        val value = Any()
        given(adapterView.getItemAtPosition(1)).willReturn(value)

        classToTest.onItemSelected(mock(), mock(), 1, 2)

        then(callback).should().invoke(value)
    }
}