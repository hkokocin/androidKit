package com.github.hkokocin.viper.router

import android.app.Activity
import com.github.hkokocin.viper.router.Router
import com.github.hkokocin.viper.android.FragmentLifecycle

open class FragmentRouter(activity: Activity) : Router(activity) {
    val lifecycle = FragmentLifecycle()
}