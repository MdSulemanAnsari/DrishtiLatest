package com.application.drishtigems.VendorUser.fragment.PurchaseHistory.PurchaseHistoryTablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.application.drishtigems.R
import com.application.drishtigems.databinding.FragmentDeliveryStatusBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialog




class DeliveryStatusBottomSheet : BottomSheetDialogFragment() {
lateinit var binding:FragmentDeliveryStatusBottomSheetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)//set corner of bottom sheet

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding=DataBindingUtil.inflate(inflater,R.layout.fragment_delivery_status_bottom_sheet, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick() {
       binding.crossViewDelivery.setOnClickListener {
         dismiss()
       }
    }
}