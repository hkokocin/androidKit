package com.github.hkokocin.androidkit

import com.github.hkokocin.androidkit.app.ActivityKit
import com.github.hkokocin.androidkit.app.BaseKit
import com.github.hkokocin.androidkit.content.ContextKit
import com.github.hkokocin.androidkit.view.ViewKit
import com.github.hkokocin.androidkit.widget.WidgetKit

//val kit: AndroidKit
//    get() = AndroidKit.instance

class AndroidKit private constructor()
    : BaseKit, WidgetKit, ViewKit, ContextKit, ActivityKit {

    companion object {
        var instance = AndroidKit()
        fun resetToDefault() {
            instance = AndroidKit()
        }
    }
}
