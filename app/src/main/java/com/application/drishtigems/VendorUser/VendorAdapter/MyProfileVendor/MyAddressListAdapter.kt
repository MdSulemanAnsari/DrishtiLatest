package com.application.drishtigems.VendorUser.VendorAdapter.MyProfileVendor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorModel.MyProfileModel.MyAddressModel
import com.application.drishtigems.databinding.MyAddressListAdapterBinding
import com.application.drishtigems.databinding.MyWishlistAdapterBinding
import kotlinx.android.synthetic.main.my_address_list_adapter.view.*
import kotlinx.android.synthetic.main.my_wishlist_adapter.view.*

class MyAddressListAdapter(var listDefault:ArrayList<MyAddressModel>): RecyclerView.Adapter<MyAddressListAdapter.ViewHolder>() {
    class ViewHolder(binding: MyAddressListAdapterBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding= DataBindingUtil.inflate<MyAddressListAdapterBinding>(LayoutInflater.from(parent.context), R.layout.my_address_list_adapter,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data=listDefault[position]
        holder.itemView.apply {
            tvDefaultBillingAddress.text=data.nameDefaultBillingAddress
            tvNameDetails.text=data.nameDefault
            tvDbAddress.text=data.addressDefault
            tNumber.text=data.tNumber
        }
    }

    override fun getItemCount(): Int {
        return listDefault.size
    }
}