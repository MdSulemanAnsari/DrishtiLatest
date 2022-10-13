package com.application.drishtigems.StaffAdapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.databinding.AdapterHomeBinding

class AdapterHome:RecyclerView.Adapter<AdapterHome.ViewHolder>() {
    class ViewHolder(binding: AdapterHomeBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val binding=DataBindingUtil.inflate<AdapterHomeBinding>(LayoutInflater.from(parent.context), R.layout.adapter_home,parent,false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }

}