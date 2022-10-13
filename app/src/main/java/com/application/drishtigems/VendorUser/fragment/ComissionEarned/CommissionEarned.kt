package com.application.drishtigems.VendorUser.fragment.ComissionEarned

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorAdapter.CommissionAdapter
import com.application.drishtigems.VendorUser.VendorModel.CommissionModel
import com.application.drishtigems.VendorUser.fragment.ShoppingCartVendor
import com.application.drishtigems.databinding.FragmentCommissionEarnedBinding
import com.application.drishtigems.ui.activity.MainActivity

class CommissionEarned : Fragment() {
    var commissionAdapter:CommissionAdapter?=null
    var listCommissionItem:ArrayList<CommissionModel> = ArrayList()
    lateinit var binding:FragmentCommissionEarnedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=DataBindingUtil.inflate(inflater,R.layout.fragment_commission_earned, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
        listItemCommission()
        recyclerCommission()
    }

    private fun onClick() {
        binding.buttonImageCommission.setOnClickListener {
            (activity as MainActivity).openDrawer()
        }
        binding.picScCe.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, ShoppingCartVendor())?.addToBackStack("") ?.commit()
        }
    }

    private fun listItemCommission() {
        listCommissionItem.clear()
        listCommissionItem.add(CommissionModel(BitmapFactory.decodeResource(this.resources,R.drawable.person_name),"Christain Jewellers","Main Town Mohali,Dolphin Chowk \nPunjab,102102","$233"))
        listCommissionItem.add(CommissionModel(BitmapFactory.decodeResource(this.resources,R.drawable.person_name),"Christain Jewellers","Main Town Mohali,Dolphin Chowk \nPunjab,102102","$233"))
        listCommissionItem.add(CommissionModel(BitmapFactory.decodeResource(this.resources,R.drawable.person_name),"Christain Jewellers","Main Town Mohali,Dolphin Chowk \nPunjab,102102","$233"))
        listCommissionItem.add(CommissionModel(BitmapFactory.decodeResource(this.resources,R.drawable.person_name),"Christain Jewellers","Main Town Mohali,Dolphin Chowk \nPunjab,102102","$233"))
        listCommissionItem.add(CommissionModel(BitmapFactory.decodeResource(this.resources,R.drawable.person_name),"Christain Jewellers","Main Town Mohali,Dolphin Chowk \nPunjab,102102","$233"))
        listCommissionItem.add(CommissionModel(BitmapFactory.decodeResource(this.resources,R.drawable.person_name),"Christain Jewellers","Main Town Mohali,Dolphin Chowk \nPunjab,102102","$233"))
    }
    private fun recyclerCommission() {
       commissionAdapter= CommissionAdapter(requireContext(),listCommissionItem)
        binding.rvCommission.adapter=commissionAdapter
    }
}