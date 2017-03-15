package com.github.hkokocin.viper.router

import android.app.Activity
import android.support.v4.app.DialogFragment
import com.github.hkokocin.viper.router.FragmentRouter

open class DialogFragmentRouter(activity: Activity) : FragmentRouter(activity) {
    lateinit var dialog: DialogFragment

    fun dismiss() = dialog.dismiss()
}