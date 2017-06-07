package com.github.hkokocin.androidkit.widget

import android.text.Editable
import android.widget.EditText
import android.widget.Spinner
import com.github.hkokocin.androidkit.onArgument
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import org.junit.Test

class WidgetKitTest {

    val editable: Editable = mock { given { it.toString() }.willReturn("string") }
    val editText: EditText = mock()
    val spinner: Spinner = mock()
    val classToTest = object : WidgetKit {}

    @Test
    fun canAddAfterTextChangeListener() {
        val callback: (String) -> Unit = mock()

        given(editText.addTextChangedListener(any()))
                .onArgument(0).ofType<TextChangeListener>()
                .doApply { afterTextChanged(editable) }

        classToTest.onAfterTextChanged(editText, callback)

        then(callback).should().invoke("string")
    }
}