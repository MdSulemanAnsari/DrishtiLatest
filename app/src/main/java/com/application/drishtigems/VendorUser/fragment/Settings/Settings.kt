package com.application.drishtigems.VendorUser.fragment.Settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.application.drishtigems.R
import com.application.drishtigems.databinding.FragmentSettingsBinding
import com.application.drishtigems.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_settings.*

class Settings : Fragment() {
    lateinit var binding:FragmentSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=DataBindingUtil.inflate(inflater,R.layout.fragment_settings, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick() {
        binding.constraintChangePassword.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, ChangePassword())?.addToBackStack("") ?.commit()
        }
        binding.constraintContactUs.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, ContactUs())?.addToBackStack("") ?.commit()
        }
        binding.buttonImageCommission.setOnClickListener {
            (activity as MainActivity).openDrawer()
        }
        binding.constraintLogout.setOnClickListener {
           LogoutDialog().show(requireFragmentManager(),"MyLogoutDialog")
        }
    }
}