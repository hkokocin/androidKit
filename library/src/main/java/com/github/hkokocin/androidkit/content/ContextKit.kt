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
import com.github.hkokocin.androidkit.kit

val Context.windowManager: WindowManager
    get() = kit.getWindowManager(this)

val Context.inputMethodManager: InputMethodManager
    get() = kit.getInputMethodManager(this)

val Context.alarmManager: AlarmManager
    get() = kit.getAlarmManager(this)

val Context.connectivityManager: ConnectivityManager
    get() = kit.getConnectivityManager(this)

val Context.locationManager: LocationManager
    get() = kit.getLocationManager(this)

val Context.clipboardManager: ClipboardManager
    get() = kit.getClipboardManager(this)

val Context.displayMetrics: DisplayMetrics
    get() = kit.getDisplayMetrics(this)

fun Context.alertDialog(init: AlertDialog.Builder.() -> Unit) = kit.alertDialog(this, init)

fun Context.toActivity() = kit.toActivity(this)


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