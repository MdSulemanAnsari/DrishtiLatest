package com.application.drishtigems.VendorUser.VendorAdapter.BringOnlineStockAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorModel.DesignModel
import com.application.drishtigems.databinding.DesignAdapterBinding
import kotlinx.android.synthetic.main.design_adapter.view.*

class DesignAdapter (var listDesignRing:ArrayList<DesignModel>): RecyclerView.Adapter<DesignAdapter.ViewHolder>() {
    class ViewHolder(binding: DesignAdapterBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= DataBindingUtil.inflate<DesignAdapterBinding>(LayoutInflater.from(parent.context), R.layout.design_adapter,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=listDesignRing[position]
        holder.itemView.apply {
            imageStockRingDesign.setImageBitmap(data.imageRingDesign)
            tvItemRing.text=data.nameRingDesign
            tvPriceRing.text=data.priceRingDesign
        }
    }
    override fun getItemCount(): Int {
        return listDesignRing.size
    }
}