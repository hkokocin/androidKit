package com.github.hkokocin.androidkit.widget

import android.text.Editable
import android.widget.EditText
import android.widget.Spinner
import com.github.hkokocin.androidkit.AndroidKit
import com.github.hkokocin.androidkit.onArgument
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

class WidgetKitTest {

    val editText: EditText = mock()
    val spinner: Spinner = mock()
    val classToTest = object : WidgetKit {}

    @Test
    @Ignore("how do I mock this editable")
    fun canAddAfterTextChangeListenerForEditTexts() {
    val editable: Editable = mock { given { it.toString() }.willReturn("string") }
        val callback: (String) -> Unit = mock()

        given(editText.addTextChangedListener(any()))
                .onArgument(0).ofType<TextChangeListener>()
                .doApply { afterTextChanged(editable) }

        classToTest.afterTextChanged(editText, callback)

        then(callback).should().invoke("string")
    }

    @Test
    fun canAddBeforeTextChangeListenerForEditTexts() {
        val callback: (CharSequence, Int, Int, Int) -> Unit = mock()

        given(editText.addTextChangedListener(any()))
                .onArgument(0).ofType<TextChangeListener>()
                .doApply { beforeTextChanged("string", 1, 2, 3) }

        classToTest.beforeTextChanged(editText, callback)

        then(callback).should().invoke("string", 1, 2, 3)
    }

    @Test
    fun canAddOnTextChangeListenerForEditTexts() {
        val callback: (CharSequence, Int, Int, Int) -> Unit = mock()

        given(editText.addTextChangedListener(any()))
                .onArgument(0).ofType<TextChangeListener>()
                .doApply { onTextChanged("string", 1, 2, 3) }

        classToTest.onTextChanged(editText, callback)

        then(callback).should().invoke("string", 1, 2, 3)
    }

    @Test
    fun canAddItemChangedListenerForSpinners() {
        val callback: (Any) -> Unit = mock()
        val any = Any()

        given(spinner.getItemAtPosition(1)).willReturn(any)

        given(spinner.setOnItemSelectedListener(any()))
                .onArgument(0).ofType<ItemSelectedListener<Any>>()
                .doApply { onItemSelected(null, null, 1, 1) }

        classToTest.onItemSelected(spinner, callback)

        then(callback).should().invoke(any)
    }
}

class WidgetExtensionsTest{

    val kit: AndroidKit = mock()
    val spinner: Spinner = mock()
    val editText: EditText = mock()

    @Before
    fun setUp() {
        AndroidKit.instance = kit
    }

    @After
    fun tearDown() {
        AndroidKit.resetToDefault()
    }

    @Test
    fun canAddAfterTextChangeListenerForEditTexts() {
        val callback: (String) -> Unit = mock()

        editText.afterTextChanged(callback)

        then(kit).should().afterTextChanged(editText, callback)
    }

    @Test
    fun canAddBeforeTextChangeListenerForEditTexts() {
        val callback: (CharSequence, Int, Int, Int) -> Unit = mock()

        editText.beforeTextChanged(callback)

        then(kit).should().beforeTextChanged(editText, callback)
    }

    @Test
    fun canAddOnTextChangeListenerForEditTexts() {
        val callback: (CharSequence, Int, Int, Int) -> Unit = mock()

        editText.onTextChanged(callback)

        then(kit).should().onTextChanged(editText, callback)
    }

    @Test
    fun canAddItemChangedListenerForSpinners() {
        val callback: (Any) -> Unit = mock()

        spinner.onItemSelected(callback)

        then(kit).should().onItemSelected(spinner, callback)
    }

}