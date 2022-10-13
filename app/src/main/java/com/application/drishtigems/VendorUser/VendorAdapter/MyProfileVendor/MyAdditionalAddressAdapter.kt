package com.application.drishtigems.VendorUser.VendorAdapter.MyProfileVendor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorModel.MyProfileModel.MyAdditionalModel
import com.application.drishtigems.VendorUser.VendorModel.MyProfileModel.MyAddressModel
import com.application.drishtigems.databinding.MyAdditionalAddressAdapterBinding
import com.application.drishtigems.databinding.MyAddressListAdapterBinding
import kotlinx.android.synthetic.main.my_additional_address_adapter.view.*
import kotlinx.android.synthetic.main.my_address_list_adapter.view.*

class MyAdditionalAddressAdapter(var listAdditional:ArrayList<MyAdditionalModel>): RecyclerView.Adapter<MyAdditionalAddressAdapter.ViewHolder>() {
    class ViewHolder(binding: MyAdditionalAddressAdapterBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding= DataBindingUtil.inflate<MyAdditionalAddressAdapterBinding>(LayoutInflater.from(parent.context), R.layout.my_additional_address_adapter,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data=listAdditional[position]
        holder.itemView.apply {
            tvNameAdditional.text=data.nameDefaultAdditional
            tvAaAddress.text=data.addressDefaultAdditional
            tNumberAdditional.text=data.tNumberAdditional
        }
    }

    override fun getItemCount(): Int {
        return listAdditional.size
    }
}