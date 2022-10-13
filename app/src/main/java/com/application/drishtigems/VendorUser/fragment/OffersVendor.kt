package com.application.drishtigems.VendorUser.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorAdapter.OffersVendorAdapter
import com.application.drishtigems.VendorUser.VendorModel.OfferModelVendor
import com.application.drishtigems.databinding.FragmentOffersVendorBinding
import com.application.drishtigems.ui.activity.MainActivity

class OffersVendor : Fragment() {
    lateinit var binding:FragmentOffersVendorBinding
    var offersVendorAdapter: OffersVendorAdapter?=null
    var addListOffer:ArrayList<OfferModelVendor> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_offers_vendor, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
        offerAddList()
        recyclerOffer()
    }

    private fun onClick() {
        binding.buttonOfferVendor.setOnClickListener {
            (activity as MainActivity).openDrawer()
        }
        binding.picScOffer.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, ShoppingCartVendor())?.addToBackStack("") ?.commit()
        }
    }

    private fun offerAddList() {
        addListOffer.clear()
        addListOffer.addAll(listOf(OfferModelVendor(BitmapFactory.decodeResource(this.resources,R.drawable.offer_pic_vendor),"SIGN IN TODAY TO GET 10% \n DISCOUNT ON ALL PRODUCTS.")))
        addListOffer.addAll(listOf(OfferModelVendor(BitmapFactory.decodeResource(this.resources,R.drawable.offer_pic_vendor),"SIGN IN TODAY TO GET 10% \n DISCOUNT ON ALL PRODUCTS.")))
        addListOffer.addAll(listOf(OfferModelVendor(BitmapFactory.decodeResource(this.resources,R.drawable.offer_pic_vendor),"SIGN IN TODAY TO GET 10% \n DISCOUNT ON ALL PRODUCTS.")))
        addListOffer.addAll(listOf(OfferModelVendor(BitmapFactory.decodeResource(this.resources,R.drawable.offer_pic_vendor),"SIGN IN TODAY TO GET 10% \n DISCOUNT ON ALL PRODUCTS.")))
        addListOffer.addAll(listOf(OfferModelVendor(BitmapFactory.decodeResource(this.resources,R.drawable.offer_pic_vendor),"SIGN IN TODAY TO GET 10% \n DISCOUNT ON ALL PRODUCTS.")))
    }
    private fun recyclerOffer() {
        offersVendorAdapter= OffersVendorAdapter(addListOffer)
        binding.rvOfferVendor.adapter=offersVendorAdapter
    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }
}