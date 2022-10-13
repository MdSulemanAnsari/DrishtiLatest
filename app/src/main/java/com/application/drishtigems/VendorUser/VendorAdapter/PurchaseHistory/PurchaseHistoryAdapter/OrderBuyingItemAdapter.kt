package com.application.drishtigems.VendorUser.VendorAdapter.PurchaseHistory.PurchaseHistoryAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorModel.PurchaseHistoryModel.OrderBuyingModel
import com.application.drishtigems.databinding.OrderBuyingItemAdapterBinding
import kotlinx.android.synthetic.main.order_buying_item_adapter.view.*

class OrderBuyingItemAdapter(var listBuying:ArrayList<OrderBuyingModel>):RecyclerView.Adapter<OrderBuyingItemAdapter.ViewHolder>() {
    class ViewHolder(binding:OrderBuyingItemAdapterBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=DataBindingUtil.inflate<OrderBuyingItemAdapterBinding>(LayoutInflater.from(parent.context), R.layout.order_buying_item_adapter,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data=listBuying[position]
        holder.itemView.apply {
            imageBuyingRing.setImageBitmap(data.imageBuying)
            tvBringCaratEmerald.text=data.ringName
            tvBuyingPrice.text=data.ringPrice
            tvSizeValue.text=data.sizeRing
        }

    }

    override fun getItemCount(): Int {
       return listBuying.size
    }
}