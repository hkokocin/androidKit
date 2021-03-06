package com.github.hkokocin.androidkit.support.v4.app

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.support.v4.app.Fragment
import com.github.hkokocin.androidkit.app.getExtra
import com.github.hkokocin.androidkit.app.getResource
import com.github.hkokocin.androidkit.app.start
import com.github.hkokocin.androidkit.app.startForResult
import com.github.hkokocin.androidkit.content.getColorInt

// ==============================================================================
// resources
// ==============================================================================

inline fun <reified T : Any> Fragment.extra(name: String, default: T) = lazy {
    activity?.run { getExtra(intent, name, T::class) } ?: default
}

inline fun <reified T : Any> Fragment.resource(resourcesId: Int) = lazy {
    getResource(resources, resourcesId, T::class)
}

fun Fragment.colorResource(resourcesId: Int, theme: Resources.Theme? = null) = lazy {
    resources.getColorInt(resourcesId, theme)
}

fun Fragment.dimensionInPixels(resourcesId: Int) = lazy {
    resources.getDimensionPixelSize(resourcesId)
}

// ==============================================================================
// intents
// ==============================================================================

inline fun <reified T : Activity> Fragment.start(noinline init: Intent.() -> Unit = {}) {
    activity?.run { start<T>(init) }
}

inline fun <reified T : Activity> Fragment.startForResult(requestCode: Int, noinline init: Intent.() -> Unit = {}) {
    activity?.run { startForResult<T>(requestCode, init) }
}
