package com.application.drishtigems.VendorUser.VendorAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorModel.CommissionProfileEarnedModel
import com.application.drishtigems.databinding.CommissionProfileEarnedAdapterBinding
import kotlinx.android.synthetic.main.commission_profile_earned_adapter.view.*

class CommissionProfileEarnedAdapter(var listCommission:ArrayList<CommissionProfileEarnedModel>):RecyclerView.Adapter<CommissionProfileEarnedAdapter.ViewHolder>() {
    class ViewHolder(binding:CommissionProfileEarnedAdapterBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     var binding=DataBindingUtil.inflate<CommissionProfileEarnedAdapterBinding>(LayoutInflater.from(parent.context), R.layout.commission_profile_earned_adapter,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       var data=listCommission[position]
        holder.itemView.apply {
            imageItemCommission.setImageBitmap(data.emeraldImageCommission)
            tvEmeraldCommission.text=data.emeraldNameCommission
            tvValueBuyingCommission.text=data.buyingOptionName
            tvPriceCommission.text=data.priceItemCommission
            tvDateCommission.text=data.purchaseDateCommission
            tvSoldOnDate.text=data.soldOnDateCommission
            tvCommissionPercentage.text=data.CommissionPrice
        }
    }

    override fun getItemCount(): Int {
       return listCommission.size
    }
}