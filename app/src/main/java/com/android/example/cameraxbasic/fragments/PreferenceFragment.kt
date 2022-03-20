package com.android.example.cameraxbasic.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.android.example.cameraxbasic.R
import com.android.example.cameraxbasic.databinding.FragmentGalleryBinding
import com.android.example.cameraxbasic.databinding.FragmentPreferenceBinding
import com.android.example.cameraxbasic.utils.CameraPreference
import com.android.example.cameraxbasic.utils.SharedManager

class PreferenceFragment: DialogFragment() {

    private var _fragmentPreferenceBinding: FragmentPreferenceBinding? = null
    private val fragmentPreferenceBinding get() = _fragmentPreferenceBinding!!

    private val sharedManager: SharedManager by lazy {SharedManager(requireContext())}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentPreferenceBinding = FragmentPreferenceBinding.inflate(inflater,container,false)
        val currentCameraPreference = sharedManager.getCurrentCameraPreference()
        var changedCameraPreference = CameraPreference()

        fragmentPreferenceBinding.radioGroupBright.apply{

            when(currentCameraPreference.exposure){
                -2 -> { this.check(R.id.radioBtnBright1) }
                -1 -> { this.check(R.id.radioBtnBright2) }
                0 -> { this.check(R.id.radioBtnBright3) }
                1 -> { this.check(R.id.radioBtnBright4) }
                2 -> { this.check(R.id.radioBtnBright5) }
            }
        }

        fragmentPreferenceBinding.radioGroupResolution.apply{
            when(currentCameraPreference.resolution){
                1080 -> { this.check(R.id.radioBtnResolution1)}
                720 -> { this.check(R.id.radioBtnResolution2)}
                480 -> { this.check(R.id.radioBtnResolution3)}
            }
        }

        fragmentPreferenceBinding.radioGroupZoom.apply{
            when(currentCameraPreference.zoom){
                1F -> { this.check(R.id.radioBtnZoom1)}
                2F -> { this.check(R.id.radioBtnZoom2)}
                5F -> { this.check(R.id.radioBtnZoom3)}
            }
        }

        fragmentPreferenceBinding.radioGroupBright.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.radioBtnBright1 -> { changedCameraPreference.apply{ exposure = -2 } }
                R.id.radioBtnBright2 -> { changedCameraPreference.apply{ exposure = -1 } }
                R.id.radioBtnBright3 -> { changedCameraPreference.apply{ exposure = 0 } }
                R.id.radioBtnBright4 -> { changedCameraPreference.apply{ exposure = 1 } }
                R.id.radioBtnBright5 -> { changedCameraPreference.apply{ exposure = 2 } }
            }
        }

        fragmentPreferenceBinding.radioGroupResolution.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.radioBtnResolution1 -> { changedCameraPreference.apply{ resolution = 1080 } }
                R.id.radioBtnResolution2 -> { changedCameraPreference.apply{ resolution = 720 } }
                R.id.radioBtnResolution3 -> { changedCameraPreference.apply{ resolution = 480 } }
            }
        }

        fragmentPreferenceBinding.radioGroupZoom.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.radioBtnZoom1 -> { changedCameraPreference.apply{ zoom = 1F } }
                R.id.radioBtnZoom1 -> { changedCameraPreference.apply{ zoom = 2F } }
                R.id.radioBtnZoom1 -> { changedCameraPreference.apply{ zoom = 5F } }
            }
        }

        fragmentPreferenceBinding.doneButton.setOnClickListener {
            sharedManager.saveCurrentCameraPreference(changedCameraPreference)
            Log.d("Preference","Save camera Preference")
            dismiss()
        }

        fragmentPreferenceBinding.cancelButton.setOnClickListener {
            Log.d("Preference","cancel camera preference")
            dismiss()
        }

        return fragmentPreferenceBinding.root
    }



    override fun onDestroyView() {
        _fragmentPreferenceBinding = null
        super.onDestroyView()
        Log.d("Preference","DestroyView")
    }

}