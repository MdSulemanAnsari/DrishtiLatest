package com.application.drishtigems.StaffAdapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.databinding.AdapterNotificationBinding
import com.application.drishtigems.StaffAdapter.model.ModelNotification
import kotlinx.android.synthetic.main.adapter_notification.view.*

class AdapterNotification(var notificationList: ArrayList<ModelNotification>?):RecyclerView.Adapter<AdapterNotification.ViewHolder>(){

    class ViewHolder(binding:AdapterNotificationBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterNotification.ViewHolder {
        var binding = DataBindingUtil.inflate<AdapterNotificationBinding>(LayoutInflater.from(parent.context), R.layout.adapter_notification,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterNotification.ViewHolder, position: Int) {
        var data=notificationList!![position]
        holder.itemView.apply {
           tittleNotify.text=data.tittleNotification
            dateNotify.text=data.dateNotification
            timeNotify.text=data.timeNotification
        }
    }

    override fun getItemCount(): Int {
        return notificationList!!.size
    }
}