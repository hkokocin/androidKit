package com.github.hkokocin.androidkit.content

import android.content.res.Resources
import android.os.Build

@Suppress("DEPRECATION")
fun Resources.getColorInt(resourcesId: Int, theme: Resources.Theme? = null) =
        if (Build.VERSION.SDK_INT >= 23)
            getColor(resourcesId, theme)
        else
            getColor(resourcesId)