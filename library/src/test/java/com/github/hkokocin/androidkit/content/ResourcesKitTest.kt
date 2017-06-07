package com.github.hkokocin.androidkit.content

import android.annotation.SuppressLint
import android.content.res.Resources
import com.github.hkokocin.androidkit.AndroidKit
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.winterbe.expekt.should
import org.junit.After
import org.junit.Before
import org.junit.Test

class ResourcesKitTest {

    val resources: Resources = mock()
    var version = 0
    val classToTest = object : ResourcesKit {
        override val sdkVersion: Int
            get() = version
    }

    @Test
    fun canGetColorIntForOldAndroidVersions() {
        version = 10
        given(resources.getColor(1)).willReturn(2)

        val result = classToTest.getColorInt(resources, 1)

        result.should.be.equal(2)
    }

    @SuppressLint("NewApi")
    @Test
    fun canGetColorIntForNewAndroidVersions() {
        version = 23
        given(resources.getColor(1, null)).willReturn(2)

        val result = classToTest.getColorInt(resources, 1, null)

        result.should.be.equal(2)
    }
}

class ResourcesExtensionsTest {

    val kit: AndroidKit = mock()
    val resources: Resources = mock()

    @Before
    fun setUp() {
        AndroidKit.instance = kit
    }

    @After
    fun tearDown(){
        AndroidKit.resetToDefault()
    }

    @Test
    fun canGetColorInt() {
        given(kit.getColorInt(resources, 1, null)).willReturn(2)

        val result = resources.getColorInt(1)

        result.should.be.equal(2)
    }
}