package com.application.drishtigems.VendorUser.fragment.GemstoneHome.HomeGemstones

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.application.drishtigems.R
import com.application.drishtigems.databinding.FragmentSecurityCheckOutBinding
import kotlinx.android.synthetic.main.fragment_security_check_out.*

class SecurityCheckOut : Fragment() {
lateinit var binding:FragmentSecurityCheckOutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
  binding=DataBindingUtil.inflate(inflater,R.layout.fragment_security_check_out, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
        stateAddNewSpinner()
        countryAddNewSpinner()
    }

    private fun onClick() {
        binding.backPressedSc.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.buttonPlaceHolder.setOnClickListener {
            ChoosePayment().show(requireFragmentManager(),"ChoosePaymentDialog")
        }
    }

    private fun stateAddNewSpinner() {
        val list = arrayOf("Jharkhand", "Punjab", "Delhi")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerStateAddNewSc?.adapter = adapter
    }
    private fun countryAddNewSpinner() {
        val list = arrayOf("India", "India", "India")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        SpinnerCountryAddNewSc?.adapter = adapter
    }
}