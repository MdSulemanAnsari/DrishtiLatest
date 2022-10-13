package com.application.drishtigems.VendorUser.fragment.ComissionEarned

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorAdapter.CommissionProfileEarnedAdapter
import com.application.drishtigems.VendorUser.VendorModel.CommissionProfileEarnedModel
import com.application.drishtigems.databinding.FragmentCommissionProfileEarnedBinding
import android.content.Intent
import android.net.Uri


class CommissionProfileEarned : Fragment() {
    val str="\"CHRISTAIN\""
    val strSold="\"Christain\""
    lateinit var binding:FragmentCommissionProfileEarnedBinding
    var listProfileCommission:ArrayList<CommissionProfileEarnedModel> = ArrayList()
    var commissionProfileEarnedAdapter:CommissionProfileEarnedAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_commission_profile_earned, container, false)
        binding.textView3.text = "TOTAL SALES MADE BY $str"
        binding.soldProduct.text = "Sold Products By $strSold"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()

        listProfileCommission()
        recyclerProfileCommission()
    }


    private fun listProfileCommission() {
        listProfileCommission.clear()
        listProfileCommission.add(CommissionProfileEarnedModel(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic),"EMERALD PAIR-5.48 CARATS","Loose Gemstone","$100","17/02/2021","18/09/2021","$11.20"))
        listProfileCommission.add(CommissionProfileEarnedModel(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic),"EMERALD PAIR-5.48 CARATS","Loose Gemstone","$100","17/02/2021","18/09/2021","$11.20"))
        listProfileCommission.add(CommissionProfileEarnedModel(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic),"EMERALD PAIR-5.48 CARATS","Loose Gemstone","$100","17/02/2021","18/09/2021","$11.20"))
        listProfileCommission.add(CommissionProfileEarnedModel(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic),"EMERALD PAIR-5.48 CARATS","Loose Gemstone","$100","17/02/2021","18/09/2021","$11.20"))
    }
    private fun recyclerProfileCommission() {
        commissionProfileEarnedAdapter=CommissionProfileEarnedAdapter(listProfileCommission)
        binding.rvCommissionProfile.adapter=commissionProfileEarnedAdapter
    }


    private fun onClick() {
        binding.backPressedCommission.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.callIcon.setOnClickListener {
            val numberOne = "1234567890"
            val numberDial = Uri.parse("tel:$numberOne")
            val dial = Intent(Intent.ACTION_DIAL)
            dial.data = numberDial
            startActivity(dial)
        }
    }
}