package com.github.hkokocin.androidkit.content;

import android.app.Activity
import android.app.AlarmManager
import android.app.AlertDialog
import android.content.ClipboardManager
import android.content.Context
import android.content.ContextWrapper
import android.location.LocationManager
import android.net.ConnectivityManager
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

val Context.windowManager: WindowManager
    get() = getSystemService(Context.WINDOW_SERVICE) as WindowManager

val Context.inputMethodManager: InputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

val Context.alarmManager: AlarmManager
    get() = getSystemService(Context.ALARM_SERVICE) as AlarmManager

val Context.connectivityManager: ConnectivityManager
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

val Context.locationManager: LocationManager
    get() = getSystemService(Context.LOCATION_SERVICE) as LocationManager

val Context.clipboardManager: ClipboardManager
    get() = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

val Context.displayMetrics: DisplayMetrics
    get() {
        val metrics = DisplayMetrics();
        windowManager.defaultDisplay.getMetrics(metrics);
        return metrics;
    }

fun Context.alertDialog(init: AlertDialog.Builder.() -> Unit): AlertDialog {
    val builder = AlertDialog.Builder(this)
    builder.init()
    val dialog = builder.create()
    dialog.show()
    return dialog
}

fun Context.toActivity(): Activity? {
    return when (this) {
        is Activity -> this
        is ContextWrapper -> this.baseContext.toActivity()
        else -> null
    }
}