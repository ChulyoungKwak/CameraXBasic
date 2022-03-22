package com.android.example.cameraxbasic.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.asLiveData
import com.android.example.cameraxbasic.MainApplication
import com.android.example.cameraxbasic.R
import com.android.example.cameraxbasic.databinding.FragmentPreferenceBinding
import com.android.example.cameraxbasic.data.CameraPreference
import com.android.example.cameraxbasic.utils.CameraPreferenceManager
import com.android.example.cameraxbasic.utils.SharedManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PreferenceFragment: DialogFragment() {

    private var _fragmentPreferenceBinding: FragmentPreferenceBinding? = null
    private val fragmentPreferenceBinding get() = _fragmentPreferenceBinding!!

//    lateinit var cameraPreferenceManager: CameraPreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentPreferenceBinding = FragmentPreferenceBinding.inflate(inflater,container,false)

        fragmentPreferenceBinding.radioGroupBright.apply{
            CoroutineScope(Dispatchers.Default).launch{
                when(MainApplication.getInstance().getDataStore().cameraExposureFlow.first()){
                    -2 -> { fragmentPreferenceBinding.radioGroupBright.check(R.id.radioBtnBright1) }
                    -1 -> { fragmentPreferenceBinding.radioGroupBright.check(R.id.radioBtnBright2) }
                    0 -> { fragmentPreferenceBinding.radioGroupBright.check(R.id.radioBtnBright3) }
                    1 -> { fragmentPreferenceBinding.radioGroupBright.check(R.id.radioBtnBright4) }
                    2 -> { fragmentPreferenceBinding.radioGroupBright.check(R.id.radioBtnBright5) }
                }
            }
        }

        fragmentPreferenceBinding.radioGroupResolution.apply{
            CoroutineScope(Dispatchers.Default).launch{
                when(MainApplication.getInstance().getDataStore().cameraResolutionFlow.first()){
                    1080 -> { fragmentPreferenceBinding.radioGroupResolution.check(R.id.radioBtnResolution1) }
                    720 -> { fragmentPreferenceBinding.radioGroupResolution.check(R.id.radioBtnResolution2) }
                    480 -> { fragmentPreferenceBinding.radioGroupResolution.check(R.id.radioBtnResolution3) }
                }
            }
        }

        fragmentPreferenceBinding.radioGroupZoom.apply{
            CoroutineScope(Dispatchers.Default).launch{
                when(MainApplication.getInstance().getDataStore().cameraZoomFlow.first()){
                    1F -> { fragmentPreferenceBinding.radioGroupZoom.check(R.id.radioBtnZoom1) }
                    2F -> { fragmentPreferenceBinding.radioGroupZoom.check(R.id.radioBtnZoom2) }
                    3F -> { fragmentPreferenceBinding.radioGroupZoom.check(R.id.radioBtnZoom3) }
                }
            }
        }

        fragmentPreferenceBinding.radioGroupBright.setOnCheckedChangeListener { _, checkedId ->
            CoroutineScope(Dispatchers.Default).launch {
                when (checkedId) {
                    R.id.radioBtnBright1 -> {
                        MainApplication.getInstance().getDataStore().setExposure(-2)
                    }
                    R.id.radioBtnBright2 -> {
                        MainApplication.getInstance().getDataStore().setExposure(-1)
                    }
                    R.id.radioBtnBright3 -> {
                        MainApplication.getInstance().getDataStore().setExposure(0)
                    }
                    R.id.radioBtnBright4 -> {
                        MainApplication.getInstance().getDataStore().setExposure(1)
                    }
                    R.id.radioBtnBright5 -> {
                        MainApplication.getInstance().getDataStore().setExposure(2)
                    }
                }
            }
        }

        fragmentPreferenceBinding.radioGroupResolution.setOnCheckedChangeListener { _, checkedId ->
            CoroutineScope(Dispatchers.Default).launch {
                when (checkedId) {
                    R.id.radioBtnResolution1 -> {
                        MainApplication.getInstance().getDataStore().setResolution(1080)
                    }
                    R.id.radioBtnResolution2 -> {
                        MainApplication.getInstance().getDataStore().setResolution(720)
                    }
                    R.id.radioBtnResolution3 -> {
                        MainApplication.getInstance().getDataStore().setResolution(480)
                    }
                }
            }
        }

        fragmentPreferenceBinding.radioGroupZoom.setOnCheckedChangeListener { _, checkedId ->
            CoroutineScope(Dispatchers.Default).launch {
                when (checkedId) {
                    R.id.radioBtnZoom1 -> {
                        MainApplication.getInstance().getDataStore().setZoom(1F)
                    }
                    R.id.radioBtnZoom2 -> {
                        MainApplication.getInstance().getDataStore().setZoom(2F)
                    }
                    R.id.radioBtnZoom3 -> {
                        MainApplication.getInstance().getDataStore().setZoom(5F)
                    }
                }
            }
        }

        fragmentPreferenceBinding.doneButton.setOnClickListener {
//            sharedManager.saveCurrentCameraPreference(changedCameraPreference)
            dismiss()
        }

        fragmentPreferenceBinding.cancelButton.setOnClickListener {
            dismiss()
        }

//        val currentCameraPreference = sharedManager.getCurrentCameraPreference()
//        var changedCameraPreference = CameraPreference()
//
//        fragmentPreferenceBinding.radioGroupBright.apply{
//            when(currentCameraPreference.exposure){
//                -2 -> { this.check(R.id.radioBtnBright1) }
//                -1 -> { this.check(R.id.radioBtnBright2) }
//                0 -> { this.check(R.id.radioBtnBright3) }
//                1 -> { this.check(R.id.radioBtnBright4) }
//                2 -> { this.check(R.id.radioBtnBright5) }
//            }
//        }
//
//        fragmentPreferenceBinding.radioGroupResolution.apply{
//            when(currentCameraPreference.resolution){
//                1080 -> { this.check(R.id.radioBtnResolution1)}
//                720 -> { this.check(R.id.radioBtnResolution2)}
//                480 -> { this.check(R.id.radioBtnResolution3)}
//            }
//        }
//
//        fragmentPreferenceBinding.radioGroupZoom.apply{
//            when(currentCameraPreference.zoom){
//                1F -> { this.check(R.id.radioBtnZoom1)}
//                2F -> { this.check(R.id.radioBtnZoom2)}
//                5F -> { this.check(R.id.radioBtnZoom3)}
//            }
//        }
//
//        fragmentPreferenceBinding.radioGroupBright.setOnCheckedChangeListener { _, checkedId ->
//            when(checkedId) {
//                R.id.radioBtnBright1 -> { changedCameraPreference.apply{ exposure = -2 } }
//                R.id.radioBtnBright2 -> { changedCameraPreference.apply{ exposure = -1 } }
//                R.id.radioBtnBright3 -> { changedCameraPreference.apply{ exposure = 0 } }
//                R.id.radioBtnBright4 -> { changedCameraPreference.apply{ exposure = 1 } }
//                R.id.radioBtnBright5 -> { changedCameraPreference.apply{ exposure = 2 } }
//            }
//            changedCameraPreference.apply{ isExposureChanged = true}
//        }
//
//        fragmentPreferenceBinding.radioGroupResolution.setOnCheckedChangeListener { _, checkedId ->
//            when(checkedId) {
//                R.id.radioBtnResolution1 -> {
//                    changedCameraPreference.apply{
//                        resolution = 1080
//                        cameraSizeWidth= 1080
//                        cameraSizeHeight = 1920
//                    } }
//                R.id.radioBtnResolution2 -> { changedCameraPreference.apply{
//                    resolution = 720
//                    cameraSizeWidth= 720
//                    cameraSizeHeight = 1280
//                } }
//                R.id.radioBtnResolution3 -> { changedCameraPreference.apply{
//                    resolution = 480
//                    cameraSizeWidth= 480
//                    cameraSizeHeight = 640
//                } }
//            }
//            changedCameraPreference.apply{ isResolutionChanged = true}
//        }
//
//        fragmentPreferenceBinding.radioGroupZoom.setOnCheckedChangeListener { _, checkedId ->
//            when(checkedId) {
//                R.id.radioBtnZoom1 -> { changedCameraPreference.apply{ zoom = 1F } }
//                R.id.radioBtnZoom1 -> { changedCameraPreference.apply{ zoom = 2F } }
//                R.id.radioBtnZoom1 -> { changedCameraPreference.apply{ zoom = 5F } }
//            }
//            changedCameraPreference.apply{ isZoomChanged = true}
//        }
//
//        fragmentPreferenceBinding.doneButton.setOnClickListener {
//            sharedManager.saveCurrentCameraPreference(changedCameraPreference)
//            Log.d("Preference","Save camera Preference")
//            dismiss()
//        }
//
//        fragmentPreferenceBinding.cancelButton.setOnClickListener {
//            Log.d("Preference","cancel camera preference")
//            dismiss()
//        }

        return fragmentPreferenceBinding.root
    }



    override fun onDestroyView() {
        _fragmentPreferenceBinding = null
        super.onDestroyView()
        Log.d("Preference","DestroyView")
    }

}