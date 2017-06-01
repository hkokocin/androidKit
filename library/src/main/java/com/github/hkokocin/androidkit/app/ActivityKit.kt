package com.github.hkokocin.androidkit.app

import android.app.Activity
import android.content.Intent
import android.view.View
import com.github.hkokocin.androidkit.AndroidKit
import kotlin.reflect.KClass

inline fun <reified T : Activity> Activity.start(noinline init: Intent.() -> Unit = {})
        = AndroidKit.instance.start(this, T::class, init)

inline fun <reified T : Activity> Activity.startForResult(requestCode: Int, noinline init: Intent.() -> Unit = {})
        = AndroidKit.instance.startForResult(this, T::class, requestCode, init)

@Suppress("UNCHECKED_CAST")
fun <T : View> Activity.viewId(resourcesId: Int) = lazy { AndroidKit.instance.viewById<T>(this, resourcesId) as T }

inline fun <reified T : Any> Activity.extra(name: String) = lazy { AndroidKit.instance.extra(this, name, T::class) }
inline fun <reified T : Any> Activity.extra(name: String, default: T) = lazy {
    AndroidKit.instance.extra(this, name, default, T::class)
}

inline fun <reified T : Any> Activity.resource(resourcesId: Int) = lazy {
    AndroidKit.instance.resource(this, T::class, resourcesId)
}

@Suppress("UNCHECKED_CAST")
fun Activity.colorResource(resourcesId: Int) = lazy {
    AndroidKit.instance.getColorInt(resources, resourcesId)
}

@Suppress("UNCHECKED_CAST")
fun Activity.dimensionInPixels(resourcesId: Int) = lazy {
    AndroidKit.instance.dimensionInPixels(resources, resourcesId)
}

class IntentProvider {
    fun <T : Activity> get(activity: Activity, type: Class<T>) = Intent(activity, type)
}

interface ActivityKit {

    val intentProvider: IntentProvider

    fun <T : Activity> start(activity: Activity, type: KClass<T>, init: Intent.() -> Unit = {}) {
        val intent = intentProvider.get(activity, type.java)
        intent.init()
        activity.startActivity(intent)
    }

    fun <T : Activity> startForResult(
            activity: Activity,
            type: KClass<T>,
            requestCode: Int,
            init: Intent.() -> Unit = {}
    ) {
        val intent = intentProvider.get(activity, type.java)
        intent.init()
        activity.startActivityForResult(intent, requestCode)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : View> viewById(activity: Activity, resourcesId: Int) = activity.findViewById(resourcesId) as T

    fun <T : Any> extra(activity: Activity, name: String, type: KClass<T>) =
            AndroidKit.instance.getExtra(activity.intent, name, type)

    fun <T : Any> extra(activity: Activity, name: String, default: T, type: KClass<T>) =
            AndroidKit.instance.getExtra(activity.intent, name, type) ?: default

    fun <T : Any> resource(activity: Activity, type: KClass<T>, resourcesId: Int)
            = AndroidKit.instance.getResource(activity.resources, resourcesId, type)
}

