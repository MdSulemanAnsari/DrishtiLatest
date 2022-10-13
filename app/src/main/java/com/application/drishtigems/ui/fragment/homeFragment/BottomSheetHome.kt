package com.application.drishtigems.ui.fragment.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.application.drishtigems.R
import com.application.drishtigems.StaffAdapter.adapter.AdapterBottomSheet
import com.application.drishtigems.databinding.FragmentBottomSheetHomeBinding
import com.application.drishtigems.StaffAdapter.model.ModelBottomSheet
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetHome : BottomSheetDialogFragment() {
  lateinit var binding:FragmentBottomSheetHomeBinding
    private var list : ArrayList<ModelBottomSheet> ? = ArrayList()
    var adapterBottomSheet:AdapterBottomSheet?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setStyle(STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)//set corner of bottom sheet

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=DataBindingUtil.inflate(inflater,R.layout.fragment_bottom_sheet_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBottom.visibility=View.GONE
        binding.view4.visibility=View.GONE
        binding.tvNotFound.visibility=View.VISIBLE

            //Bottom Sheet Recycler View and Cross
        bottomRecyclerViewCall()
        bottomSheetList()
        binding.crossView.setOnClickListener {
         dismiss()
        }

    }

    private fun bottomSheetList() {
        list!!.add(ModelBottomSheet("Up to $1,000","10%"))
        list!!.add(ModelBottomSheet("$1,000 to $2,000","15%"))
        list!!.add(ModelBottomSheet("$2,000 to $3,000","20%"))
        list!!.add(ModelBottomSheet("$3,000 to $5,000","25%"))
        list!!.add(ModelBottomSheet("$5,000+","15%"))

    }

    private fun bottomRecyclerViewCall() {
       adapterBottomSheet=AdapterBottomSheet(list)
        binding.rvBottom.adapter=adapterBottomSheet

    }

}