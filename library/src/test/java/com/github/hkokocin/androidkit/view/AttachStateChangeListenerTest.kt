package com.github.hkokocin.androidkit.view

import android.view.View
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import org.junit.Test

class AttachStateChangeListenerTest{

    val callback: (View) -> Unit = mock()
    val view: View = mock()

    @Test
    fun delagatesAttachedToWindowEvents() {
        val classToTest = AttachStateChangeListener(callback)

        classToTest.onViewAttachedToWindow(view)

        then(callback).should().invoke(view)
    }

    @Test
    fun delagatesDetachedToWindowEvents() {
        val classToTest = AttachStateChangeListener({}, callback)

        classToTest.onViewDetachedFromWindow(view)

        then(callback).should().invoke(view)
    }

}