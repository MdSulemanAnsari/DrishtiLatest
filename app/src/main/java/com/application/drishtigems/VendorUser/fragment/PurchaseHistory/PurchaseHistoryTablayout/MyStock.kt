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
import com.application.drishtigems.VendorUser.VendorAdapter.PurchaseHistory.MyStockAdapter
import com.application.drishtigems.VendorUser.VendorModel.PurchaseHistoryModel.MyStockModel
import com.application.drishtigems.databinding.FragmentMyStockBinding
import kotlinx.android.synthetic.main.fragment_my_stock.*

class MyStock : Fragment() {
    lateinit var binding:FragmentMyStockBinding
    var listMyStock:ArrayList<MyStockModel> = ArrayList()
    var myStockAdapter: MyStockAdapter?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_my_stock, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myStockSpinner()
        myStockListItem()
        recyclerMyStock()
    }
    private fun myStockSpinner() {
        val list=arrayOf("All","Sold","Unsold")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMyStock?.adapter = adapter
    }
    private fun myStockListItem() {
        listMyStock.clear()
        listMyStock.add(MyStockModel(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic,),"EMERALD-8.68 CARATS","Ring(22k Yellow Gold","$130","17/09/2021"))
        listMyStock.add(MyStockModel(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic,),"EMERALD-8.68 CARATS","Ring(22k Yellow Gold","$130","17/09/2021"))
        listMyStock.add(MyStockModel(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic,),"EMERALD-8.68 CARATS","Ring(22k Yellow Gold","$130","17/09/2021"))
        listMyStock.add(MyStockModel(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic,),"EMERALD-8.68 CARATS","Ring(22k Yellow Gold","$130","17/09/2021"))
        listMyStock.add(MyStockModel(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic,),"EMERALD-8.68 CARATS","Ring(22k Yellow Gold","$130","17/09/2021"))
    }

    private fun recyclerMyStock() {
        myStockAdapter= MyStockAdapter(requireContext(),listMyStock)
        binding.rvMyStock.adapter=myStockAdapter
    }




}