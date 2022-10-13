package com.application.drishtigems.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.application.drishtigems.R
import com.application.drishtigems.databinding.FragmentBillGeneratedBinding
import com.application.drishtigems.ui.activity.MainActivity

class BillGenerated : Fragment() {
    lateinit var binding:FragmentBillGeneratedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_bill_generated, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scannerButton.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, QrScanner())?.addToBackStack("") ?.commit()
        }
        binding.billDrawerButton.setOnClickListener {
            (activity as MainActivity).openDrawer()
        }
    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding!!.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }
}
