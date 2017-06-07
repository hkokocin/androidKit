package com.github.hkokocin.androidkit

import android.os.Build
import com.github.hkokocin.androidkit.app.ActivityKit
import com.github.hkokocin.androidkit.app.BaseKit
import com.github.hkokocin.androidkit.app.IntentProvider
import com.github.hkokocin.androidkit.content.AlertDialogBuilderProvider
import com.github.hkokocin.androidkit.content.ContextKit
import com.github.hkokocin.androidkit.content.ResourcesKit
import com.github.hkokocin.androidkit.content.SharedPreferencesKit
import com.github.hkokocin.androidkit.view.SnackBarProvider
import com.github.hkokocin.androidkit.view.ViewKit
import com.github.hkokocin.androidkit.widget.WidgetKit

class AndroidKit private constructor()
    : BaseKit, WidgetKit, ViewKit, ContextKit, ActivityKit, ResourcesKit, SharedPreferencesKit {

    override val snackBarProvider = SnackBarProvider()
    override val alertDialogBuilderProvider = AlertDialogBuilderProvider()
    override val intentProvider = IntentProvider()
    override val sdkVersion = Build.VERSION.SDK_INT

    companion object {
        var instance = AndroidKit()

        fun resetToDefault() {
            instance = AndroidKit()
        }
    }
}
