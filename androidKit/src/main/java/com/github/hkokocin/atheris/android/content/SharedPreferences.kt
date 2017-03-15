package com.github.hkokocin.atheris.android.content

import android.content.SharedPreferences

fun SharedPreferences.setLong(key: String, value: Long) {
    edit().putLong(key, value).apply()
}