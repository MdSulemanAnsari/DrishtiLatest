package com.application.drishtigems.StaffAdapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.databinding.AdapterSaleGraphBinding
import com.application.drishtigems.StaffAdapter.model.ModelSaleGraph
import kotlinx.android.synthetic.main.adapter_sale_graph.view.*

class AdapterSaleGraph(var saleList: ArrayList<ModelSaleGraph>?):RecyclerView.Adapter<AdapterSaleGraph.ViewHolder>() {
    class ViewHolder(binding:AdapterSaleGraphBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=DataBindingUtil.inflate<AdapterSaleGraphBinding>(LayoutInflater.from(parent.context), R.layout.adapter_sale_graph,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
   val data=saleList!![position]
        holder.itemView.apply {
            tvSaleDate.text=data.saleDate
            tvTargetAmount.text=data.saleTargetAmount
            tvMoneySale.text=data.saleMoney
        }
    }

    override fun getItemCount(): Int {
       return saleList!!.size
    }
}