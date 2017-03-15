package com.github.hkokocin.viper.android

import android.os.Bundle
import android.view.MenuItem

class FragmentLifecycle {
    var onCreate: (Bundle?) -> Unit = {}
    var onStart: () -> Unit = {}
    var onResume: () -> Unit = {}
    var onPause: () -> Unit = {}
    var onStop: () -> Unit = {}
    var onDestroy: () -> Unit = {}
    var onSaveInstanceState: (Bundle?) -> Unit = {}
    var onBackPressed: () -> Unit = {}
    var onOptionsItemSelected: (MenuItem?) -> Boolean = { false }
}