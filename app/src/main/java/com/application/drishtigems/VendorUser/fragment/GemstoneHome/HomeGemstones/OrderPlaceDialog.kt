package com.application.drishtigems.VendorUser.fragment.GemstoneHome.HomeGemstones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.fragment.PurchaseHistory.PurchaseHistory
import com.application.drishtigems.databinding.FragmentOrderPlaceDialogBinding

class OrderPlaceDialog : DialogFragment() {
    lateinit var binding:FragmentOrderPlaceDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.dialog_bg)
        dialog!!.setCanceledOnTouchOutside(false)
      binding=DataBindingUtil.inflate(inflater,R.layout.fragment_order_place_dialog, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
    }
    override fun onStart() {
        super.onStart()
        val width=(resources.displayMetrics.widthPixels *0.85).toInt()
        val height=(resources.displayMetrics.heightPixels *0.40).toInt()
        dialog!!.window?.setLayout(width,ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun onClick() {
        binding.buttonGoToPurchaseHistory.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, PurchaseHistory())?.commit()
            dialog!!.dismiss()
        }
   /*     binding.crossDialogOrderPlace.setOnClickListener {
            dialog!!.dismiss()
        }*/
    }


}