package com.example.hackernews.utils

import android.content.Context
import android.content.SharedPreferences

class SharePref(context: Context) {

    private var sharedpreferences: SharedPreferences

    companion object {
        const val PREFERENCE_NAME = "PREFERENCE_DATA"
    }

    init {
        sharedpreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun setString(value: String, key: String) {
        val editor = sharedpreferences.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    fun getString(key: String): String? {
        return sharedpreferences.getString(key, "Tidak Ada Data")
    }

    fun clear() {
        val editor = sharedpreferences.edit()
        editor.clear()
        editor.apply()
    }
}