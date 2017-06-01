package com.github.hkokocin.androidkit.app

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.content.res.Resources
import com.github.hkokocin.androidkit.AndroidKit

// ==============================================================================
// resources
// ==============================================================================

inline fun <reified T : Any> Fragment.extra(name: String, default: T) = lazy {
    AndroidKit.instance.getExtra(activity.intent, name, T::class) ?: default
}

inline fun <reified T : Any> Fragment.resource(resourcesId: Int) = lazy {
    AndroidKit.instance.getResource(resources, resourcesId, T::class)
}

@Suppress("UNCHECKED_CAST")
fun Fragment.colorResource(resourcesId: Int, theme: Resources.Theme? = null) = lazy {
    AndroidKit.instance.getColorInt(resources, resourcesId, theme)
}

@Suppress("UNCHECKED_CAST")
fun Fragment.dimensionInPixels(resourcesId: Int) = lazy {
    AndroidKit.instance.dimensionInPixels(resources, resourcesId)
}

// ==============================================================================
// intents
// ==============================================================================

inline fun <reified T : Activity> Fragment.start(noinline init: Intent.() -> Unit = {}) {
    AndroidKit.instance.start(activity, T::class, init)
}

inline fun <reified T : Activity> Fragment.startForResult(requestCode: Int, noinline init: Intent.() -> Unit = {}) {
    AndroidKit.instance.startForResult(activity, T::class, requestCode, init)
}
