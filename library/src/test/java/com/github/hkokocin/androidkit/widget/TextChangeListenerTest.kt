package com.github.hkokocin.androidkit.widget

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import org.junit.Test

internal class TextChangeListenerTest {


    @Test
    fun delegatesBeforeTextChangedEvents() {
        val callback: (CharSequence, Int, Int, Int) -> Unit = mock()
        val classToTest = TextChangeListener(beforeChanged = callback)

        classToTest.beforeTextChanged("string", 1, 2, 3)

        then(callback).should().invoke("string", 1, 2, 3)
    }

    @Test
    fun delegatesOnTextChangedEvents() {
        val callback: (CharSequence, Int, Int, Int) -> Unit = mock()
        val classToTest = TextChangeListener(onChanged = callback)

        classToTest.onTextChanged("string", 1, 2, 3)

        then(callback).should().invoke("string", 1, 2, 3)
    }
}