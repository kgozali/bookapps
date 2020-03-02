package com.sampletest.testproject.application

import android.app.Application
import com.facebook.stetho.Stetho
import com.sampletest.testproject.BuildConfig
import com.sampletest.testproject.singleton.AppsToken
import java.util.*


class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        setupDeviceInfo()
        setupStetho()
    }


    private fun setupDeviceInfo() {
        if (AppsToken.deviceId.isBlank()) {
            AppsToken.deviceId = UUID.randomUUID().toString()
        }
    }

    private fun setupStetho() {
        // Setup Stethoq
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    companion object {
        lateinit var instance: SampleApplication

        fun get(): SampleApplication {
            return instance
        }
    }
}