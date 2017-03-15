package com.github.hkokocin.atheris.android.app

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.View
import com.github.hkokocin.atheris.android.content.getColorInt
import java.io.Serializable
import kotlin.reflect.KClass

// ==============================================================================
// resources
// ==============================================================================

@Suppress("UNCHECKED_CAST")
fun <T : View> Activity.viewId(resourcesId: Int) = lazy { findViewById(resourcesId) as T }

inline fun <reified T : Any> Activity.extra(name: String) = lazy { getExtra(T::class, name, intent) }
inline fun <reified T : Any> Activity.extra(name: String, default: T) = lazy {
    getExtra(T::class,
            name,
            intent) ?: default
}

inline fun <reified T : Any> Activity.resource(resourcesId: Int) = lazy {
    getResource(resources,
            resourcesId,
            T::class)
}

@Suppress("UNCHECKED_CAST")
fun Activity.colorResource(resourcesId: Int) = lazy {
    resources.getColorInt(resourcesId)
}

@Suppress("UNCHECKED_CAST")
fun Activity.dimensionInPixels(resourcesId: Int) = lazy {
    resources.getDimensionPixelSize(resourcesId)
}
// ------------------------------------------------------------------------------
// helpers
// ------------------------------------------------------------------------------

@Suppress("UNCHECKED_CAST")
fun <T : Any> getExtra(type: KClass<T>, name: String, intent: Intent): T? = when (type) {
    String::class        -> intent.getStringExtra(name) as T
    Array<String>::class -> intent.getStringArrayExtra(name) as T
    Int::class           -> intent.getIntExtra(name, 0) as T
    Long::class          -> intent.getLongExtra(name, 0) as T
    Boolean::class       -> intent.getBooleanExtra(name, false) as T
    Serializable::class  -> intent.getSerializableExtra(name) as T
    else                 -> throw  IllegalArgumentException("cannot get extra of unknown type ${type.java.name}")
}

@Suppress("UNCHECKED_CAST")
fun <T : Any> getResource(resources: Resources, resourceId: Int, type: KClass<T>) = when (type) {
    String::class        -> resources.getString(resourceId) as T
    Array<String>::class -> resources.getStringArray(resourceId) as T
    Int::class           -> resources.getInteger(resourceId) as T
    Array<Int>::class    -> resources.getIntArray(resourceId).toTypedArray() as T
    Boolean::class       -> resources.getBoolean(resourceId) as T
    Drawable::class      -> resources.getDrawable(resourceId, null) as T
    else                 -> throw IllegalArgumentException("cannot get resource of unknown type ${type.java.name}")
}

// ==============================================================================
// intents
// ==============================================================================

inline fun <reified T : Activity> Activity.start(noinline init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
}

inline fun <reified T : Activity> Activity.startForResult(requestCode: Int, noinline init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivityForResult(intent, requestCode)
}


