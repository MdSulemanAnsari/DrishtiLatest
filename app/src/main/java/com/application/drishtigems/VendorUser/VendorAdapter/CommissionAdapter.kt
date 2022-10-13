package com.application.drishtigems.VendorUser.VendorAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorModel.CommissionModel
import com.application.drishtigems.VendorUser.fragment.ComissionEarned.CommissionProfileEarned
import com.application.drishtigems.databinding.CommissionAdapterBinding
import com.application.drishtigems.ui.activity.MainActivity
import kotlinx.android.synthetic.main.commission_adapter.view.*

class CommissionAdapter(val context: Context,val listCommission:ArrayList<CommissionModel>) :RecyclerView.Adapter<CommissionAdapter.ViewHolder>(){
    class ViewHolder(binding:CommissionAdapterBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=DataBindingUtil.inflate<CommissionAdapterBinding>(LayoutInflater.from(parent.context), R.layout.commission_adapter,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     val data=listCommission[position]
        holder.itemView.apply {
            imageCommission.setImageBitmap(data.imageCommission)
            tvNameCommission.text=data.NameCommission
            tvAddressUser.text=data.AddressCommission
            tvCommissionPrice.text=data.priceCommission
        }
        holder.itemView.setOnClickListener {
            val commissionProfileEarned= CommissionProfileEarned()
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout, commissionProfileEarned).addToBackStack("").commit()
        }
    }

    override fun getItemCount(): Int {
       return listCommission.size
    }
}