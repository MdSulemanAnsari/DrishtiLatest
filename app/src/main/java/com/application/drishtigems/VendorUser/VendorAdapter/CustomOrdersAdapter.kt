package com.application.drishtigems.VendorUser.VendorAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.Network.StaffNetwork.ApiModel.CustomOrder.CustomOrderRecentModel
import com.application.drishtigems.R
import com.application.drishtigems.databinding.CustomOrderAdapterBinding
import kotlinx.android.synthetic.main.custom_order_adapter.view.*

class CustomOrdersAdapter(var listOrder:ArrayList<CustomOrderRecentModel>):RecyclerView.Adapter<CustomOrdersAdapter.ViewHolder>() {
    class ViewHolder(binding:CustomOrderAdapterBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val binding=DataBindingUtil.inflate<CustomOrderAdapterBinding>(LayoutInflater.from(parent.context), R.layout.custom_order_adapter,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val data=listOrder[position]
      //  val customOrderRecentModel:CustomOrderRecentModel?=null
        holder.itemView.apply {
            tvEmeraldRecentOrder.text=data.gemName
            tvValueWeight.text= data.weightUnit.name
            tvValueQuantity.text= data.quantity.toString()
            tvValueQuality.text= data.stoneQuality.name
            tvValuePriceRange.text="${data.minPrice} ${data.maxPrice}"
            tvDataOrder.text=data.createdTime
        }
    }
    override fun getItemCount(): Int {
       return listOrder.size
    }
}