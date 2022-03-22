package com.android.example.cameraxbasic

import android.app.Application
import android.util.Log
import androidx.camera.camera2.Camera2Config
import androidx.camera.core.CameraXConfig
import com.android.example.cameraxbasic.utils.CameraPreferenceManager

/**
 * Set CameraX logging level to Log.ERROR to avoid excessive logcat messages.
 * Refer to https://developer.android.com/reference/androidx/camera/core/CameraXConfig.Builder#setMinimumLoggingLevel(int)
 * for details.
 */
class MainApplication : Application(), CameraXConfig.Provider {
    private lateinit var dataStore : CameraPreferenceManager

    companion object {
        private lateinit var mainApplication: MainApplication
        fun getInstance() : MainApplication = mainApplication
    }

    override fun getCameraXConfig(): CameraXConfig {
        return CameraXConfig.Builder.fromConfig(Camera2Config.defaultConfig())
            .setMinimumLoggingLevel(Log.ERROR).build()
    }

    override fun onCreate() {
        super.onCreate()
        mainApplication = this
        dataStore = CameraPreferenceManager(this)
    }

    fun getDataStore() : CameraPreferenceManager = dataStore

}