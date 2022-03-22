package com.android.example.cameraxbasic.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settingPrefs")

class CameraPreferenceManager(context: Context) {

    private var appContext = context.applicationContext

    companion object {
        val CAMERA_ZOOM_KEY = floatPreferencesKey("CAMERA_ZOOM")
        val CAMERA_WIDTH_KEY = intPreferencesKey("CAMERA_WIDTH")
        val CAMERA_HEIGHT_KEY = intPreferencesKey("CAMERA_HEIGHT")
        val CAMERA_EXPOSURE_KEY = intPreferencesKey("CAMERA_EXPOSURE")
        val CAMERA_RESOLUTION_KEY = intPreferencesKey("CAMERA_RESOLUTION")
        val CAMERA_CHANGE_KEY = stringPreferencesKey("CAMERA_CHANGE")
    }

    suspend fun setZoom(zoom: Float){
        appContext.dataStore.edit{
            it[CAMERA_ZOOM_KEY] = zoom
            it[CAMERA_CHANGE_KEY] = "zoom"
        }
    }

    suspend fun setResolution(resolution: Int){

        when(resolution){
            1080 -> {
                appContext.dataStore.edit{
                    it[CAMERA_WIDTH_KEY] = 1080
                    it[CAMERA_HEIGHT_KEY] = 1920
                    it[CAMERA_CHANGE_KEY] = "resolution"
                }
            }
            720 -> {
                appContext.dataStore.edit{
                    it[CAMERA_WIDTH_KEY] = 720
                    it[CAMERA_HEIGHT_KEY] = 1280
                    it[CAMERA_CHANGE_KEY] = "resolution"
                }
            }
            480 -> {
                appContext.dataStore.edit{
                    it[CAMERA_WIDTH_KEY] = 480
                    it[CAMERA_HEIGHT_KEY] = 640
                    it[CAMERA_CHANGE_KEY] = "resolution"
                }
            }
        }
        appContext.dataStore.edit{
            it[CAMERA_RESOLUTION_KEY] = resolution
        }
    }

    suspend fun setExposure(exposure: Int){
        appContext.dataStore.edit{
            it[CAMERA_EXPOSURE_KEY] = exposure
            it[CAMERA_CHANGE_KEY] = "exposure"
        }
    }

    val cameraZoomFlow: Flow<Float> = appContext.dataStore.data.map {
        it[CAMERA_ZOOM_KEY] ?: 1F
    }

    val cameraResolutionFlow: Flow<Int> = appContext.dataStore.data.map {
        it[CAMERA_RESOLUTION_KEY] ?: 1080
    }

    val cameraWidthFlow: Flow<Int> = appContext.dataStore.data.map {
        it[CAMERA_WIDTH_KEY] ?: 720
    }

    val cameraHeightFlow: Flow<Int> = appContext.dataStore.data.map {
        it[CAMERA_HEIGHT_KEY] ?: 1080
    }

    val cameraExposureFlow: Flow<Int> = appContext.dataStore.data.map {
        it[CAMERA_EXPOSURE_KEY] ?: 0
    }

    val cameraSettingFlow: Flow<String> = appContext.dataStore.data.map {
        it[CAMERA_CHANGE_KEY] ?: "nothing"
    }
}