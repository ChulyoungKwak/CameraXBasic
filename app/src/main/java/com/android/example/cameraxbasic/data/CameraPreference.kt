package com.android.example.cameraxbasic.data

data class CameraPreference (
    var exposure: Int? = 0,
    var zoom: Float? = 1F,
    var cameraSizeWidth: Int? = 1280,
    var cameraSizeHeight: Int? = 720,
    var resolution:Int? = 720,
    var isExposureChanged: Boolean? = false,
    var isZoomChanged: Boolean? = false,
    var isResolutionChanged: Boolean? = false,
)

data class UserCameraPreference (
    val exposure: Int?,
    val zoom: Float?,
    var cameraSizeWidth: Int?,
    var cameraSizeHeight: Int?
    )