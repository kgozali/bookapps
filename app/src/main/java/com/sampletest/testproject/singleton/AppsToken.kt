package com.sampletest.testproject.singleton

import com.sampletest.testproject.BuildConfig
import com.sampletest.testproject.application.SampleApplication
import com.sampletest.testproject.base.SharedPreferences

object AppsToken {
    private val sharedPreferences: SharedPreferences by lazy { SharedPreferences() }

    val context = SampleApplication.get()
    const val PREF_NAME = BuildConfig.APPLICATION_ID

    var deviceId: String
        get() = sharedPreferences.getStringPref(DEVICE_ID)
        set(value) {
            sharedPreferences.setStringPref(DEVICE_ID, value)
        }

    var userToken: String
        get() = sharedPreferences.getStringPref(USER_TOKEN)
        set(value) {
            sharedPreferences.setStringPref(USER_TOKEN, value)
        }

    var userId: Long
        get() = sharedPreferences.getLongPref(USER_ID)
        set(value) {
            sharedPreferences.setLongPref(USER_ID, value)
        }

    private const val DEVICE_ID = "device_id"
    private const val USER_TOKEN = "user_token"
    private const val USER_ID = "user_id"

}