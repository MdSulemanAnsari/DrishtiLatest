package com.application.drishtigems.StaffAdapter.adapter.Filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.databinding.AdapterTreatmentBinding
import com.application.drishtigems.StaffAdapter.model.Filter.ModelTreatment
import kotlinx.android.synthetic.main.adapter_treatment.view.*

class AdapterTreatment(var filterTreatmentList: ArrayList<ModelTreatment>?): RecyclerView.Adapter<AdapterTreatment.ViewHolder>() {
    class ViewHolder(binding: AdapterTreatmentBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= DataBindingUtil.inflate<AdapterTreatmentBinding>(LayoutInflater.from(parent.context), R.layout.adapter_treatment,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=filterTreatmentList!![position]
        holder.itemView.apply {
            tvTreatment.text=data.treatment
        }

    }
    override fun getItemCount(): Int {
        return filterTreatmentList!!.size
    }

}