package com.github.hkokocin.androidkit.view

import android.support.design.widget.Snackbar
import android.view.MotionEvent
import android.view.View

fun View.snackbar(message: CharSequence, duration: Int = Snackbar.LENGTH_LONG, init: Snackbar.() -> Unit = {}) {
    val snackBar = Snackbar.make(this, message, duration)
    snackBar.init()
    snackBar.show()
}

fun View.snackbar(messageId: Int, duration: Int = Snackbar.LENGTH_LONG, init: Snackbar.() -> Unit = {}) {
    snackbar(resources.getString(messageId), duration, init)
}

fun View.onClick(listener: (View) -> Unit) {
    setOnClickListener(listener)
}

fun View.onLongClick(listener: (View) -> Unit) {
    setOnLongClickListener {
        listener.invoke(it)
        true
    }
}

fun View.onTouch(listener: (View, MotionEvent) -> Boolean) {
    setOnTouchListener(listener)
}

fun View.onAttachedToWindow(listener: (view: View) -> Unit) {
    addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
        override fun onViewDetachedFromWindow(view: View) {
        }

        override fun onViewAttachedToWindow(view: View) {
            listener(view)
        }
    })
}

fun View.onDetachedFromWindow(listener: (view: View) -> Unit) {
    addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
        override fun onViewDetachedFromWindow(view: View) {
            listener(view)
        }

        override fun onViewAttachedToWindow(view: View) {
        }
    })
}

@Suppress("UNUSED_PARAMETER")
fun View.onLayoutChanged(callback: () -> Unit) {
    addOnLayoutChangeListener { view, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom -> callback() }
}
