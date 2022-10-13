package com.application.drishtigems.VendorUser.VendorAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorModel.OfferModelVendor
import com.application.drishtigems.databinding.OffersVendorAdapterBinding
import kotlinx.android.synthetic.main.offers_vendor_adapter.view.*

class OffersVendorAdapter(var offerListVendor:ArrayList<OfferModelVendor>):RecyclerView.Adapter<OffersVendorAdapter.ViewHolder>() {
    class ViewHolder(binding:OffersVendorAdapterBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var binding=DataBindingUtil.inflate<OffersVendorAdapterBinding>(LayoutInflater.from(parent.context), R.layout.offers_vendor_adapter,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    var data=offerListVendor[position]
        holder.itemView.apply {
            imageOfferVendor.setImageBitmap(data.imageVendor)
            tvVendorOffer.text=data.discountName
        }
    }

    override fun getItemCount(): Int {
        return offerListVendor.size
    }
}