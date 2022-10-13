package com.application.drishtigems.StaffAdapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.databinding.AdapterBottomSheetBinding
import com.application.drishtigems.StaffAdapter.model.ModelBottomSheet
import kotlinx.android.synthetic.main.adapter_bottom_sheet.view.*

class AdapterBottomSheet(var list: ArrayList<ModelBottomSheet>?): RecyclerView.Adapter<AdapterBottomSheet.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterBottomSheet.ViewHolder {
        val binding= DataBindingUtil.inflate<AdapterBottomSheetBinding>(LayoutInflater.from(parent.context), R.layout.adapter_bottom_sheet,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterBottomSheet.ViewHolder, position: Int) {

        val data = list!![position]
        holder.itemView.apply {
            tvMonthlyTarget.text=data.monthly
            tvIncentive.text=data.incentive
        }
    }
    override fun getItemCount(): Int {
    return list!!.size

    }
    class ViewHolder(binding:AdapterBottomSheetBinding):RecyclerView.ViewHolder(binding.root) {

    }

}