package com.android.example.cameraxbasic.utils

import android.content.Context
import android.content.SharedPreferences
import com.android.example.cameraxbasic.data.CameraPreference
import com.android.example.cameraxbasic.utils.PreferenceHelper.get
import com.android.example.cameraxbasic.utils.PreferenceHelper.set

class SharedManager(context: Context) {
    private val prefs: SharedPreferences = PreferenceHelper.defaultPrefs(context)

    fun saveCurrentCameraPreference(camerapreference: CameraPreference) {
        prefs["zoom"] = camerapreference.zoom
        prefs["cameraSizeWidth"] = camerapreference.cameraSizeWidth
        prefs["cameraSizeHeight"] = camerapreference.cameraSizeHeight
        prefs["exposure"] = camerapreference.exposure
        prefs["resolution"] = camerapreference.resolution
        prefs["isExposureChanged"] = camerapreference.isExposureChanged
        prefs["isZoomChanged"] = camerapreference.isZoomChanged
        prefs["isResolutionChanged"] = camerapreference.isResolutionChanged
    }

    fun getCurrentCameraPreference(): CameraPreference {
        return CameraPreference().apply {
            zoom = prefs["zoom",1F]
            cameraSizeWidth = prefs["cameraSizeWidth", 1280]
            cameraSizeHeight = prefs["cameraSizeHeight", 720]
            exposure = prefs["exposure", 0]
            resolution = prefs["resolution", 1080]
            isExposureChanged = prefs["isExposureChanged", false]
            isZoomChanged = prefs["isZoomChanged", false]
            isResolutionChanged = prefs["isResolutionChanged", false]
        }
    }


}