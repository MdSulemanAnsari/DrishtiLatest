package com.application.drishtigems.VendorUser.VendorAdapter.BringOnlineStockAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorModel.BringOnlineStockModel.NoticeModel
import com.application.drishtigems.databinding.EmeraldItemAdapterVendorBinding
import com.application.drishtigems.databinding.NoticeAdapterBinding
import kotlinx.android.synthetic.main.emerald_item_adapter_vendor.view.*
import kotlinx.android.synthetic.main.emerald_item_adapter_vendor.view.imageStockVendor
import kotlinx.android.synthetic.main.notice_adapter.view.*

class NoticeAdapter(var listNotice:ArrayList<NoticeModel>): RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {
    class ViewHolder(binding: NoticeAdapterBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding= DataBindingUtil.inflate<NoticeAdapterBinding>(LayoutInflater.from(parent.context), R.layout.notice_adapter,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data=listNotice[position]
        holder.itemView.apply {
            imageStockRing.setImageBitmap(data.imageNotice)
            tvItemRing.text=data.ringName
        }
    }
    override fun getItemCount(): Int {
        return listNotice.size
    }
}