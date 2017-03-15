package com.github.hkokocin.viper.android

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem

class ActivityLifecycle {
    var onCreate: (Bundle?) -> Unit = {}
    var onPostCreate: (Bundle?) -> Unit = {}
    var onStart: () -> Unit = {}
    var onResume: () -> Unit = {}
    var onPostResume: () -> Unit = {}
    var onPause: () -> Unit = {}
    var onStop: () -> Unit = {}
    var onDestroy: () -> Unit = {}
    var onSaveInstanceState: (Bundle?) -> Unit = {}
    var onConfigurationChanged: (Configuration?) -> Unit = {}
    var onBackPressed: () -> Unit = {}
    var onOptionsItemSelected: (MenuItem) -> Boolean = { false }
}