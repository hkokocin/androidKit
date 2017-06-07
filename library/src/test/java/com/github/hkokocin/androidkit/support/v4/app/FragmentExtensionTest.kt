package com.github.hkokocin.androidkit.support.v4.app

import android.content.Intent
import android.content.res.Resources
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.github.hkokocin.androidkit.AndroidKit
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import com.winterbe.expekt.should
import org.junit.Before
import org.junit.Test

internal class FragmentExtensionTest {

    val kit: AndroidKit = mock()
    val resources: Resources = mock()
    val intent: Intent = mock()

    val activity: AppCompatActivity = mock {
        given { it.intent }.willReturn(intent)
    }

    val fragment: Fragment = mock {
        given { it.resources }.willReturn(resources)
        given { it.activity }.willReturn(activity)
    }

    @Before
    fun setUp() {
        AndroidKit.instance = kit
    }

    @Test
    fun canGetExtra() {
        given(kit.getExtra(intent, "name", String::class)).willReturn("extra")

        val result by fragment.extra("name", "")

        result.should.be.equal("extra")
    }

    @Test
    fun fallsBackToDefaultIfExtraMissing() {
        val result by fragment.extra("name", "default")

        result.should.be.equal("default")
    }

    @Test
    fun canGetResource() {
        given(kit.getResource(resources, 1, String::class)).willReturn("resource")

        val result by fragment.resource<String>(1)

        result.should.be.equal("resource")
    }

    @Test
    fun canGetColor() {
        given(kit.getColorInt(resources, 1, null)).willReturn(2)

        val result by fragment.colorResource(1)

        result.should.be.equal(2)
    }

    @Test
    fun canGetDimension() {
        given(resources.getDimensionPixelSize(1)).willReturn(2)

        val result by fragment.dimensionInPixels(1)

        result.should.be.equal(2)
    }

    @Test
    fun canStartActivity() {
        val init: Intent.() -> Unit = {}

        fragment.start<AppCompatActivity>(init)

        then(kit).should().start(activity, AppCompatActivity::class, init)
    }

    @Test
    fun canStartActivityForResult() {
        val init: Intent.() -> Unit = {}

        fragment.startForResult<AppCompatActivity>(1, init)

        then(kit).should().startForResult(activity, AppCompatActivity::class, 1, init)
    }

}