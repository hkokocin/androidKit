package com.github.hkokocin.androidkit.content

import android.content.SharedPreferences

fun SharedPreferences.setLong(key: String, value: Long) {
    edit().putLong(key, value).apply()
}