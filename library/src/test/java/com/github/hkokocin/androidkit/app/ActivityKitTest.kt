package com.github.hkokocin.androidkit.app

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.hkokocin.androidkit.AndroidKit
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import com.winterbe.expekt.should
import org.junit.After
import org.junit.Before
import org.junit.Test


class ActivityKitTest {

    val intent: Intent = mock()
    val resources: Resources = mock()
    val kit: AndroidKit = mock()

    @Before
    fun setUp() {
        AndroidKit.instance = kit
    }

    @After
    fun tearDown(){
        AndroidKit.resetToDefault()
    }

    val activity: Activity = mock {
        given { it.intent }.willReturn(intent)
        given { it.resources }.willReturn(resources)
    }

    val classToTest = object : ActivityKit {
        override val intentProvider: IntentProvider = mock {
            given { it.get(activity, Activity::class.java) }.willReturn(intent)
        }
    }

    @Test
    fun canStartActivity() {
        classToTest.start(activity, Activity::class)

        then(activity).should().startActivity(intent)
    }

    @Test
    fun canModifyIntentBeforeStartActivity() {
        classToTest.start(activity, Activity::class) {
            putExtra("key", "value")
        }

        then(intent).should().putExtra("key", "value")
    }

    @Test
    fun canStartActivityForResult() {
        classToTest.startForResult(activity, Activity::class, 1)

        then(activity).should().startActivityForResult(intent, 1)
    }

    @Test
    fun canModifyIntentBeforeStartActivityForResult() {
        classToTest.startForResult(activity, Activity::class, 0) {
            putExtra("key", "value")
        }

        then(intent).should().putExtra("key", "value")
    }

    @Test
    fun canRetrieveViews() {
        val view = mock<View>()
        given(activity.findViewById<View>(1)).willReturn(view)

        val result = classToTest.viewById<View>(activity, 1)

        result.should.be.equal(view)
    }

    @Test
    fun canGetExtra() {
        given(kit.getExtra(intent, "name", String::class)).willReturn("extra")

        val extra = classToTest.extra(activity, "name", String::class)

        extra.should.be.equal("extra")
    }

    @Test
    fun canGetFallbackToDefaultWhenGettingExtra() {
        val extra = classToTest.extra(activity, "name", "default", String::class)

        extra.should.be.equal("default")
    }

    @Test
    fun canGetResource() {
        given(kit.getResource(resources, 1, String::class)).willReturn("string")

        val result = classToTest.resource(activity, String::class, 1)

        result.should.be.equal("string")
    }
}

class ActivityKitExtensionsTest {

    val resources: Resources = mock()
    val kit: AndroidKit = mock()
    val activity: Activity = mock{
        given{ it.resources }.willReturn(resources)
    }

    @Before
    fun setUp() {
        AndroidKit.instance = kit
    }

    @Test
    fun canStartActivity() {
        val init: Intent.() -> Unit = {}

        activity.start<AppCompatActivity>(init)

        then(kit).should().start(activity, AppCompatActivity::class, init)
    }

    @Test
    fun canStartActivityForResult() {
        val init: Intent.() -> Unit = {}

        activity.startForResult<AppCompatActivity>(1, init)

        then(kit).should().startForResult(activity, AppCompatActivity::class, 1, init)
    }

    @Test
    fun canGetViewById() {
        val view: View = mock()
        given(kit.viewById<View>(activity, 1)).willReturn(view)

        val result: View by activity.viewId<View>(1)

        result.should.be.equal(view)
    }

    @Test
    fun canGetExtra() {
        given(kit.extra(activity, "key", String::class)).willReturn("value")

        val result by activity.extra<String>("key")

        result.should.be.equal("value")
    }

    @Test
    fun canGetExtraWithDefaultValue() {
        given(kit.extra(activity, "key", "default", String::class)).willReturn("value")

        val result by activity.extra<String>("key", "default")

        result.should.be.equal("value")
    }

    @Test
    fun canGetResource() {
        given(kit.resource(activity, String::class, 1)).willReturn("value")

        val result: String by activity.resource(1)

        result.should.be.equal("value")
    }

    @Test
    fun canGetColorResource() {
        given(kit.getColorInt(resources, 1)).willReturn(2)

        val result by activity.colorResource(1)

        result.should.be.equal(2)
    }

    @Test
    fun canGetDimensionInPixels() {
        given(kit.dimensionInPixels(resources, 1)).willReturn(2)

        val result by activity.dimensionInPixels(1)

        result.should.be.equal(2)
    }
}