package com.github.hkokocin.androidkit.view

import android.content.res.Resources
import android.support.design.widget.Snackbar
import android.view.MotionEvent
import android.view.View
import com.github.hkokocin.androidkit.AndroidKit
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import org.junit.After
import org.junit.Before
import org.junit.Test

class ViewKitTest {

    val message = "message"
    val duration = 1

    val resources: Resources = mock()

    val view: View = mock {
        given { it.resources }.willReturn(resources)
    }

    val snackbar: Snackbar = mock()
    val snackBarProvider: SnackBarProvider = mock {
        given { it.make(view, message, duration) }.willReturn(snackbar)
    }

    val classToTest = object : ViewKit {
        override val snackBarProvider = this@ViewKitTest.snackBarProvider
    }

    @Test
    fun canShowSnackBar() {
        classToTest.snackbar(view, message, duration)

        then(snackbar).should().show()
    }

    @Test
    fun canInitializeSnackBar() {
        classToTest.snackbar(view, message, duration) {
            duration = 2
        }

        then(snackbar).should().duration = 2
    }

    @Test
    fun canShowSnackBarUsingTitleResource() {
        given(resources.getString(1)).willReturn(message)

        classToTest.snackbar(view, 1, duration)

        then(snackbar).should().show()
    }

    @Test
    fun canSetOnClickListener() {
        classToTest.onClick(view) {}

        then(view).should().setOnClickListener(any())
    }

    @Test
    fun canSetOnLongClickListener() {
        classToTest.onLongClick(view) { true }

        then(view).should().setOnLongClickListener(any())
    }

    @Test
    fun canSetOnTouchListener() {
        classToTest.onTouch(view) { _, _ -> true }

        then(view).should().setOnTouchListener(any())
    }

    @Test
    fun canSetOnAttachedToWindowListener() {
        classToTest.onAttachedToWindow(view) {}

        then(view).should().addOnAttachStateChangeListener(any())
    }

    @Test
    fun canSetOnDetachedFromWindowListener() {
        classToTest.onDetachedFromWindow(view) {}

        then(view).should().addOnAttachStateChangeListener(any())
    }

    @Test
    fun canSetOnLayoutChangedListener() {
        classToTest.onLayoutChanged(view) {}

        then(view).should().addOnLayoutChangeListener(any())
    }
}

class ViewExtensionsTest {

    val view: View = mock()
    val kit: AndroidKit = mock()

    @Before
    fun setUp() {
        AndroidKit.instance = kit
    }

    @After
    fun tearDown(){
        AndroidKit.resetToDefault()
    }

    @Test
    fun canShowSnackBar() {
        view.snackbar("message", 1)

        then(kit).should().snackbar(eq(view), eq("message"), eq(1), any())
    }

    @Test
    fun canShowSnackBarWithInitFunction() {
        val init: Snackbar.() -> Unit = {}

        view.snackbar("message", 1, init)

        then(kit).should().snackbar(view, "message", 1, init)
    }

    @Test
    fun canShowSnackBarWithMessageResourceId() {
        view.snackbar(1, 2)

        then(kit).should().snackbar(eq(view), eq(1), eq(2), any())
    }

    @Test
    fun canShowSnackBarWithMessageResourceIdWithInitFunction() {
        val init: Snackbar.() -> Unit = {}

        view.snackbar(1, 2, init)

        then(kit).should().snackbar(view, 1, 2, init)
    }

    @Test
    fun canSetOnClickListener() {
        val listener: (View) -> Unit = {}

        view.onClick(listener)

        then(kit).should().onClick(view, listener)
    }

    @Test
    fun canSetOnLongClickListener() {
        val listener: (View) -> Boolean = { true }

        view.onLongClick(listener)

        then(kit).should().onLongClick(view, listener)
    }

    @Test
    fun canSetOnTouchListener() {
        val listener: (View, MotionEvent) -> Boolean = {_, _ -> true }

        view.onTouch(listener)

        then(kit).should().onTouch(view, listener)
    }

    @Test
    fun canSetonAttachedToWindowListener() {
        val listener: (View) -> Unit = {}

        view.onAttachedToWindow(listener)

        then(kit).should().onAttachedToWindow(view, listener)
    }

    @Test
    fun canSetonDetachedFromWindowListener() {
        val listener: (View) -> Unit = {}

        view.onDetachedFromWindow(listener)

        then(kit).should().onDetachedFromWindow(view, listener)
    }

}