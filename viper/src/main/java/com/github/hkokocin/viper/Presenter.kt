package com.github.hkokocin.viper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.hkokocin.atheris.android.content.getColorInt
import kotlin.reflect.KClass

abstract class Presenter(
        private val layoutResource: Int,
        protected val context: Context
) {
    protected lateinit var rootView: View
    var viewCreated: Boolean = false
        private set

    protected open fun onViewCreated() {}

    @Suppress("UNCHECKED_CAST")
    protected fun <T : View> viewId(resourcesId: Int) = lazy { rootView.findViewById(resourcesId) as T }

    protected inline fun <reified T : Any> resource(resourcesId: Int) = lazy {
        getResource(T::class, resourcesId)
    }

    protected inline fun <reified T : Any> getResource(resourcesId: Int) = getResource(T::class, resourcesId)

    fun <T : Any> getResource(type: KClass<T>, resourcesId: Int): T {
        return com.github.hkokocin.atheris.android.app.getResource(
                context.resources,
                resourcesId,
                type)
    }

    @Suppress("UNCHECKED_CAST")
    protected fun colorResource(resourcesId: Int) = lazy {
        context.resources.getColorInt(resourcesId)
    }

    @Suppress("UNCHECKED_CAST")
    protected fun dimensionInPixels(resourcesId: Int) = lazy {
        context.resources.getDimensionPixelSize(resourcesId)
    }

    fun createView(inflater: LayoutInflater, parent: ViewGroup? = null): View {
        rootView = inflater.inflate(layoutResource, parent, false)
        viewCreated = true
        onViewCreated()
        return rootView
    }
}