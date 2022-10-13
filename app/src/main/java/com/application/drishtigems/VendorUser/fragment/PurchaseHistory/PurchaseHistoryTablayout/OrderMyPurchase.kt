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
import com.application.drishtigems.VendorUser.VendorAdapter.PurchaseHistory.PurchaseHistoryAdapter.OrderBuyingItemAdapter
import com.application.drishtigems.VendorUser.VendorAdapter.PurchaseHistory.PurchaseHistoryAdapter.PagerAdapterMyPurchase
import com.application.drishtigems.VendorUser.VendorModel.PurchaseHistoryModel.OrderBuyingModel
import com.application.drishtigems.databinding.FragmentOrderMyPurchaseBinding

class OrderMyPurchase : Fragment() {
lateinit var binding:FragmentOrderMyPurchaseBinding
    var pagerAdapterMyPurchase: PagerAdapterMyPurchase?=null
    var listMyOrderPurchase:ArrayList<ModelViewPagerCarat>?= ArrayList()
    var orderBuyingItemAdapter: OrderBuyingItemAdapter?=null
    var listBuyingRing:ArrayList<OrderBuyingModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_order_my_purchase, container, false)
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
        binding.tvCheckStatus.setOnClickListener {
            val deliveryStatusBottomSheet=DeliveryStatusBottomSheet()
            deliveryStatusBottomSheet.isCancelable=false
            deliveryStatusBottomSheet.show(requireActivity().supportFragmentManager,"DeliveryBottomSheet")
        }
        binding.backPressedMyPurchase.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun caratList() {
        listMyOrderPurchase!!.clear()
        listMyOrderPurchase!!.add(ModelViewPagerCarat(BitmapFactory.decodeResource(this.resources, R.drawable.view_pager_image)))
        listMyOrderPurchase!!.add(ModelViewPagerCarat(BitmapFactory.decodeResource(this.resources, R.drawable.view_pager_image)))
        listMyOrderPurchase!!.add(ModelViewPagerCarat(BitmapFactory.decodeResource(this.resources, R.drawable.view_pager_image)))
        listMyOrderPurchase!!.add(ModelViewPagerCarat(BitmapFactory.decodeResource(this.resources, R.drawable.view_pager_image)))
    }
    private fun viewPagerCall() {
        pagerAdapterMyPurchase= PagerAdapterMyPurchase(requireActivity(),listMyOrderPurchase?: arrayListOf())
        binding.viewPagerMyPurchase.adapter=pagerAdapterMyPurchase
        binding.tabLayMyPurchase.setupWithViewPager(binding.viewPagerMyPurchase, true)
    }

    private fun buyingList() {
        listBuyingRing.clear()
        listBuyingRing.add(OrderBuyingModel(BitmapFactory.decodeResource(this.resources,R.drawable.ring_pic),"REMYG01","$30","9"))
        listBuyingRing.add(OrderBuyingModel(BitmapFactory.decodeResource(this.resources,R.drawable.ring_pic),"REMYG01","$30","9"))
        listBuyingRing.add(OrderBuyingModel(BitmapFactory.decodeResource(this.resources,R.drawable.ring_pic),"REMYG01","$30","9"))
        listBuyingRing.add(OrderBuyingModel(BitmapFactory.decodeResource(this.resources,R.drawable.ring_pic),"REMYG01","$30","9"))
    }
    private fun recyclerBuying() {
        orderBuyingItemAdapter= OrderBuyingItemAdapter(listBuyingRing)
        binding.rvBuyingRingItem.adapter=orderBuyingItemAdapter
    }

}