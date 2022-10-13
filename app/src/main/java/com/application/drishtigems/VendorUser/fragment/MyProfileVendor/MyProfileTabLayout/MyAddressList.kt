package com.application.drishtigems.VendorUser.fragment.MyProfileVendor.MyProfileTabLayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorAdapter.MyProfileVendor.MyAdditionalAddressAdapter
import com.application.drishtigems.VendorUser.VendorAdapter.MyProfileVendor.MyAddressListAdapter
import com.application.drishtigems.VendorUser.VendorModel.MyProfileModel.MyAdditionalModel
import com.application.drishtigems.VendorUser.VendorModel.MyProfileModel.MyAddressModel
import com.application.drishtigems.VendorUser.fragment.MyProfileVendor.MyProfileVendor
import com.application.drishtigems.databinding.FragmentMyAddressListBinding

class MyAddressList : Fragment() {
    lateinit var binding:FragmentMyAddressListBinding
    private var myAddressListAdapter:MyAddressListAdapter?=null
    var myAdditionalAddressAdapter:MyAdditionalAddressAdapter?=null
    private var listAddDefault:ArrayList<MyAddressModel> =ArrayList()
    var listAddAdditional:ArrayList<MyAdditionalModel> =ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding=DataBindingUtil.inflate(inflater,R.layout.fragment_my_address_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAddItemDefault()
        recyclerDeafultAddress()
        recyclerAdditionalAddress()
        listAddItemAdditional()
        onClick()
    }

    private fun onClick() {
        binding.buttonAddNewAddress.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, AddNewAddress())?.addToBackStack("") ?.commit()
        }
    }

    private fun listAddItemDefault() {
        listAddDefault.clear()
        listAddDefault.add(MyAddressModel("DEFAULT BILLING ADDRESS","Adam Smith","202\nMohali,Punjab,145036\nIndia","T:9865326548"))
        listAddDefault.add(MyAddressModel("DEFAULT BILLING ADDRESS","Adam Smith","202\nMohali,Punjab,145036\nIndia","T:9865326548"))
        listAddDefault.add(MyAddressModel("DEFAULT BILLING ADDRESS","Adam Smith","202\nMohali,Punjab,145036\nIndia","T:9865326548"))
    }
    private fun recyclerDeafultAddress() {
        myAddressListAdapter= MyAddressListAdapter(listAddDefault)
        binding.rvDefaultAddress.adapter=myAddressListAdapter
    }
    private fun listAddItemAdditional() {
        listAddAdditional.clear()
        listAddAdditional.add(MyAdditionalModel("Adam Smith","202\nMoahli,Punjab,145623\nIndia","T:9865326548"))
        listAddAdditional.add(MyAdditionalModel("Adam Smith","202\nMohali,Punjab,145036\nIndia","T:9865326548"))
        listAddAdditional.add(MyAdditionalModel("Adam Smith","202\nMohali,Punjab,145036\nIndia","T:9865326548"))
    }
    private fun recyclerAdditionalAddress() {
        myAdditionalAddressAdapter= MyAdditionalAddressAdapter(listAddAdditional)
        binding.rvAdditionalAddress.adapter=myAdditionalAddressAdapter
    }

}