package com.github.hkokocin.androidkit.view

import android.support.design.widget.Snackbar
import android.view.MotionEvent
import android.view.View
import com.github.hkokocin.androidkit.AndroidKit

fun View.snackbar(message: CharSequence, duration: Int = Snackbar.LENGTH_LONG, init: Snackbar.() -> Unit = {}) {
    return AndroidKit.instance.snackbar(this, message, duration, init)
}

fun View.snackbar(messageId: Int, duration: Int = Snackbar.LENGTH_LONG, init: Snackbar.() -> Unit = {}) {
    return AndroidKit.instance.snackbar(this, messageId, duration, init)
}

fun View.onClick(listener: (View) -> Unit) = AndroidKit.instance.onClick(this, listener)

fun View.onLongClick(callback: (View) -> Boolean) = AndroidKit.instance.onLongClick(this, callback)

fun View.onTouch(callback: (View, MotionEvent) -> Boolean) = AndroidKit.instance.onTouch(this, callback)

fun View.onAttachedToWindow(callback: (view: View) -> Unit) = AndroidKit.instance.onAttachedToWindow(this, callback)

fun View.onDetachedFromWindow(callback: (view: View) -> Unit) = AndroidKit.instance.onDetachedFromWindow(this, callback)

@Suppress("UNUSED_PARAMETER")
fun View.onLayoutChanged(callback: () -> Unit) = AndroidKit.instance.onLayoutChanged(this, callback)

interface ViewKit {

    fun snackbar(
            view: View,
            message: CharSequence,
            duration: Int = Snackbar.LENGTH_LONG,
            init: Snackbar.() -> Unit = {}
    ) {
        val snackBar = Snackbar.make(view, message, duration)
        snackBar.init()
        snackBar.show()
    }

    fun snackbar(view: View, messageId: Int, duration: Int = Snackbar.LENGTH_LONG, init: Snackbar.() -> Unit = {}) {
        snackbar(view, view.resources.getString(messageId), duration, init)
    }

    fun onClick(view: View, listener: (View) -> Unit) = view.setOnClickListener(listener)

    fun onLongClick(view: View, listener: (View) -> Boolean) = view.setOnLongClickListener(listener)

    fun onTouch(view: View, listener: (View, MotionEvent) -> Boolean) = view.setOnTouchListener(listener)

    fun onAttachedToWindow(view: View, callback: (view: View) -> Unit) {
        view.addOnAttachStateChangeListener(AttachStateChangeListener(onAttached = callback))
    }

    fun onDetachedFromWindow(view: View, callback: (view: View) -> Unit) {
        view.addOnAttachStateChangeListener(AttachStateChangeListener(onDetached = callback))
    }

    @Suppress("UNUSED_PARAMETER")
    fun onLayoutChanged(view: View, callback: () -> Unit) {
        view.addOnLayoutChangeListener { view, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom -> callback() }
    }
}