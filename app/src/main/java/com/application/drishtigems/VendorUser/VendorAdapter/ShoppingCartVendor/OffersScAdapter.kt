package com.application.drishtigems.VendorUser.VendorAdapter.ShoppingCartVendor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorAdapter.OffersVendorAdapter
import com.application.drishtigems.VendorUser.VendorModel.OfferModelVendor
import com.application.drishtigems.databinding.OffersScAdapterBinding
import com.application.drishtigems.databinding.OffersVendorAdapterBinding
import kotlinx.android.synthetic.main.offers_sc_adapter.view.*
import kotlinx.android.synthetic.main.offers_vendor_adapter.view.*
import kotlinx.android.synthetic.main.offers_vendor_adapter.view.imageOfferVendor
import kotlinx.android.synthetic.main.offers_vendor_adapter.view.tvVendorOffer

class OffersScAdapter (var offerListVendorSc:ArrayList<OfferModelVendor>): RecyclerView.Adapter<OffersScAdapter.ViewHolder>() {
    class ViewHolder(binding: OffersScAdapterBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding= DataBindingUtil.inflate<OffersScAdapterBinding>(LayoutInflater.from(parent.context), R.layout.offers_sc_adapter,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data=offerListVendorSc[position]
        holder.itemView.apply {
            imageOfferVendorSc.setImageBitmap(data.imageVendor)
            tvVendorOfferSc.text=data.discountName
        }
    }

    override fun getItemCount(): Int {
        return offerListVendorSc.size
    }
}