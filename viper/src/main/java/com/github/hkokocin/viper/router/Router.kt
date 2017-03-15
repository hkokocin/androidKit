package com.github.hkokocin.viper.router

import android.app.Activity
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import com.github.hkokocin.atheris.android.app.getExtra
import com.github.hkokocin.atheris.android.app.getResource
import com.github.hkokocin.atheris.android.content.getColorInt
import kotlin.reflect.KClass

open class Router(
        private val activity: Activity
) {

    val packageName: String = activity.packageName

    fun finishActivity() = activity.finish()

    // ==============================================================================
    // Actionbar
    // ==============================================================================

    fun setTitle(title: String) {
        activity.actionBar?.title = title
        if (activity is AppCompatActivity) {
            activity.supportActionBar?.title = title
        }
    }

    fun setSubTitle(subtitle: String) {
        activity.actionBar?.subtitle = subtitle
        if (activity is AppCompatActivity) {
            activity.supportActionBar?.subtitle = subtitle
        }
    }

    // ==============================================================================
    // Resources
    // ==============================================================================

    val resources: Resources
        get() = activity.resources

    inline fun <reified T : Any> extra(name: String, default: T) = extra(name, default, T::class)
    fun <T : Any> extra(name: String, default: T, type: KClass<T>) = getExtra(type, name, activity.intent) ?: default

    inline fun <reified T : Any> resource(resourcesId: Int) = resource(resourcesId, T::class)
    fun <T : Any> resource(resourcesId: Int, type: KClass<T>) = getResource(resources, resourcesId, type)

    fun colorResource(resourcesId: Int, theme: Resources.Theme? = null) = resources.getColorInt(resourcesId, theme)

    fun dimensionInPixels(resourcesId: Int) = resources.getDimensionPixelSize(resourcesId)
}