package com.github.hkokocin.androidkit.content

import android.content.SharedPreferences
import com.github.hkokocin.androidkit.AndroidKit

fun SharedPreferences.applyBoolean(key: String, value: Boolean) =
        AndroidKit.instance.applyBoolean(this, key, value)

fun SharedPreferences.applyInt(key: String, value: Int) =
        AndroidKit.instance.applyInt(this, key, value)

fun SharedPreferences.applyFloat(key: String, value: Float) =
        AndroidKit.instance.applyFloat(this, key, value)

fun SharedPreferences.applyLong(key: String, value: Long) =
        AndroidKit.instance.applyLong(this, key, value)

fun SharedPreferences.applyString(key: String, value: String) =
        AndroidKit.instance.applyString(this, key, value)

fun SharedPreferences.applyStringSet(key: String, value: Set<String>) =
        AndroidKit.instance.applyStringSet(this, key, value)

interface SharedPreferencesKit {
    fun applyBoolean(preferences: SharedPreferences, key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    fun applyInt(preferences: SharedPreferences, key: String, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }

    fun applyFloat(preferences: SharedPreferences, key: String, value: Float) {
        preferences.edit().putFloat(key, value).apply()
    }

    fun applyLong(preferences: SharedPreferences, key: String, value: Long) {
        preferences.edit().putLong(key, value).apply()
    }

    fun applyString(preferences: SharedPreferences, key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun applyStringSet(preferences: SharedPreferences, key: String, value: Set<String>) {
        preferences.edit().putStringSet(key, value).apply()
    }
}