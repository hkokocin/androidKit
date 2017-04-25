package com.github.hkokocin.androidkit.app

import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import com.github.hkokocin.androidkit.AndroidKit
import java.io.Serializable
import kotlin.reflect.KClass

fun <T : Any> getExtra(type: KClass<T>, name: String, intent: Intent): T?
        = AndroidKit.instance.getExtra(type, name, intent)

fun <T : Any> getResource(resources: Resources, resourceId: Int, type: KClass<T>)
        = AndroidKit.instance.getResource(resources, resourceId, type)

interface BaseKit {
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getExtra(type: KClass<T>, name: String, intent: Intent): T? = when (type) {
        Char::class                -> intent.getCharExtra(name, ' ') as T?
        Array<Char>::class         -> intent.getCharArrayExtra(name) as T?
        String::class              -> intent.getStringExtra(name) as T?
        Array<String>::class       -> intent.getStringArrayExtra(name) as T?
        CharSequence::class        -> intent.getCharSequenceExtra(name) as T?
        Array<CharSequence>::class -> intent.getCharSequenceArrayExtra(name) as T?
        Short::class               -> intent.getShortExtra(name, 0) as T?
        Array<Short>::class        -> intent.getShortArrayExtra(name) as T?
        Int::class                 -> intent.getIntExtra(name, 0) as T?
        Array<Int>::class          -> intent.getIntArrayExtra(name) as T?
        Long::class                -> intent.getLongExtra(name, 0) as T?
        Array<Long>::class         -> intent.getLongArrayExtra(name) as T?
        Double::class              -> intent.getDoubleExtra(name, 0.0) as T?
        Array<Double>::class       -> intent.getDoubleArrayExtra(name) as T?
        Float::class               -> intent.getFloatExtra(name, 0f) as T?
        Array<Float>::class        -> intent.getFloatArrayExtra(name) as T?
        Boolean::class             -> intent.getBooleanExtra(name, false) as T?
        Array<Boolean>::class      -> intent.getBooleanArrayExtra(name) as T?
        Byte::class                -> intent.getByteExtra(name, 0) as T?
        Array<Byte>::class         -> intent.getByteArrayExtra(name) as T?
        Serializable::class        -> intent.getSerializableExtra(name) as T?
        Bundle::class              -> intent.getBundleExtra(name) as T?
        else                       -> throw  IllegalArgumentException("cannot get extra of unknown type ${type.java.name}")
    }

    @Suppress("UNCHECKED_CAST", "DEPRECATION")
    fun <T : Any> getResource(resources: Resources, resourceId: Int, type: KClass<T>) = when (type) {
        String::class              -> resources.getString(resourceId) as T
        Array<String>::class       -> resources.getStringArray(resourceId) as T
        CharSequence::class        -> resources.getText(resourceId) as T
        Array<CharSequence>::class -> resources.getTextArray(resourceId) as T
        Int::class                 -> resources.getInteger(resourceId) as T
        Array<Int>::class          -> resources.getIntArray(resourceId).toTypedArray() as T
        Boolean::class             -> resources.getBoolean(resourceId) as T
        Drawable::class            -> resources.getDrawable(resourceId) as T
        else                       -> throw IllegalArgumentException("cannot get resource of unknown type ${type.java.name}")
    }
}