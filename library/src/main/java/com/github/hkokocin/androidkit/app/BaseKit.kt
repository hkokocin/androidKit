package com.github.hkokocin.androidkit.app

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.View
import com.github.hkokocin.androidkit.AndroidKit
import com.github.hkokocin.androidkit.content.getColorInt
import java.io.Serializable
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
fun <T : Any> getExtra(type: KClass<T>, name: String, intent: Intent): T?
        = AndroidKit.instance.getExtra(type, name, intent)

@Suppress("UNCHECKED_CAST")
fun <T : Any> getResource(resources: Resources, resourceId: Int, type: KClass<T>)
        = AndroidKit.instance.getResource(resources, resourceId, type)

interface BaseKit {
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
}