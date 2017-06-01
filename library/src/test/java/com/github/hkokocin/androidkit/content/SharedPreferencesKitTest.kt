package com.github.hkokocin.androidkit.content

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.github.hkokocin.androidkit.AndroidKit
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import org.junit.Before
import org.junit.Test

class SharedPreferencesKitTest {

    val editor: SharedPreferences.Editor = mock {
        given { it.putBoolean(any(), any()) }.willReturn(it)
        given { it.putInt(any(), any()) }.willReturn(it)
        given { it.putFloat(any(), any()) }.willReturn(it)
        given { it.putLong(any(), any()) }.willReturn(it)
        given { it.putString(any(), any()) }.willReturn(it)
        given { it.putStringSet(any(), any()) }.willReturn(it)
    }

    @SuppressLint("CommitPrefEdits")
    val preferences: SharedPreferences = mock {
        given { it.edit() }.willReturn(editor)
    }

    val classToTest = object: SharedPreferencesKit{}

    @Test
    fun canApplyBoolean() {
        classToTest.applyBoolean(preferences, "key", true)

        inOrder(editor).apply {
            verify(editor).putBoolean("key", true)
            verify(editor).apply()
        }
    }

    @Test
    fun canApplyInt() {
        classToTest.applyInt(preferences, "key", 1)

        inOrder(editor).apply {
            verify(editor).putInt("key", 1)
            verify(editor).apply()
        }
    }

    @Test
    fun canApplyFloat() {
        classToTest.applyFloat(preferences, "key", 1.0F)

        inOrder(editor).apply {
            verify(editor).putFloat("key", 1.0F)
            verify(editor).apply()
        }
    }

    @Test
    fun canApplyLong() {
        classToTest.applyLong(preferences, "key", 1L)

        inOrder(editor).apply {
            verify(editor).putLong("key", 1L)
            verify(editor).apply()
        }
    }

    @Test
    fun canApplyString() {
        classToTest.applyString(preferences, "key", "string")

        inOrder(editor).apply {
            verify(editor).putString("key", "string")
            verify(editor).apply()
        }
    }

    @Test
    fun canApplyStringSet() {
        classToTest.applyStringSet(preferences, "key", setOf("string"))

        inOrder(editor).apply {
            verify(editor).putStringSet("key", setOf("string"))
            verify(editor).apply()
        }
    }
}

class SharedPreferencesExtensionsTest{

    val kit: AndroidKit = mock()
    val preferences: SharedPreferences = mock()

    @Before
    fun setUp() {
        AndroidKit.instance = kit
    }

    @Test
    fun canApplyBoolean() {
        preferences.applyBoolean("key", true)

        then(kit).should().applyBoolean(preferences, "key", true)
    }

    @Test
    fun canApplyInt() {
        preferences.applyInt("key", 1)

        then(kit).should().applyInt(preferences, "key", 1)
    }

    @Test
    fun canapplyFloat() {
        preferences.applyFloat("key", 1F)

        then(kit).should().applyFloat(preferences, "key", 1F)
    }

    @Test
    fun canapplyLong() {
        preferences.applyLong("key", 1L)

        then(kit).should().applyLong(preferences, "key", 1L)
    }

    @Test
    fun canapplyString() {
        preferences.applyString("key", "string")

        then(kit).should().applyString(preferences, "key", "string")
    }

    @Test
    fun canApplyStringSet() {
        preferences.applyStringSet("key", setOf("string"))

        then(kit).should().applyStringSet(preferences, "key", setOf("string"))
    }

}