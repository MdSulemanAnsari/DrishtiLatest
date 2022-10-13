package com.application.drishtigems.VendorUser.fragment.Settings

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.fragment.PurchaseHistory.PurchaseHistory
import com.application.drishtigems.databinding.FragmentLogoutDialogBinding
import com.application.drishtigems.ui.base.fragment.LoginScreen

class LogoutDialog : DialogFragment() {
    lateinit var binding:FragmentLogoutDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.dialog_bg)
        dialog!!.setCanceledOnTouchOutside(false)
       binding=DataBindingUtil.inflate(inflater,R.layout.fragment_logout_dialog, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width=(resources.displayMetrics.widthPixels *0.85).toInt()
        val height=(resources.displayMetrics.heightPixels *0.40).toInt()
        dialog!!.window?.setLayout(width,ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
    }

    private fun onClick() {
        binding.buttonLogoutYes.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, LoginScreen())?.commit()
            dialog!!.dismiss()
        }
        binding.crossLogout.setOnClickListener{
            dialog!!.dismiss()
        }
    }
}