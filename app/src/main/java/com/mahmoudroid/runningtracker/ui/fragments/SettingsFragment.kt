package com.mahmoudroid.runningtracker.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mahmoudroid.runningtracker.R
import com.mahmoudroid.runningtracker.other.Constants.KEY_NAME
import com.mahmoudroid.runningtracker.other.Constants.KEY_WEIGHT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_setup.etName
import kotlinx.android.synthetic.main.fragment_setup.etWeight
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFieldsFromSharedPref()


        btnApplyChanges.setOnClickListener {
            val success = applyChangesToSharedPref()
            if (success) {
                Snackbar.make(
                    view,
                    "Saved New Data",
                    Snackbar.LENGTH_LONG,
                ).show()
            } else {
                Snackbar.make(
                    view,
                    "Check Null Fields",
                    Snackbar.LENGTH_LONG,
                ).show()
            }
        }
    }

    private fun loadFieldsFromSharedPref() {
        val name = sharedPreferences.getString(KEY_NAME, "")
        val weight = sharedPreferences.getFloat(KEY_WEIGHT, 80f)
        etSettingName.setText(name)
        etSettingWeight.setText(weight.toString())
    }

    private fun applyChangesToSharedPref(): Boolean {

        val nameText = etSettingName.text.toString()
        val weightText = etSettingWeight.text.toString()

        if (nameText.isEmpty() || weightText.isEmpty()) {
            return false
        }
        sharedPreferences.edit()
            .putString(KEY_NAME, nameText)
            .putFloat(KEY_WEIGHT, weightText.toFloat())
            .apply()

        val toolbarText = "Let's Go $nameText"
        requireActivity().tvToolbarTitle.text = toolbarText
        return true

    }


}

