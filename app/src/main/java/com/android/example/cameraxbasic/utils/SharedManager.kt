package com.android.example.cameraxbasic.utils

import android.content.Context
import android.content.SharedPreferences
import com.android.example.cameraxbasic.utils.PreferenceHelper.get
import com.android.example.cameraxbasic.utils.PreferenceHelper.set

class SharedManager(context: Context) {
    private val prefs: SharedPreferences = PreferenceHelper.defaultPrefs(context)

    fun saveCurrentCameraPreference(camerapreference: CameraPreference) {
        prefs["zoom"] = camerapreference.zoom
        prefs["resolution"] = camerapreference.resolution
        prefs["exposure"] = camerapreference.exposure
    }

    fun getCurrentCameraPreference(): CameraPreference {
        return CameraPreference().apply {
            zoom = prefs["zoom",1F]
            resolution = prefs["resolution", 1080]
            exposure = prefs["exposure", 0]
        }
    }


}