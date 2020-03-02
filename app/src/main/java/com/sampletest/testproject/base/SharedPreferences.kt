package com.sampletest.testproject.base

import android.content.Context
import android.content.SharedPreferences
import com.sampletest.testproject.singleton.AppsToken

class SharedPreferences {
    fun getBooleanPref(key: String) : Boolean {
        val pref: SharedPreferences? = AppsToken.context.getSharedPreferences(AppsToken.PREF_NAME, Context.MODE_PRIVATE)
        return pref?.getBoolean(key, false) ?: false
    }

    fun setBooleanPref(key: String, value: Boolean) {
        val pref: SharedPreferences? = AppsToken.context.getSharedPreferences(AppsToken.PREF_NAME, Context.MODE_PRIVATE)
        pref?.edit()?.putBoolean(key, value)?.apply()
    }

    fun getStringPref(key: String) : String {
        val pref: SharedPreferences? = AppsToken.context.getSharedPreferences(AppsToken.PREF_NAME, Context.MODE_PRIVATE)
        return pref?.getString(key, "") ?: ""
    }

    fun setStringPref(key: String, value: String) {
        val pref: SharedPreferences? = AppsToken.context.getSharedPreferences(AppsToken.PREF_NAME, Context.MODE_PRIVATE)
        pref?.edit()?.putString(key, value)?.apply()
    }

    fun getIntPref(key: String) : Int {
        val pref: SharedPreferences? = AppsToken.context.getSharedPreferences(AppsToken.PREF_NAME, Context.MODE_PRIVATE)
        return pref?.getInt(key, 0) ?: 0
    }

    fun setIntPref(key: String, value: Int) {
        val pref: SharedPreferences? = AppsToken.context.getSharedPreferences(AppsToken.PREF_NAME, Context.MODE_PRIVATE)
        pref?.edit()?.putInt(key, value)?.apply()
    }

    fun getLongPref(key: String) : Long {
        val pref: SharedPreferences? = AppsToken.context.getSharedPreferences(AppsToken.PREF_NAME, Context.MODE_PRIVATE)
        return pref?.getLong(key, 0L) ?: 0L
    }

    fun setLongPref(key: String, value: Long) {
        val pref: SharedPreferences? = AppsToken.context.getSharedPreferences(AppsToken.PREF_NAME, Context.MODE_PRIVATE)
        pref?.edit()?.putLong(key, value)?.apply()
    }

    fun getStringSetPref(key: String) : MutableSet<String>? {
        val pref: SharedPreferences? = AppsToken.context.getSharedPreferences(AppsToken.PREF_NAME, Context.MODE_PRIVATE)
        return pref?.getStringSet(key, null)
    }

    fun setStringSetPref(key: String, value: MutableSet<String>?) {
        val pref: SharedPreferences? = AppsToken.context.getSharedPreferences(AppsToken.PREF_NAME, Context.MODE_PRIVATE)
        pref?.edit()?.putStringSet(key, value)?.apply()
    }
}