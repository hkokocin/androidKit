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
import android.view.Display
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.github.hkokocin.androidkit.AndroidKit
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import com.winterbe.expekt.should
import org.junit.Before
import org.junit.Test

class ContextKitTest {

    val context: Context = mock()
    val dialog: AlertDialog = mock()
    val activity: Activity = mock()
    
    
    val builder: AlertDialog.Builder = mock{
        given { it.create() }.willReturn(dialog)
    }

    val builderProvider: AlertDialogBuilderProvider = mock{
        given { it.get(context) }.willReturn(builder)
    }

    val classToTest = object : ContextKit {
        override val alertDialogBuilderProvider = this@ContextKitTest.builderProvider
    }

    @Test
    fun canGetWindowManager() {
        val windowManager: WindowManager = mock()
        given(context.getSystemService(Context.WINDOW_SERVICE)).willReturn(windowManager)

        val result = classToTest.getWindowManager(context)

        result.should.be.equal(windowManager)
    }

    @Test
    fun canGetInputMethodManager() {
        val windowManager: InputMethodManager = mock()
        given(context.getSystemService(Context.INPUT_METHOD_SERVICE)).willReturn(windowManager)

        val result = classToTest.getInputMethodManager(context)

        result.should.be.equal(windowManager)
    }

    @Test
    fun canGetAlarmManager() {
        val windowManager: AlarmManager = mock()
        given(context.getSystemService(Context.ALARM_SERVICE)).willReturn(windowManager)

        val result = classToTest.getAlarmManager(context)

        result.should.be.equal(windowManager)
    }

    @Test
    fun canGetConnectivityManager() {
        val windowManager: ConnectivityManager = mock()
        given(context.getSystemService(Context.CONNECTIVITY_SERVICE)).willReturn(windowManager)

        val result = classToTest.getConnectivityManager(context)

        result.should.be.equal(windowManager)
    }

    @Test
    fun canGetLocationManager() {
        val windowManager: LocationManager = mock()
        given(context.getSystemService(Context.LOCATION_SERVICE)).willReturn(windowManager)

        val result = classToTest.getLocationManager(context)

        result.should.be.equal(windowManager)
    }

    @Test
    fun canGetClipboardManager() {
        val windowManager: ClipboardManager = mock()
        given(context.getSystemService(Context.CLIPBOARD_SERVICE)).willReturn(windowManager)

        val result = classToTest.getClipboardManager(context)

        result.should.be.equal(windowManager)
    }

    @Test
    fun canCreateAlertDialogs() {
        val result =  classToTest.alertDialog(context){}

        result.should.be.equal(dialog)
    }

    @Test
    fun showsAlertDialogsAutomatically() {
        val dialog = classToTest.alertDialog(context){}

        then(dialog).should().show()
    }

    @Test
    fun canInitializeBuilder() {
        classToTest.alertDialog(context){
            setMessage("message")
        }

        then(builder).should().setMessage("message")
    }

    @Test
    fun canGetDisplayMetrics() {
        val windowManager: WindowManager = mock()
        val defaultDisplay: Display = mock()
        given(context.getSystemService(Context.WINDOW_SERVICE)).willReturn(windowManager)
        given(windowManager.defaultDisplay).willReturn(defaultDisplay)

        val metrics = classToTest.getDisplayMetrics(context)

        then(defaultDisplay).should().getMetrics(metrics)
    }

    @Test
    fun canCastContextToActivity() {
        val context: Context = activity
        
        context.toActivity().should.be.equal(activity)
    }

    @Test
    fun canRetrieveActivityFromContextWrapper() {
        val context: ContextWrapper = mock {
            given { it.baseContext }.willReturn(activity)
        }

        context.toActivity().should.be.equal(activity)
    }

    @Test
    fun fallsBackToNullContextIsNotConnectedToActivity() {
        val context: Context = mock()

        context.toActivity().should.be.`null`
    }
}

class ContextExtensionsTest{

    val kit: AndroidKit = mock()
    val context: Context = mock()

    @Before
    fun setUp() {
        AndroidKit.instance = kit
    }

    @Test
    fun canGetWindowManager() {
        val manager: WindowManager = mock()
        given(kit.getWindowManager(context)).willReturn(manager)

        context.windowManager.should.be.equal(manager)
    }

    @Test
    fun canGetInputMethodManager() {
        val manager: InputMethodManager = mock()
        given(kit.getInputMethodManager(context)).willReturn(manager)

        context.inputMethodManager.should.be.equal(manager)
    }

    @Test
    fun canGetAlarmManager() {
        val manager: AlarmManager = mock()
        given(kit.getAlarmManager(context)).willReturn(manager)

        context.alarmManager.should.be.equal(manager)
    }

    @Test
    fun canGetConnectivityManager() {
        val manager: ConnectivityManager = mock()
        given(kit.getConnectivityManager(context)).willReturn(manager)

        context.connectivityManager.should.be.equal(manager)
    }

    @Test
    fun canGetLocationManager() {
        val manager: LocationManager = mock()
        given(kit.getLocationManager(context)).willReturn(manager)

        context.locationManager.should.be.equal(manager)
    }

    @Test
    fun canGetClipboardManager() {
        val manager: ClipboardManager = mock()
        given(kit.getClipboardManager(context)).willReturn(manager)

        context.clipboardManager.should.be.equal(manager)
    }

    @Test
    fun canGetDisplayMetrics() {
        val manager: DisplayMetrics = mock()
        given(kit.getDisplayMetrics(context)).willReturn(manager)

        context.displayMetrics.should.be.equal(manager)
    }

    @Test
    fun canCreateAlertDialog() {
        val init: AlertDialog.Builder.() -> Unit = {}
        val dialog: AlertDialog = mock()
        given(kit.alertDialog(context, init)).willReturn(dialog)

        val result = context.alertDialog(init)

        result.should.be.equal(dialog)
    }

    @Test
    fun canGetActivityFromContext() {
        val activity: Activity = mock()
        given(kit.toActivity(context)).willReturn(activity)

        val result = context.toActivity()

        result.should.be.equal(activity)

    }

}