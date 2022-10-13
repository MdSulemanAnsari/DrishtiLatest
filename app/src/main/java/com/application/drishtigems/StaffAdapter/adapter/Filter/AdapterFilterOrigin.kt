package com.application.drishtigems.StaffAdapter.adapter.Filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.databinding.AdapterFilterOriginBinding
import com.application.drishtigems.StaffAdapter.model.Filter.ModelFilterOrigin
import kotlinx.android.synthetic.main.adapter_filter_origin.view.*

class AdapterFilterOrigin (var filterOriginList: ArrayList<ModelFilterOrigin>?): RecyclerView.Adapter<AdapterFilterOrigin.ViewHolder>() {
    class ViewHolder(binding: AdapterFilterOriginBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= DataBindingUtil.inflate<AdapterFilterOriginBinding>(LayoutInflater.from(parent.context), R.layout.adapter_filter_origin,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=filterOriginList!![position]
        holder.itemView.apply {
            tvFilterOrigin.text=data.filterOrigin
        }

    }
    override fun getItemCount(): Int {
        return filterOriginList!!.size
    }

}