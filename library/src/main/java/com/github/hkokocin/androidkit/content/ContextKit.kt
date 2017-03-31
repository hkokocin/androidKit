package com.github.hkokocin.androidkit.content

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
import com.github.hkokocin.androidkit.AndroidKit

val Context.windowManager: WindowManager
    get() = AndroidKit.instance.getWindowManager(this)

val Context.inputMethodManager: InputMethodManager
    get() = AndroidKit.instance.getInputMethodManager(this)

val Context.alarmManager: AlarmManager
    get() = AndroidKit.instance.getAlarmManager(this)

val Context.connectivityManager: ConnectivityManager
    get() = AndroidKit.instance.getConnectivityManager(this)

val Context.locationManager: LocationManager
    get() = AndroidKit.instance.getLocationManager(this)

val Context.clipboardManager: ClipboardManager
    get() = AndroidKit.instance.getClipboardManager(this)

val Context.displayMetrics: DisplayMetrics
    get() = AndroidKit.instance.getDisplayMetrics(this)

fun Context.alertDialog(init: AlertDialog.Builder.() -> Unit) = AndroidKit.instance.alertDialog(this, init)

fun Context.toActivity() = AndroidKit.instance.toActivity(this)


interface ContextKit {

    fun  getWindowManager(context: Context) = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    fun  getInputMethodManager(context: Context) = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    fun  getAlarmManager(context: Context) = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun  getConnectivityManager(context: Context) = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun  getLocationManager(context: Context) = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    fun  getClipboardManager(context: Context) = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    fun  getDisplayMetrics(context: Context): DisplayMetrics {
            val metrics = DisplayMetrics()
            context.windowManager.defaultDisplay.getMetrics(metrics)
            return metrics
        }

    fun alertDialog(context: Context, init: AlertDialog.Builder.() -> Unit): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.init()
        val dialog = builder.create()
        dialog.show()
        return dialog
    }

    fun toActivity(context: Context): Activity? {
        return when (context) {
            is Activity       -> context
            is ContextWrapper -> context.baseContext.toActivity()
            else              -> null
        }
    }
}