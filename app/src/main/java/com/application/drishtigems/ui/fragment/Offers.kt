package com.application.drishtigems.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.application.drishtigems.R
import com.application.drishtigems.StaffAdapter.adapter.AdapterOffers
import com.application.drishtigems.databinding.FragmentOffersBinding
import com.application.drishtigems.StaffAdapter.model.ModelOffers
import com.application.drishtigems.ui.activity.MainActivity

class Offers : Fragment() {
    lateinit var binding:FragmentOffersBinding
 var offerList:ArrayList<ModelOffers>?= ArrayList()
    var adapterOffers:AdapterOffers?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_offers, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonOffers.setOnClickListener {
            (activity as MainActivity).openDrawer()
        }

        recyclerOffler()
        offerListItem()
    }

    private fun offerListItem() {
        offerList!!.add(ModelOffers("Up to $1,000","10%"))
        offerList!!.add(ModelOffers("$1,000 to $2,000","15%"))
        offerList!!.add(ModelOffers("$2,000 to $3,000","20%"))
        offerList!!.add(ModelOffers("$3,000 to $5,000","25%"))
        offerList!!.add(ModelOffers("$5,000+","15%"))
    }

    private fun recyclerOffler() {
        adapterOffers= AdapterOffers(offerList)
        binding.rvOffers.adapter=adapterOffers
    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

}