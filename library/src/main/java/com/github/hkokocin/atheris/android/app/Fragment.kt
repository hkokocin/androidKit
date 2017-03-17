package com.github.hkokocin.atheris.android.app

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.content.res.Resources
import com.github.hkokocin.atheris.android.content.getColorInt

// ==============================================================================
// resources
// ==============================================================================

inline fun <reified T : Any> Fragment.extra(name: String, default: T) = lazy {
    getExtra(T::class, name, activity.intent) ?: default
}

inline fun <reified T : Any> Fragment.resource(resourcesId: Int) = lazy {
    getResource(resources,
            resourcesId,
            T::class)
}

@Suppress("UNCHECKED_CAST")
fun Fragment.colorResource(resourcesId: Int, theme: Resources.Theme? = null) = lazy {
    resources.getColorInt(resourcesId, theme)
}

@Suppress("UNCHECKED_CAST")
fun Fragment.dimensionInPixels(resourcesId: Int) = lazy {
    resources.getDimensionPixelSize(resourcesId)
}

// ==============================================================================
// intents
// ==============================================================================

inline fun <reified T : Activity> Fragment.start(noinline init: Intent.() -> Unit = {}) {
    activity.start<T>(init)
}

inline fun <reified T : Activity> Fragment.startForResult(requestCode: Int, noinline init: Intent.() -> Unit = {}) {
    activity.startForResult<T>(requestCode, init)
}
