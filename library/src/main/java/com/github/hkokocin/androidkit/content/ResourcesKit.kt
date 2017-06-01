package com.github.hkokocin.androidkit.content

import android.annotation.SuppressLint
import android.content.res.Resources
import com.github.hkokocin.androidkit.AndroidKit

@Suppress("DEPRECATION")
fun Resources.getColorInt(resourcesId: Int, theme: Resources.Theme? = null) =
        AndroidKit.instance.getColorInt(this, resourcesId, theme)

interface ResourcesKit {

    val sdkVersion: Int

    @SuppressLint("NewApi")
    @Suppress("DEPRECATION")
    fun getColorInt(resources: Resources, resourcesId: Int, theme: Resources.Theme? = null) =
            if (sdkVersion >= 23)
                resources.getColor(resourcesId, theme)
            else
                resources.getColor(resourcesId)
}