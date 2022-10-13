package com.application.drishtigems.StaffAdapter.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.Network.StaffNetwork.ApiModel.RecentRequest.DataItem
import com.application.drishtigems.R
import com.application.drishtigems.databinding.AdapterRecentRequestBinding
import com.application.drishtigems.StaffAdapter.model.ModelRecentRequest
import kotlinx.android.synthetic.main.adapter_recent_request.view.*

class AdapterRecentRequest(var recentItemList: ArrayList<DataItem>?):RecyclerView.Adapter<AdapterRecentRequest.ViewHolder>() {
    class ViewHolder(binding:AdapterRecentRequestBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=DataBindingUtil.inflate<AdapterRecentRequestBinding>(LayoutInflater.from(parent.context), R.layout.adapter_recent_request,parent,false)
        return ViewHolder(binding)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=recentItemList!![position]
        holder.itemView.apply {
            travel.text=data.typeOfExpense
            dateRecentRequest.text=data.onDateTime
            tittleRecentRequest.text=data.description
            moneyRecentRequest.text=data.price
        }
        holder.itemView.tvPendingApproved.setOnClickListener {
            holder.itemView.tvPendingApproved.text="Approved"
            holder.itemView.tvPendingApproved.setTextColor(R.color.recentRequestColor)
        }
    }
    override fun getItemCount(): Int {
       return recentItemList!!.size
    }
}