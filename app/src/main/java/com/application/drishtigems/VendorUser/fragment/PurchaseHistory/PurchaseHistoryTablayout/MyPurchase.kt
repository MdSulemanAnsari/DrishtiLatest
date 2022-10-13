package com.application.drishtigems.VendorUser.fragment.PurchaseHistory.PurchaseHistoryTablayout

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorAdapter.PurchaseHistory.MyPurchaseAdapter
import com.application.drishtigems.VendorUser.VendorModel.PurchaseHistoryModel.MyPurchaseModel
import com.application.drishtigems.databinding.FragmentMyPurchaseBinding
import kotlinx.android.synthetic.main.fragment_my_purchase.*

class MyPurchase : Fragment() {
lateinit var binding:FragmentMyPurchaseBinding
var itemListPurchase:ArrayList<MyPurchaseModel> = ArrayList()
    var myPurchaseAdapter: MyPurchaseAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_my_purchase, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        purchaseItemList()
        recyclerPurchaseItem()
        myPurchaseSpinner()
    }

    private fun purchaseItemList() {
        itemListPurchase.clear()
        itemListPurchase.add(MyPurchaseModel(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic,),"EMERALD-8.68 CARATS","Ring(22k Yellow Gold","$130","17/09/2021"))
        itemListPurchase.add(MyPurchaseModel(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic,),"EMERALD-8.68 CARATS","Ring(22k Yellow Gold","$130","17/09/2021"))
        itemListPurchase.add(MyPurchaseModel(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic,),"EMERALD-8.68 CARATS","Ring(22k Yellow Gold","$130","17/09/2021"))
        itemListPurchase.add(MyPurchaseModel(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic,),"EMERALD-8.68 CARATS","Ring(22k Yellow Gold","$130","17/09/2021"))
        itemListPurchase.add(MyPurchaseModel(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic,),"EMERALD-8.68 CARATS","Ring(22k Yellow Gold","$130","17/09/2021"))
    }

    private fun recyclerPurchaseItem() {
        myPurchaseAdapter= MyPurchaseAdapter(requireActivity(),itemListPurchase)
        binding.rvMyPurchase.adapter=myPurchaseAdapter
    }
    private fun myPurchaseSpinner() {
        val list=arrayOf("All","Sold","Unsold")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSoldPurchase?.adapter = adapter
    }

}