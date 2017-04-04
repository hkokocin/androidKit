package com.github.hkokocin.androidkit.app

import android.app.Activity
import android.content.Intent
import android.view.View
import com.github.hkokocin.androidkit.AndroidKit
import com.github.hkokocin.androidkit.content.getColorInt
import kotlin.reflect.KClass

inline fun <reified T : Activity> Activity.start(noinline init: Intent.() -> Unit = {})
        = AndroidKit.instance.start(this, T::class, init)

inline fun <reified T : Activity> Activity.startForResult(requestCode: Int, noinline init: Intent.() -> Unit = {})
        = AndroidKit.instance.startForResult(this, T::class, requestCode, init)

@Suppress("UNCHECKED_CAST")
fun <T : View> Activity.viewId(resourcesId: Int) = lazy { AndroidKit.instance.viewById<T>(this, resourcesId) as T }

inline fun <reified T : Any> Activity.extra(name: String) = lazy { AndroidKit.instance.extra(this, T::class, name) }
inline fun <reified T : Any> Activity.extra(name: String, default: T) = lazy {
    AndroidKit.instance.extra(this, T::class, name, default)
}

inline fun <reified T : Any> Activity.resource(resourcesId: Int) = lazy {
    AndroidKit.instance.resource(this, T::class, resourcesId)
}

@Suppress("UNCHECKED_CAST")
fun Activity.colorResource(resourcesId: Int) = lazy {
    AndroidKit.instance.colorResource(this, resourcesId)
}

@Suppress("UNCHECKED_CAST")
fun Activity.dimensionInPixels(resourcesId: Int) = lazy {
    AndroidKit.instance.dimensionInPixels(this, resourcesId)
}

interface ActivityKit {

    fun <T : Activity> start(activity: Activity, type: KClass<T>, init: Intent.() -> Unit = {}) {
        val intent = Intent(activity, type.java)
        intent.init()
        activity.startActivity(intent)
    }

    fun <T : Activity> startForResult(activity: Activity,
            type: KClass<T>,
            requestCode: Int,
            init: Intent.() -> Unit = {}) {
        val intent = Intent(activity, type.java)
        intent.init()
        activity.startActivityForResult(intent, requestCode)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : View> viewById(activity: Activity, resourcesId: Int) = activity.findViewById(resourcesId) as T

    fun <T : Any> extra(activity: Activity, type: KClass<T>, name: String) = getExtra(type, name, activity.intent)

    fun <T : Any> extra(activity: Activity, type: KClass<T>, name: String, default: T) = getExtra(
            type,
            name,
            activity.intent
    ) ?: default

    fun <T : Any> resource(activity: Activity, type: KClass<T>, resourcesId: Int) = getResource(
            activity.resources,
            resourcesId,
            type
    )

    @Suppress("UNCHECKED_CAST")
    fun colorResource(activity: Activity, resourcesId: Int) = activity.resources.getColorInt(resourcesId)

    @Suppress("UNCHECKED_CAST")
    fun dimensionInPixels(activity: Activity, resourcesId: Int) = activity.resources.getDimensionPixelSize(resourcesId)
}

