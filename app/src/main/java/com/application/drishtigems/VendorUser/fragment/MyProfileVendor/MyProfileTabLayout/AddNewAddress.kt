package com.application.drishtigems.VendorUser.fragment.MyProfileVendor.MyProfileTabLayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.fragment.MyProfileVendor.MyProfileVendor
import com.application.drishtigems.VendorUser.fragment.Settings.ContactUs
import com.application.drishtigems.databinding.FragmentAddNewAddressBinding
import kotlinx.android.synthetic.main.fragment_add_new_address.*
import kotlinx.android.synthetic.main.fragment_filter_stock.*
import kotlinx.android.synthetic.main.fragment_filter_stock.spinnerSortFilter

class AddNewAddress : Fragment() {
lateinit var binding:FragmentAddNewAddressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=DataBindingUtil.inflate(inflater,R.layout.fragment_add_new_address, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
        stateAddNewSpinner()
        countryAddNewSpinner()
    }

    private fun onClick() {
        binding.backPressedAddNewAddress.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.buttonSubmitAddNew.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, MyProfileVendor())?.addToBackStack("") ?.commit()
        }
    }
    private fun stateAddNewSpinner() {
        val list = arrayOf("Jharkhand", "Punjab", "Delhi")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerStateAddNew?.adapter = adapter
    }
    private fun countryAddNewSpinner() {
        val list = arrayOf("India", "India", "India")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        SpinnerCountryAddNew?.adapter = adapter
    }
}