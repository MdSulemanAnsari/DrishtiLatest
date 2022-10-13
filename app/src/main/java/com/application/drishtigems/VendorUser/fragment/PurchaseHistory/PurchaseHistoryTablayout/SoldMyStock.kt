package com.application.drishtigems.VendorUser.fragment.PurchaseHistory.PurchaseHistoryTablayout

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.application.drishtigems.R
import com.application.drishtigems.StaffAdapter.model.ModelViewPagerCarat
import com.application.drishtigems.VendorUser.VendorAdapter.PurchaseHistory.PurchaseHistoryAdapter.PagerAdapterSoldMyStock
import com.application.drishtigems.VendorUser.VendorAdapter.PurchaseHistory.PurchaseHistoryAdapter.SoldItemMyStockAdapter
import com.application.drishtigems.VendorUser.VendorModel.PurchaseHistoryModel.OrderBuyingModel
import com.application.drishtigems.databinding.FragmentSoldMyStockBinding

class SoldMyStock : Fragment() {
    lateinit var binding:FragmentSoldMyStockBinding
    var pagerAdapterSoldMyStock: PagerAdapterSoldMyStock?=null
    var listViewPagerItem:ArrayList<ModelViewPagerCarat>?= ArrayList()
    var soldItemMyStockAdapter: SoldItemMyStockAdapter?=null
    var listBuyingRingSold:ArrayList<OrderBuyingModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding=DataBindingUtil.inflate(inflater,R.layout.fragment_sold_my_stock, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        caratList()
        viewPagerCall()
        buyingList()
        recyclerBuying()
        onClick()

    }

    private fun onClick() {
        binding.buttonAddCarat.setOnClickListener {
            SoldDialog().show(requireFragmentManager(), "MyCustomDialogFragment")
        }
        binding.bpSold.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun caratList() {
        listViewPagerItem!!.clear()
        listViewPagerItem!!.add(ModelViewPagerCarat(BitmapFactory.decodeResource(this.resources, R.drawable.view_pager_image)))
        listViewPagerItem!!.add(ModelViewPagerCarat(BitmapFactory.decodeResource(this.resources, R.drawable.view_pager_image)))
        listViewPagerItem!!.add(ModelViewPagerCarat(BitmapFactory.decodeResource(this.resources, R.drawable.view_pager_image)))
        listViewPagerItem!!.add(ModelViewPagerCarat(BitmapFactory.decodeResource(this.resources, R.drawable.view_pager_image)))
    }
    private fun viewPagerCall() {
        pagerAdapterSoldMyStock= PagerAdapterSoldMyStock(requireActivity(),listViewPagerItem?: arrayListOf())
        binding.viewPagerSoldMyStock.adapter=pagerAdapterSoldMyStock
        binding.tabLaySoldMyStock.setupWithViewPager(binding.viewPagerSoldMyStock, true)
    }
    private fun buyingList() {
        listBuyingRingSold.clear()
        listBuyingRingSold.add(OrderBuyingModel(BitmapFactory.decodeResource(this.resources,R.drawable.ring_pic),"REMYG01","$30","9"))
        listBuyingRingSold.add(OrderBuyingModel(BitmapFactory.decodeResource(this.resources,R.drawable.ring_pic),"REMYG01","$30","9"))
        listBuyingRingSold.add(OrderBuyingModel(BitmapFactory.decodeResource(this.resources,R.drawable.ring_pic),"REMYG01","$30","9"))
        listBuyingRingSold.add(OrderBuyingModel(BitmapFactory.decodeResource(this.resources,R.drawable.ring_pic),"REMYG01","$30","9"))
    }
    private fun recyclerBuying() {
        soldItemMyStockAdapter= SoldItemMyStockAdapter(listBuyingRingSold)
        binding.rvBuyingRingSold.adapter=soldItemMyStockAdapter
    }


}